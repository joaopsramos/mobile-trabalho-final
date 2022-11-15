package com.example.grocelist.data

import com.example.grocelist.model.ShoppingItem

class ShoppingItemRepository(private val dao: ShoppingItemDao) {
    fun all(picked: Boolean) = dao.all(picked)
    fun insert(item: ShoppingItem) = dao.insert(item)
    fun delete(id: Long) = dao.delete(id)
    fun togglePicked(id: Long, picked: Boolean) = dao.togglePicked(id, picked)
}