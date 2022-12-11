package com.example.grocelist.data.shopping_cart

import androidx.room.Dao
import androidx.room.Insert
import com.example.grocelist.model.ShoppingCart

@Dao
interface ShoppingCartDao {
    @Insert
    fun insert(cart: ShoppingCart)
}