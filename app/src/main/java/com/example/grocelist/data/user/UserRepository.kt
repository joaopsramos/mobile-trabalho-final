package com.example.grocelist.data.user

import com.example.grocelist.model.User

class UserRepository(private val dao: UserDao) {
    suspend fun getUserByEmailAndPassword(email: String, password: String): User? {
        return dao.getByEmailAndPassword(email, password)
    }

    suspend fun emailExists(email: String) = dao.emailExists(email)

    fun insert(user: User) = dao.insert(user)
}
