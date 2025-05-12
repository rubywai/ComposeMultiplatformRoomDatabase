package com.rubylearner.roomdb.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rubylearner.roomdb.data.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert
    suspend fun insert(todoDao: TodoItem)

    @Update
    suspend fun update(todoDao: TodoItem)

    @Delete
    suspend fun delete(todoDao: TodoItem)

    @Query("select * from TodoItem")
    fun getAllTodos() : Flow<List<TodoItem>>

}