package com.example.grocelist.ui.shopping_cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocelist.data.shopping_cart.ShoppingCartRepository
import com.example.grocelist.data.shopping_item.ShoppingItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingCartViewModel(private val repo: ShoppingItemRepository, private val cartRepo: ShoppingCartRepository) : ViewModel() {
    fun all(cartId: Long) = repo.all(cartId)

    fun delete(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(id)
    }

    fun togglePicked(id: Long, picked: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        repo.togglePicked(id, picked)
    }

    fun getCart(userId: Long) = cartRepo.getLast(userId)
    fun allCarts(userId: Long) = cartRepo.all(userId)
    fun deleteCart(cartId: Long) = cartRepo.delete(cartId)
}