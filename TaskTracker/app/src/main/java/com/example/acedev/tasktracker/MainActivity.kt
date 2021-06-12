package com.example.acedev.tasktracker

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Adapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.acedev.tasktracker.Adapter.TaskAdapter
import com.example.acedev.tasktracker.Entity.TasksEntity
import com.example.acedev.tasktracker.Utils.TimerClass
import com.example.acedev.tasktracker.ViewModel.TaskViewModel
import com.example.acedev.tasktracker.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity(), TaskAdapter.TaskOnClickListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : TaskViewModel
    private lateinit var adapter : TaskAdapter
    private lateinit var timer : TimerClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        val animationDrawable = binding.activeTaskRL.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(10)
        animationDrawable.setExitFadeDuration(5000)
        animationDrawable.start()

        binding.activeTaskCard.visibility = View.GONE

        val recyclerView : RecyclerView = binding.recyclerView
        adapter = TaskAdapter(this, this@MainActivity)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(TaskViewModel::class.java)

        viewModel.tasksList.observe(this, {
            if(it != null) {
                adapter.updateList(it)
            }
        })

        binding.btnNewTask.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        binding.btnActiveTaskPause.setOnClickListener {
            setActiveWindowBackground("inactive")
            viewModel.updateTask()
            timer.stopTimer()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.item_clearList -> {
                viewModel.clearDatabase()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onButtonClicked(task : TasksEntity) {
         Log.i("Active", "$task")
        displayActiveTask(task)
        setActiveWindowBackground("active")
        viewModel.activeTask = task
        timer = TimerClass(task, binding.tvActiveTaskDuration, this)
        timer.startTimer()
    }


    fun displayActiveTask(task : TasksEntity) {
        binding.tvActiveTaskTitle.text = task.title
        binding.tvActiveTaskRemarks.text = task.remarks
        binding.tvActiveTaskDuration.text = adapter.formatTime(task.duration)
        binding.tvActiveTaskPriority.text = "Priority : ${task.priority}"

        binding.btnActiveTaskPause.visibility = View.VISIBLE
        binding.activeTaskCard.visibility = View.VISIBLE
    }

    fun setActiveWindowBackground(state : String) {

        when(state) {
            "active" -> {
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    binding.activeTaskRL.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.gradient_animation))
                    binding.btnActiveTaskPause.visibility = View.VISIBLE
                }
                else {
                    binding.activeTaskRL.background = AppCompatResources.getDrawable(this, R.drawable.gradient_animation)
                    binding.btnActiveTaskPause.visibility = View.VISIBLE
                }
            }
            "inactive" -> {
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    binding.activeTaskRL.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.gradient_pause_animation))
                    binding.btnActiveTaskPause.visibility = View.GONE
                }
                else {
                    binding.activeTaskRL.background = AppCompatResources.getDrawable(this, R.drawable.gradient_pause_animation)
                    binding.btnActiveTaskPause.visibility = View.GONE
                }
            }
        }
    }
}