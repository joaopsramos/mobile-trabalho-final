package com.example.grocelist.ui.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector, val name: String) {
    object ShoppingCart : Screen("shoppingCart", Icons.Filled.ShoppingCart, "Carrinho de compras")
    object History : Screen("history", Icons.Filled.List, "Histórico")
}