package com.example.acedev.tasktracker.Repository

import androidx.lifecycle.LiveData
import com.example.acedev.tasktracker.Dao.TasksDao
import com.example.acedev.tasktracker.Entity.TasksEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskRepository(private val taskDao : TasksDao) {
    val taskList : LiveData<List<TasksEntity>> = taskDao.getTasks()

    fun insertTask(task : TasksEntity) {
        GlobalScope.launch (Dispatchers.IO){
            taskDao.insert(task)
        }
    }

    fun clearData() {
        GlobalScope.launch (Dispatchers.IO){
            taskDao.clearDatabase()
        }
    }
}