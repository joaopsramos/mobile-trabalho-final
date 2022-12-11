package com.example.grocelist.data.shopping_cart

import com.example.grocelist.model.ShoppingCart

class ShoppingCartRepository(private val dao: ShoppingCartDao) {
    fun all(userId: Long) = dao.all(userId)
    fun get(cartId: Long) = dao.get(cartId)
    fun insert(cart: ShoppingCart) = dao.insert(cart)
    fun getLast(userId: Long) = dao.getLast(userId)
    fun delete(cartId: Long) = dao.delete(cartId)
}