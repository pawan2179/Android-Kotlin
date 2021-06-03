package com.example.acedev.tasktracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.acedev.tasktracker.Adapter.TaskAdapter
import com.example.acedev.tasktracker.ViewModel.TaskViewModel
import com.example.acedev.tasktracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val recyclerView : RecyclerView = binding.recyclerView
        val adapter = TaskAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
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
    }
}