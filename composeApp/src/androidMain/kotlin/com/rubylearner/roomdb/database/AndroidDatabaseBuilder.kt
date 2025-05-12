package com.rubylearner.roomdb.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rubylearner.roomdb.data.database.RoomDb

fun androidDatabaseBuilder(context: Context) : RoomDatabase.Builder<RoomDb>{
    val dbFile = context.applicationContext.getDatabasePath("room.db")
    return Room.databaseBuilder(context,dbFile.absolutePath)
}