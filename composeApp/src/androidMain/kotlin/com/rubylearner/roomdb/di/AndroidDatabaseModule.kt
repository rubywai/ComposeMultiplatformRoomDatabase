package com.rubylearner.roomdb.di

import androidx.room.RoomDatabase
import com.rubylearner.roomdb.data.database.RoomDb
import com.rubylearner.roomdb.database.androidDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidDbModule = module {
    single<RoomDatabase.Builder<RoomDb>> {
       androidDatabaseBuilder(androidContext())
    }
}