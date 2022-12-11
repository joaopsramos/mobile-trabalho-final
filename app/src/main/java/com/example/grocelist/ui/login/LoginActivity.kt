package com.example.grocelist.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.grocelist.MainActivity
import com.example.grocelist.R
import com.example.grocelist.model.User
import com.example.grocelist.ui.register.RegisterActivity
import com.example.grocelist.ui.theme.GrocelistTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: LoginViewModel by viewModel()
            viewModel.login()

            GrocelistTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary,
                ) {
                    Login(viewModel, ::onRegisterClick, ::onLoginSuccess)
                }
            }
        }
    }

    private fun onLoginSuccess(user: User) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("userId", user.id)
        }
        startActivity(intent)
    }

    private fun onRegisterClick() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun Login(viewModel: LoginViewModel, onRegisterClick: () -> Unit, onLoginSuccess: (User) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Logo()

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Grocelist",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.surface
        )

        Spacer(modifier = Modifier.height(64.dp))

        Form(viewModel)

        Button(
            onClick = { onRegisterClick() },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 36.dp, end = 36.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
        ) {
            Text(text = "Registrar", modifier = Modifier.padding(vertical = 8.dp))
        }

        with(viewModel.uiState) {
            when (this) {
                is LoginUiState.Success -> onLoginSuccess(user)
                is LoginUiState.Error -> {
                    Snackbar(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 32.dp
                        )
                    ) {
                        Text(text = message)
                    }
                }
                else -> {}
            }
        }
    }
}

@Composable
private fun Logo() {
    Surface(
        color = MaterialTheme.colors.surface,
        shape = CircleShape,
        modifier = Modifier.padding(top = 18.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "Icon",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .padding(all = 18.dp)
        )
    }
}

@Composable
private fun Form(viewModel: LoginViewModel) {
    val formFieldModifier = Modifier
        .fillMaxWidth()
        .padding(start = 38.dp, end = 38.dp)

    TextField(
        value = viewModel.email,
        onValueChange = { viewModel.email = it },
        label = { Text(text = "E-mail") },
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "person") },
        colors = TextFieldDefaults.textFieldColors(
            MaterialTheme.colors.onBackground,
            backgroundColor = MaterialTheme.colors.background
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = formFieldModifier
    )
    TextField(
        value = viewModel.password,
        onValueChange = { viewModel.password = it },
        label = { Text(text = "Senha") },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lock") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            MaterialTheme.colors.onBackground,
            backgroundColor = MaterialTheme.colors.background
        ),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = formFieldModifier.padding(top = 8.dp)
    )

    Button(
        onClick = { viewModel.login() },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        modifier = formFieldModifier.padding(top = 16.dp)
    ) {
        Text(text = "Entrar", modifier = Modifier.padding(vertical = 8.dp))
    }
}
