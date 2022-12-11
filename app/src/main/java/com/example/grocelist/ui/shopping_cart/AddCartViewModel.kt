package com.example.grocelist.ui.shopping_cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocelist.data.shopping_cart.ShoppingCartRepository
import com.example.grocelist.model.ShoppingCart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCartViewModel(private val repo: ShoppingCartRepository) : ViewModel() {
    var cartName by mutableStateOf("")
    var error by mutableStateOf("")

    fun isValid(): Boolean {
        error = if (cartName.isBlank()) "O nome não pode ser vazio" else ""

        if (error.isNotBlank()) {
            return false
        }

        return true
    }

    fun add(userId: Long) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(ShoppingCart(userId, cartName))
    }
}
