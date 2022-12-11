package com.example.grocelist.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.grocelist.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE email = :email and password = :password")
    suspend fun getByEmailAndPassword(email: String, password: String): User?

    @Query("SELECT EXISTS(SELECT 1 FROM user WHERE email = :email LIMIT 1)")
    suspend fun emailExists(email: String): Boolean

    @Insert
    fun insert(user: User): Long
}
