package com.example.grocelist.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.grocelist.model.ShoppingItem
import com.example.grocelist.ui.shopping_cart.ShoppingCartViewModel

@Composable
fun History(viewModel: ShoppingCartViewModel, userId: Long, onItemClick: (ShoppingItem) -> Unit) {
    val onDeleteClick: (ShoppingItem) -> Unit = { item -> viewModel.delete(item.id) }
    val items = viewModel.all(userId, picked = true).collectAsState(initial = listOf())
    val onTogglePicked: (ShoppingItem, Boolean) -> Unit =
        { item: ShoppingItem, picked: Boolean -> viewModel.togglePicked(item.id, picked) }

    ShoppingItemList(
        items = items,
        title = "Histórico",
        onTogglePicked = onTogglePicked,
        onItemClick = onItemClick,
        onDeleteClick = onDeleteClick
    )
}
