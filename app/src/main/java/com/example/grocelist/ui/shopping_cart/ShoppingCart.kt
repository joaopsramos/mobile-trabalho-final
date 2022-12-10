package com.example.grocelist.ui.shopping_cart

import android.content.Intent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.grocelist.model.ShoppingItem
import com.example.grocelist.ui.ShoppingItemList

@Composable
fun ShoppingCart(
    viewModel: ShoppingCartViewModel,
    userId: Long,
    onAddClick: (() -> Unit)? = null,
    onItemClick: ((ShoppingItem) -> Unit)? = null,
    onImportClick: (() -> Unit)? = null,
) {
    val context = LocalContext.current
    val items = viewModel.all(userId, picked = false).collectAsState(initial = listOf())

    val onDeleteClick: (ShoppingItem) -> Unit = { item -> viewModel.delete(item.id) }
    val onTogglePicked: (ShoppingItem, Boolean) -> Unit =
        { item, picked -> viewModel.togglePicked(item.id, picked) }
    val onShareClick: (List<ShoppingItem>) -> Unit = { local_items ->
        val intent = Intent(Intent.ACTION_SEND)

        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, local_items.joinToString("\n") { "${it.qty} - ${it.name}" })
        context.startActivity(intent)
    }

    ShoppingItemList(
        items = items,
        title = "Carrinho de compras",
        onAddClick = onAddClick,
        onTogglePicked = onTogglePicked,
        onItemClick = onItemClick,
        onDeleteClick = onDeleteClick,
        onShareClick = onShareClick,
        onImportClick = onImportClick
    )

}


