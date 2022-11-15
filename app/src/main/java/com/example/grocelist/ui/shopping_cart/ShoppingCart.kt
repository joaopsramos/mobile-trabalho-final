package com.example.grocelist.ui.shopping_cart

import androidx.compose.runtime.*
import com.example.grocelist.model.ShoppingItem
import com.example.grocelist.ui.ShoppingItemList

@Composable
fun ShoppingCart(viewModel: ShoppingCartViewModel, onAddClick: (() -> Unit)? = null) {
    val items = viewModel.all(picked = false).collectAsState(initial = listOf())
    val onDeleteClick: (ShoppingItem) -> Unit = { item: ShoppingItem -> viewModel.delete(item.id) }
    val onTogglePicked: (ShoppingItem, Boolean) -> Unit =
        { item: ShoppingItem, picked: Boolean -> viewModel.togglePicked(item.id, picked) }

    ShoppingItemList(
        items = items,
        title = "Shopping Cart",
        onAddClick,
        onTogglePicked,
        onDeleteClick
    )
}

