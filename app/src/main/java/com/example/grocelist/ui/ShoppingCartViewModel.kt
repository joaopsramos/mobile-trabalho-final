package com.example.grocelist.ui

import androidx.lifecycle.ViewModel
import com.example.grocelist.data.ShoppingItemRepository

class ShoppingCartViewModel(private val repo: ShoppingItemRepository) : ViewModel() {
    fun all() = repo.all()
}