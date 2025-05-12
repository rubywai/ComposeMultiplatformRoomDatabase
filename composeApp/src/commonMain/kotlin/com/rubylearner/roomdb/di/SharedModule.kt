package com.rubylearner.roomdb.di

import com.rubylearner.roomdb.data.database.CreateDatabase
import com.rubylearner.roomdb.data.database.RoomDb
import org.koin.dsl.module

val sharedModule = module {
    single<RoomDb> {
        CreateDatabase(get()).getDatabase()
    }
}