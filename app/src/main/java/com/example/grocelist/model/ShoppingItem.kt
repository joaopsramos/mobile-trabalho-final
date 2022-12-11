package com.example.grocelist.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_item",
    foreignKeys = [ForeignKey(
        entity = ShoppingCart::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("cartId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val cartId: Long,
    val name: String,
    val qty: Int,
    val picked: Boolean = false
) {
    constructor(cartId: Long, name: String, qty: Int, picked: Boolean = false) : this(
        0L,
        cartId,
        name,
        qty,
        picked
    )
}
