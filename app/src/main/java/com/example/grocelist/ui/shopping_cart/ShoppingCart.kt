package com.example.grocelist.ui.shopping_cart

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.grocelist.model.ShoppingItem
import com.example.grocelist.ui.GenericItemsList

@Composable
fun ShoppingCart(
    viewModel: ShoppingCartViewModel,
    userId: Long,
    onAddClick: (() -> Unit)? = null,
    onItemClick: ((ShoppingItem) -> Unit)? = null,
    onImportClick: (() -> Unit)? = null,
    onSubAddClick: (() -> Unit)? = null,
) {
    val context = LocalContext.current
    val cart = viewModel.getCart(userId).collectAsState(initial = null)
    val cartId = cart.value?.id ?: -1L
    val items = viewModel.all(cartId).collectAsState(initial = listOf())

    val onDeleteClick: (ShoppingItem) -> Unit = { item -> viewModel.delete(item.id) }
    val onTogglePicked: (ShoppingItem, Boolean) -> Unit =
        { item, picked -> viewModel.togglePicked(item.id, picked) }
    val onShareClick: (List<ShoppingItem>) -> Unit = { local_items ->
        val intent = Intent(Intent.ACTION_SEND)

        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            local_items.joinToString("\n") { "${it.qty} - ${it.name}" })
        context.startActivity(intent)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrinho de compras") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                actions = {
                    if (onSubAddClick != null) {
                        IconButton(onClick = { onSubAddClick.invoke() }) {
                            Icon(Icons.Default.Add, contentDescription = "share")
                        }
                    }
                }
            )

        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                GenericItemsList(
                    items = items,
                    title = cart.value?.name,
                    onTogglePicked = onTogglePicked,
                    onItemClick = onItemClick,
                    onDeleteClick = onDeleteClick,
                    onAddClick = if (cartId == -1L) null else onAddClick,
                    onShareClick = if (cartId == -1L) null else onShareClick,
                    onImportClick = if (cartId == -1L) null else onImportClick,
                )
            }
        }
    )
}


