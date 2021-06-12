package com.example.acedev.tasktracker.Dao

import androidx.annotation.LongDef
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.acedev.tasktracker.Entity.TasksEntity

@Dao
interface TasksDao {
    @Insert
    suspend fun insert(task : TasksEntity)

    @Query("Delete from TasksDatabase")
    suspend fun clearDatabase()

    @Query("Select * from TasksDatabase order by priority desc")
    fun getTasks() : LiveData<List<TasksEntity>>

    @Query("Select * from TasksDatabase where id =:tid ")
    suspend fun getActiveTask(tid : Int) : TasksEntity

    @Query("Delete from TasksDatabase where id = :tid")
    suspend fun deleteActivity(tid:Int)

    @Query("Update TasksDatabase set elapsedTime =:timePassed where id =:tid")
    suspend fun updateTask(tid:Int, timePassed : Long)

    @Update
    suspend fun updateTask(task : TasksEntity)
}