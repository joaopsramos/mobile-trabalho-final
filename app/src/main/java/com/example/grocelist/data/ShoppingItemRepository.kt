package com.example.grocelist.data

import com.example.grocelist.model.ShoppingItem

class ShoppingItemRepository(private val dao: ShoppingItemDao) {
    fun all() = dao.all()
    fun insert(item: ShoppingItem) = dao.insert(item)
}