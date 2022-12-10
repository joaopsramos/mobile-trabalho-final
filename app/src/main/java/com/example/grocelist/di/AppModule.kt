package com.example.grocelist.di

import com.example.grocelist.data.AppDatabase
import com.example.grocelist.data.ShoppingItemRepository
import com.example.grocelist.data.UserRepository
import com.example.grocelist.ui.shopping_cart.ShoppingCartViewModel
import com.example.grocelist.ui.item.ShoppingItemViewModel
import com.example.grocelist.ui.login.LoginViewModel
import com.example.grocelist.ui.register.RegisterViewModel
import com.example.grocelist.ui.shopping_cart.ImportCartViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(androidApplication()) }
    single { get<AppDatabase>().shoppingItemDao() }
    single { get<AppDatabase>().userDao() }

    factory { ShoppingItemRepository(get()) }
    factory { UserRepository(get()) }

    viewModel { ShoppingCartViewModel(get()) }
    viewModel { ShoppingItemViewModel(get()) }
    viewModel { ImportCartViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
}
