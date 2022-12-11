package com.example.grocelist.data.shopping_item

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.grocelist.model.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {
    @Query("SELECT * FROM shopping_item WHERE cartId = :cartId")
    fun all(cartId: Long): Flow<List<ShoppingItem>>

    @Query("SELECT * FROM shopping_item WHERE id = :id")
    suspend fun get(id: Long): ShoppingItem

    @Insert
    fun insert(item: ShoppingItem)

    @Update
    fun update(item: ShoppingItem)

    @Query("UPDATE shopping_item SET picked = :picked WHERE id = :id")
    fun togglePicked(id: Long, picked: Boolean)

    @Query("DELETE FROM shopping_item WHERE id = :id")
    fun delete(id: Long)
}