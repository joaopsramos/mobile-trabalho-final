package com.example.grocelist.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocelist.data.ShoppingItemRepository
import com.example.grocelist.model.ShoppingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingItemViewModel(private val repo: ShoppingItemRepository) : ViewModel() {
    private var itemId: Long? = null

    var itemName by mutableStateOf("")
    var itemQty by mutableStateOf("1")
    var picked by mutableStateOf("false")

    fun fillItemInformation(itemId: Long) {
        viewModelScope.launch {
            if (itemId == -1L) return@launch

            this@ShoppingItemViewModel.itemId = itemId

            val item = repo.get(itemId)

            itemName = item.name
            itemQty = item.qty.toString()
            picked = item.picked.toString()
        }
    }

    fun add() = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(ShoppingItem(itemName, itemQty.toInt()))
    }

    fun update() = viewModelScope.launch(Dispatchers.IO) {
        repo.update(ShoppingItem(itemId ?: 0L, itemName, itemQty.toInt(), picked.toBoolean()))
    }
}