package com.example.grocelist.ui.history

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.grocelist.model.ShoppingCart

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun History(
    items: List<ShoppingCart>,
    onDeleteClick: (Long) -> Unit,
    onItemClick: (ShoppingCart) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Histórico de compras") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                    items(items) { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(top = 2.dp, bottom = 2.dp)
                                    .border(
                                        1.5.dp,
                                        MaterialTheme.colors.secondaryVariant,
                                        MaterialTheme.shapes.medium
                                    ),
                                shape = MaterialTheme.shapes.medium,
                                onClick = { onItemClick(item) }
                            ) {
                                ShoppingCartItem(item)
                            }

                            Column(modifier = Modifier.padding(start = 8.dp)) {
                                IconButton(onClick = { onDeleteClick.invoke(item.id) }) {
                                    Icon(
                                        Icons.Outlined.Delete,
                                        "delete",
                                        tint = MaterialTheme.colors.error
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ShoppingCartItem(item: ShoppingCart) {
    Row(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 12.dp, start = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = item.name,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}