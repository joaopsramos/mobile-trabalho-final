package com.example.grocelist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.grocelist.model.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {
    @Query("SELECT * FROM shopping_item")
    fun all(): Flow<List<ShoppingItem>>

    @Insert
    fun insert(item: ShoppingItem)
}