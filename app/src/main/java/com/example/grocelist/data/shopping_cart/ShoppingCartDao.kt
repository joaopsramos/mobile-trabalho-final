package com.example.grocelist.data.shopping_cart

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.grocelist.model.ShoppingCart
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingCartDao {
    @Query("SELECT * FROM shopping_cart WHERE userId = :userId")
    fun all(userId: Long): Flow<List<ShoppingCart>>

    @Query("SELECT * FROM shopping_cart WHERE id = :cartId")
    fun get(cartId: Long): Flow<ShoppingCart>

    @Insert
    fun insert(cart: ShoppingCart)

    @Query("SELECT * FROM shopping_cart WHERE userId = :userId ORDER BY id DESC LIMIT 1")
    fun getLast(userId: Long): Flow<ShoppingCart>

    @Query("DELETE FROM shopping_cart WHERE id = :id")
    fun delete(id: Long)
}