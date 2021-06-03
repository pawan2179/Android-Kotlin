package com.example.acedev.tasktracker.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.acedev.tasktracker.Dao.TasksDao
import com.example.acedev.tasktracker.Database.TasksDatabase
import com.example.acedev.tasktracker.Entity.TasksEntity
import com.example.acedev.tasktracker.Repository.TaskRepository

class TaskViewModel (application : Application) : AndroidViewModel(application){

    val taskRepository : TaskRepository
    val taskDao : TasksDao?
    val tasksList : LiveData<List<TasksEntity>>

    init {
        taskDao = TasksDatabase.getDatabaseInstance(application)?.getDatabaseDao()
        taskRepository = TaskRepository(taskDao!!)
        tasksList = taskRepository.taskList
    }

    fun clearDatabase() {
        taskRepository.clearData()
    }
    fun insertTask(task : TasksEntity) {
        taskRepository.insertTask(task)
    }
}