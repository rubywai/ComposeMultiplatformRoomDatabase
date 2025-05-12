package com.rubylearner.roomdb.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.rubylearner.roomdb.data.database.RoomDb
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun iosDatabaseBuilder() : RoomDatabase.Builder<RoomDb>{
    val dbPath = documentDirectory() + "/room.db"
    return  Room.databaseBuilder<RoomDb>(name = dbPath)
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}