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

class ImportCartActivity : ComponentActivity() {
    private val viewModel: ImportCartViewModel by viewModel()
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
                            viewModel.import(userId)
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
fun ShoppingItem(viewModel: ImportCartViewModel, onButtonClick: () -> Unit) {
    Row {
        OutlinedTextField(
            value = viewModel.input,
            isError = viewModel.error.isNotBlank(),
            label = { Text("Importar carrinho") },
            onValueChange = { viewModel.input = it },
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            singleLine = false,
            maxLines = 10,
            colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.surface),
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Button(modifier = Modifier.padding(bottom = 16.dp), onClick = { onButtonClick.invoke() }) {
        Text(text = "Importar")
    }
}
