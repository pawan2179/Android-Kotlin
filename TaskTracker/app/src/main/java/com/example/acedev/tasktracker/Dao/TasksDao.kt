package com.example.acedev.tasktracker.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.acedev.tasktracker.Entity.TasksEntity

@Dao
interface TasksDao {
    @Insert
    suspend fun insert(task : TasksEntity)

    @Query("Delete from TasksDatabase")
    suspend fun clearDatabase()

    @Query("Select * from TasksDatabase")
    fun getTasks() : LiveData<List<TasksEntity>>

}