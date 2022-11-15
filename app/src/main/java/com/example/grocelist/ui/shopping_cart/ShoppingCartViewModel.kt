package com.example.grocelist.ui.shopping_cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocelist.data.ShoppingItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingCartViewModel(private val repo: ShoppingItemRepository) : ViewModel() {
    fun all(picked: Boolean) = repo.all(picked)

    fun delete(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(id)
    }

    fun togglePicked(id: Long, picked: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        repo.togglePicked(id, picked)
    }
}