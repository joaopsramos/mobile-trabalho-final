package com.example.grocelist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grocelist.data.shopping_cart.ShoppingCartDao
import com.example.grocelist.data.shopping_item.ShoppingItemDao
import com.example.grocelist.data.user.UserDao
import com.example.grocelist.model.ShoppingCart
import com.example.grocelist.model.ShoppingItem
import com.example.grocelist.model.User

@Database(entities = [ShoppingItem::class, ShoppingCart::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shoppingItemDao(): ShoppingItemDao
    abstract fun shoppingCartDao(): ShoppingCartDao
    abstract fun userDao(): UserDao

    companion object {
        private const val databaseName = "grocelist-db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }

            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                databaseName
            ).build()
        }
    }
}
