package com.example.grocelist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.grocelist.model.ShoppingItem

@Composable
fun ShoppingItemList(
    items: State<List<ShoppingItem>>,
    title: String,
    onAddClick: (() -> Unit)? = null,
    onTogglePicked: (ShoppingItem, Boolean) -> Unit,
    onDeleteClick: ((ShoppingItem) -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, style = MaterialTheme.typography.h5)
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items.value) { item ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
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
                        shape = MaterialTheme.shapes.medium
                    ) {
                        ShoppingItem(item, onTogglePicked)
                    }

                    if (onDeleteClick != null) {
                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            IconButton(onClick = { onDeleteClick.invoke(item) }) {
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FloatingActionButton(
                onClick = { onAddClick?.invoke() },
                contentColor = MaterialTheme.colors.surface,
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "add")
            }
        }
    }
}

@Composable
fun ShoppingItem(
    item: ShoppingItem,
    onCheckboxClick: (item: ShoppingItem, picked: Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 12.dp, start = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = item.picked,
                onCheckedChange = { onCheckboxClick.invoke(item, it) },
                Modifier.padding(1.dp)
            )

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = item.name,
                style = MaterialTheme.typography.subtitle1
            )
        }

        Text(
            modifier = Modifier.padding(end = 4.dp),
            text = "Qty: ${item.qty}",
            style = MaterialTheme.typography.subtitle1
        )
    }
}
