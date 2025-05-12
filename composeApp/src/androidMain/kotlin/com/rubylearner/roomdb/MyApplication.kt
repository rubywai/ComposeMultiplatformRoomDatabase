package com.rubylearner.roomdb

import android.app.Application
import com.rubylearner.roomdb.di.androidDbModule
import com.rubylearner.roomdb.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            modules(androidDbModule, sharedModule)
        }
    }
}