package com.example.grocelist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_cart")
data class ShoppingCart(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val userId: Long,
    val name: String,
) {
    constructor(userId: Long, name: String) : this(0L, userId, name)
}