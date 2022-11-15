package com.example.grocelist.ui

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
import com.example.grocelist.ui.theme.GrocelistTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrocelistTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary,
                ) {
                    Login() {
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun Login(onLogin: (() -> Unit)? = null) {
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

        Form(onLogin)
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
private fun Form(onLogin: (() -> Unit)? = null) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val formFieldModifier = Modifier
        .fillMaxWidth()
        .padding(start = 38.dp, end = 38.dp)

    TextField(
        value = email,
        onValueChange = { email = it },
        label = { Text(text = "E-mail") },
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "person") },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.background),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = formFieldModifier
    )
    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(text = "Senha") },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lock") },
        colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.background),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = formFieldModifier.padding(top = 8.dp)
    )

    Button(
        onClick = { onLogin?.invoke() },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        modifier = formFieldModifier.padding(top = 16.dp)
    ) {
        Text(text = "Entrar", modifier = Modifier.padding(vertical = 8.dp))
    }
}
