package com.example.grocelist

import android.app.Application
import com.example.grocelist.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GroceListApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GroceListApplication)
            modules(appModule)
        }
    }
}