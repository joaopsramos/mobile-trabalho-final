package com.example.grocelist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.grocelist.model.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {
    @Query("SELECT * FROM shopping_item WHERE picked = :picked")
    fun all(picked: Boolean): Flow<List<ShoppingItem>>

    @Insert
    fun insert(item: ShoppingItem)

    @Query("UPDATE shopping_item SET picked = :picked WHERE id = :id")
    fun togglePicked(id: Long, picked: Boolean)

    @Query("DELETE FROM shopping_item WHERE id = :id")
    fun delete(id: Long)
}