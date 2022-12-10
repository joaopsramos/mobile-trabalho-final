package com.example.grocelist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_item")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val userId: Long,
    val name: String,
    val qty: Int,
    val picked: Boolean = false
) {
    constructor(userId: Long, name: String, qty: Int, picked: Boolean = false) : this(0L, userId, name, qty, picked)
}
