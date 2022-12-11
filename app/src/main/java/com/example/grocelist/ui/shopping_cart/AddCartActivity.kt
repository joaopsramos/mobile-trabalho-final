package com.example.grocelist.ui.shopping_cart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.grocelist.ui.theme.GrocelistTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCartActivity : ComponentActivity() {
    private val viewModel: AddCartViewModel by viewModel()
    private val snackbarModifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrocelistTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.background),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val userId = intent.getLongExtra("userId", -1)

                    ShoppingItem(viewModel) {
                        if (viewModel.isValid()) {
                            viewModel.add(userId)
                            finish()
                        }
                    }

                    Column() {
                        if (viewModel.error.isNotBlank()) {
                            Snackbar(modifier = snackbarModifier) {
                                Text(text = viewModel.error)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShoppingItem(viewModel: AddCartViewModel, onButtonClick: () -> Unit) {
    Row {
        OutlinedTextField(
            value = viewModel.cartName,
            isError = viewModel.error.isNotBlank(),
            label = { Text("Nome") },
            onValueChange = { viewModel.cartName = it },
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.surface),
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Button(modifier = Modifier.padding(bottom = 16.dp), onClick = { onButtonClick.invoke() }) {
        Text(text = "Adicionar")
    }
}
