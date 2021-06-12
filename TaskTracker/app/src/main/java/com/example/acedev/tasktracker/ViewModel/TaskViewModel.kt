package com.example.acedev.tasktracker.ViewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.acedev.tasktracker.Dao.TasksDao
import com.example.acedev.tasktracker.Database.TasksDatabase
import com.example.acedev.tasktracker.Entity.TasksEntity
import com.example.acedev.tasktracker.MainActivity
import com.example.acedev.tasktracker.Repository.TaskRepository
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.math.log

class TaskViewModel (application : Application) : AndroidViewModel(application){

    val taskRepository : TaskRepository
    val taskDao : TasksDao?
    val tasksList : LiveData<List<TasksEntity>>
    var activeTask : TasksEntity? = null

    init {
        taskDao = TasksDatabase.getDatabaseInstance(application)?.getDatabaseDao()
        taskRepository = TaskRepository(taskDao!!)
        tasksList = taskRepository.taskList
        activeTask = null
    }

    fun clearDatabase() {
        taskRepository.clearData()
    }

    fun insertTask(task : TasksEntity) {
        taskRepository.insertTask(task)
    }

    fun updateTask() {
        taskRepository.updateTask(activeTask!!)
    }
}