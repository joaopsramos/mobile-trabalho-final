package com.example.grocelist.ui.history

import androidx.lifecycle.ViewModel
import com.example.grocelist.data.shopping_cart.ShoppingCartRepository
import com.example.grocelist.data.shopping_item.ShoppingItemRepository

class HistoryDetailsViewModel(
    private val repository: ShoppingItemRepository,
    private val cartRepository: ShoppingCartRepository
) : ViewModel() {
    fun cart(cartId: Long) = cartRepository.get(cartId)
    fun items(cartId: Long) = repository.all(cartId)
}