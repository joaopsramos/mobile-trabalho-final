package com.example.grocelist.ui.register

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
import androidx.compose.material.icons.filled.Email
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
import com.example.grocelist.R
import com.example.grocelist.model.User
import com.example.grocelist.ui.login.LoginActivity
import com.example.grocelist.ui.theme.GrocelistTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: RegisterViewModel by viewModel()

            GrocelistTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary,
                ) {
                    Register(viewModel) {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun Register(viewModel: RegisterViewModel, onRegisterSuccess: (User) -> Unit) {
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

        Form(viewModel, onRegisterSuccess)
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
private fun Form(viewModel: RegisterViewModel, onRegisterSuccess: (User) -> Unit) {
    val formFieldModifier = Modifier
        .fillMaxWidth()
        .padding(start = 38.dp, end = 38.dp)

    TextField(
        value = viewModel.name,
        onValueChange = { viewModel.name = it },
        label = { Text(text = "Name") },
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "person") },
        colors = TextFieldDefaults.textFieldColors(
            MaterialTheme.colors.onBackground,
            backgroundColor = MaterialTheme.colors.background
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = formFieldModifier
    )
    TextField(
        value = viewModel.email,
        onValueChange = { viewModel.email = it },
        label = { Text(text = "E-mail") },
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "email") },
        colors = TextFieldDefaults.textFieldColors(
            MaterialTheme.colors.onBackground,
            backgroundColor = MaterialTheme.colors.background
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = formFieldModifier.padding(top = 8.dp)
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
        onClick = { viewModel.register() },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        modifier = formFieldModifier.padding(top = 16.dp)
    ) {
        Text(text = "Cadastrar", modifier = Modifier.padding(vertical = 8.dp))
    }

    with(viewModel.uiState) {
        when (this) {
            is RegisterUiState.Success -> onRegisterSuccess(user)
            is RegisterUiState.Error -> {
                Snackbar(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp)) {
                    Text(text = message)
                }
            }
            else -> {} // do nothing
        }
    }
}
