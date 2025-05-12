package com.rubylearner.roomdb.di

import androidx.room.RoomDatabase
import com.rubylearner.roomdb.data.database.RoomDb
import com.rubylearner.roomdb.database.iosDatabaseBuilder
import org.koin.core.context.startKoin
import org.koin.dsl.module

val iosDbModule = module {
    single<RoomDatabase.Builder<RoomDb>> { iosDatabaseBuilder()  }
}

fun initKoin(){
    startKoin{
        modules(iosDbModule, sharedModule)
    }
}