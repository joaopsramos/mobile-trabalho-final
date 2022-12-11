package com.example.grocelist.ui.shopping_cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocelist.data.shopping_item.ShoppingItemRepository
import com.example.grocelist.model.ShoppingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImportCartViewModel(private val repo: ShoppingItemRepository) : ViewModel() {
    var input by mutableStateOf("")
    var error by mutableStateOf("")

    fun isValid(): Boolean {
        error = if (input.isBlank()) "Nada para ser importado" else ""

        if (error.isNotBlank()) {
            return false
        }

        return true
    }

    fun import(cartId: Long) = viewModelScope.launch(Dispatchers.IO) {
        for (line in input.lines()) {
            val split = line.split("-", limit = 2)
            val shoppingItem = ShoppingItem(cartId, split[1].trim(), split[0].trim().toIntOrNull() ?: 1)

            repo.insert(shoppingItem)
        }
    }
}
