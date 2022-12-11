package com.example.grocelist.ui.item

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.grocelist.ui.theme.GrocelistTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingItemActivity : ComponentActivity() {
    private val viewModel: ShoppingItemViewModel by viewModel()
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
                    val itemId = intent.getLongExtra("id", -1)
                    val cartId = intent.getLongExtra("cartId", -1)

                    if (itemId != -1L) {
                        viewModel.fillItemInformation(cartId, itemId)

                        ShoppingItem(viewModel, buttonText = "Atualizar") {
                            if (viewModel.isValid()) {
                                viewModel.update()
                                finish()
                            }
                        }
                    } else {
                        ShoppingItem(viewModel, buttonText = "Adicionar") {
                            if (viewModel.isValid()) {
                                viewModel.add(cartId)
                                finish()
                            }
                        }
                    }

                    Column() {
                        if (viewModel.nameError.isNotBlank()) {
                            Snackbar(modifier = snackbarModifier) {
                                Text(text = viewModel.nameError)
                            }
                        }

                        if (viewModel.qtyError.isNotBlank()) {
                            Snackbar(modifier = snackbarModifier) {
                                Text(text = viewModel.qtyError)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShoppingItem(viewModel: ShoppingItemViewModel, buttonText: String, onButtonClick: () -> Unit) {
    Row {
        OutlinedTextField(
            value = viewModel.itemName,
            isError = viewModel.nameError.isNotBlank(),
            label = { Text("Item") },
            onValueChange = { viewModel.itemName = it },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            textStyle = MaterialTheme.typography.body1,
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.surface),
        )

        OutlinedTextField(
            value = viewModel.itemQty,
            isError = viewModel.qtyError.isNotBlank(),
            onValueChange = { value ->
                viewModel.itemQty = value.filter { it.isDigit() }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { Text("Qtd") },
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(end = 16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.surface)
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Button(modifier = Modifier.padding(bottom = 16.dp), onClick = { onButtonClick.invoke() }) {
        Text(text = buttonText)
    }
}
