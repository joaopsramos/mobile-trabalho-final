package com.example.grocelist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val email: String,
    val password: String
) {
    constructor(name: String, email: String, password: String) : this(0L, name, email, password)
}
