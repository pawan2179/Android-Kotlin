package com.example.acedev.tasktracker.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.acedev.tasktracker.Dao.TasksDao
import com.example.acedev.tasktracker.Entity.TasksEntity

@Database(entities = [TasksEntity::class], version = 1, exportSchema = false)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun getDatabaseDao() : TasksDao?

    companion object {
        @Volatile
        private var INSTANCE : TasksDatabase? = null

        fun getDatabaseInstance(context : Context) : TasksDatabase? {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, TasksDatabase::class.java, "TasksDatabase").build()
            }
            return INSTANCE
        }
    }
}