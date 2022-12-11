package com.example.grocelist.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocelist.data.shopping_item.ShoppingItemRepository
import com.example.grocelist.model.ShoppingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingItemViewModel(private val repo: ShoppingItemRepository) : ViewModel() {
    private var itemId: Long? = null
    private var cartId: Long? = null

    var itemName by mutableStateOf("")
    var itemQty by mutableStateOf("1")
    var picked by mutableStateOf("false")

    var nameError by mutableStateOf("")
    var qtyError by mutableStateOf("")

    fun fillItemInformation(cartId: Long, itemId: Long) {
        viewModelScope.launch {
            if (itemId == -1L) return@launch

            this@ShoppingItemViewModel.itemId = itemId
            this@ShoppingItemViewModel.cartId = cartId

            val item = repo.get(itemId)

            itemName = item.name
            itemQty = item.qty.toString()
            picked = item.picked.toString()
        }
    }

    fun isValid(): Boolean {
        nameError = if (itemName.isEmpty()) "O nome do item não pode ser vazio" else ""
        qtyError = if (itemQty.toInt() <= 0) "A quantidade deve ser maior que 0" else ""

        if (nameError.isNotBlank() || qtyError.isNotBlank()) {
            return false
        }

        return true
    }

    fun add(cartId: Long) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(ShoppingItem(cartId, itemName, itemQty.toInt()))
    }

    fun update() = viewModelScope.launch(Dispatchers.IO) {
        repo.update(
            ShoppingItem(
                itemId ?: 0L,
                cartId ?: 0L,
                itemName,
                itemQty.toInt(),
                picked.toBoolean()
            )
        )
    }
}