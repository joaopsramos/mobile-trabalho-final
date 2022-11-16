package com.example.grocelist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_item")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val qty: Int,
    val picked: Boolean = false
) {
    constructor(name: String, qty: Int, picked: Boolean = false) : this(0L, name, qty, picked)
}
