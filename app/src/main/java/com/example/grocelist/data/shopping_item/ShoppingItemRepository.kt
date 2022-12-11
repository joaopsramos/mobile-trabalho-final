package com.example.grocelist.data.shopping_item

import com.example.grocelist.model.ShoppingItem

class ShoppingItemRepository(private val dao: ShoppingItemDao) {
    fun all(cartId: Long) = dao.all(cartId)
    suspend fun get(id: Long) = dao.get(id)
    fun insert(item: ShoppingItem) = dao.insert(item)
    fun update(item: ShoppingItem) = dao.update(item)
    fun delete(id: Long) = dao.delete(id)
    fun togglePicked(id: Long, picked: Boolean) = dao.togglePicked(id, picked)
}