package com.example.grocelist.di

import com.example.grocelist.data.AppDatabase
import com.example.grocelist.data.ShoppingItemRepository
import com.example.grocelist.ui.shopping_cart.ShoppingCartViewModel
import com.example.grocelist.ui.new_item.AddShoppingItemViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(androidApplication()) }
    single { get<AppDatabase>().shoppingItemDao() }

    factory { ShoppingItemRepository(get()) }

    viewModel { ShoppingCartViewModel(get()) }
    viewModel { AddShoppingItemViewModel(get()) }
}