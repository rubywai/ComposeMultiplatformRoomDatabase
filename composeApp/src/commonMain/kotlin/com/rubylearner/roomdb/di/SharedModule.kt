package com.rubylearner.roomdb.di

import com.rubylearner.roomdb.data.database.CreateDatabase
import com.rubylearner.roomdb.data.database.RoomDb
import com.rubylearner.roomdb.viewmodel.TodoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sharedModule = module {
    single<RoomDb> {
        CreateDatabase(get()).getDatabase()
    }
    viewModel<TodoViewModel> {
        TodoViewModel(db =  get() )
    }
}