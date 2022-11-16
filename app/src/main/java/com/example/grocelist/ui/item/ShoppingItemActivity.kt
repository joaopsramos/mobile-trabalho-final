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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrocelistTheme {
                val itemId = intent.getLongExtra("id", -1)

                if (itemId != -1L) {
                    viewModel.fillItemInformation(itemId)

                    ShoppingItem(viewModel, buttonText = "Atualizar") {
                        viewModel.update()
                        finish()
                    }
                } else {
                    ShoppingItem(viewModel, buttonText = "Adicionar") {
                        viewModel.add()
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
fun ShoppingItem(viewModel: ShoppingItemViewModel, buttonText: String, onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            OutlinedTextField(
                value = viewModel.itemName,
                label = { Text("Item") },
                onValueChange = { viewModel.itemName = it },
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp),
                textStyle = MaterialTheme.typography.body1,
                maxLines = 1,
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.surface)
            )

            OutlinedTextField(
                value = viewModel.itemQty,
                onValueChange = { value ->
                    viewModel.itemQty = value.filter { it.isDigit() }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                label = { Text("Qty") },
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(end = 16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.surface)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onButtonClick.invoke() }) {
            Text(text = buttonText)
        }
    }
}
