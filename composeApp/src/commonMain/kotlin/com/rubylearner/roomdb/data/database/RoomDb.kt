package com.rubylearner.roomdb.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.rubylearner.roomdb.data.dao.TodoDao
import com.rubylearner.roomdb.data.model.TodoItem

@Database(
    entities = [TodoItem::class],
    version = 1,
    exportSchema = true,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<RoomDb>{
    override fun initialize(): RoomDb
}