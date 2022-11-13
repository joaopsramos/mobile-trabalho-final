package com.example.grocelist.ui.new_item

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.grocelist.ui.ui.theme.GrocelistTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddShoppingItemActivity : ComponentActivity() {
    private val viewModel: AddShoppingItemViewModel by viewModel()

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
                    Row {
                        OutlinedTextField(
                            value = viewModel.itemName,
                            label = { Text("Item") },
                            onValueChange = { viewModel.itemName = it },
                            modifier = Modifier
//                                    .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            textStyle = MaterialTheme.typography.body1,
                            maxLines = 1
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
//                                    .fillMaxWidth()
                                .padding(end = 16.dp),
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = {
                        viewModel.add()
                        finish()
                    }) {
                        Text(text = "Adicionar")
                    }
                }
            }
        }
    }
}
