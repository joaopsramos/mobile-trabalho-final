package com.example.grocelist.data.shopping_cart

import com.example.grocelist.model.ShoppingCart

class ShoppingCartRepository(private val dao: ShoppingCartDao) {
    fun insert(cart: ShoppingCart) = dao.insert(cart)
}