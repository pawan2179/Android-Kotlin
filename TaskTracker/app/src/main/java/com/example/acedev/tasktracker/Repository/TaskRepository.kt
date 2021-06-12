package com.example.acedev.tasktracker.Repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.acedev.tasktracker.Dao.TasksDao
import com.example.acedev.tasktracker.Entity.TasksEntity
import kotlinx.coroutines.*

class TaskRepository(private val taskDao : TasksDao) {
    val taskList : LiveData<List<TasksEntity>> = taskDao.getTasks()
    var activeTask : TasksEntity? = null
    fun insertTask(task : TasksEntity) {
        GlobalScope.launch (Dispatchers.IO){
            taskDao.insert(task)
        }
    }

    suspend fun getActiveTask(id : Int) {
        val job = GlobalScope.launch(Dispatchers.IO) {
            activeTask = taskDao.getActiveTask(id)
            Log.i("ActiveInJoin", "$activeTask")
        }
        job.join()
        Log.i("ActiveOutJoin", "$activeTask")
    }

    fun clearData() {
        GlobalScope.launch (Dispatchers.IO){
            taskDao.clearDatabase()
        }
    }

    fun deleteTask(id: Int) {
        GlobalScope.launch (Dispatchers.IO){
            taskDao.deleteActivity(id)
        }
    }

    fun updateTask(task : TasksEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            taskDao.updateTask(task)
        }
    }
}