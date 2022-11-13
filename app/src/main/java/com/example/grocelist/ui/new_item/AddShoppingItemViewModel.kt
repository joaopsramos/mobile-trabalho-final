package com.example.grocelist.ui.new_item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocelist.data.ShoppingItemRepository
import com.example.grocelist.model.ShoppingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddShoppingItemViewModel(private val repo: ShoppingItemRepository) : ViewModel() {
    var itemName by mutableStateOf("")
    var itemQty by mutableStateOf("1")

    fun add() = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(ShoppingItem(itemName, itemQty.toInt()))
    }
}