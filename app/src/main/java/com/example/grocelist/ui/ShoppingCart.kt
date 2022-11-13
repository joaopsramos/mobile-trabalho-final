package com.example.grocelist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.grocelist.model.ShoppingItem
import androidx.compose.foundation.lazy.items

@Composable
fun ShoppingCart(viewModel: ShoppingCartViewModel, onAddClick: (() -> Unit)? = null) {
    val items = viewModel.all().collectAsState(initial = listOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        LazyColumn(modifier = Modifier.heightIn(0.dp, 570.dp)) {
            items(items.value) { item ->
                ShoppingItem(item)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
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
fun ShoppingItem(item: ShoppingItem) {
    var checkedState by remember { mutableStateOf(false) }

    Surface(
//        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .border(1.5.dp, MaterialTheme.colors.secondaryVariant, MaterialTheme.shapes.medium),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkedState,
                    onCheckedChange = { checkedState = it },
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
}
