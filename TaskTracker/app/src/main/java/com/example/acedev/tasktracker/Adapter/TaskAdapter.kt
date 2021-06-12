package com.example.acedev.tasktracker.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.acedev.tasktracker.Entity.TasksEntity
import com.example.acedev.tasktracker.R
import com.example.acedev.tasktracker.databinding.ActivityMainBinding.inflate
import com.google.android.material.button.MaterialButton
import java.util.concurrent.TimeUnit

class TaskAdapter(val context : Context, val taskClickListener : TaskOnClickListener) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder> () {

    val taskList = ArrayList<TasksEntity>()

    inner class TaskViewHolder(task : View) : RecyclerView.ViewHolder(task) {
        val titleView : TextView = task.findViewById(R.id.tvTitle)
        val remarksView : TextView = task.findViewById(R.id.tvRemarks)
        val durationView : TextView = task.findViewById(R.id.tvDuration)
        val priorityView : TextView = task.findViewById(R.id.tvPriority)

        val btnStart : MaterialButton = task.findViewById(R.id.btnTaskStart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val viewHolder = TaskViewHolder(LayoutInflater.from(context).inflate(R.layout.tasks_list_item, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.titleView.text = taskList[position].title
        holder.remarksView.text = taskList[position].remarks
        holder.durationView.text = formatTime(taskList[position].duration - taskList[position].elapsedTime)
        holder.priorityView.text = "Priority : " + taskList[position].priority.toString()

        holder.btnStart.setOnClickListener {
            taskClickListener.onButtonClicked(taskList[position])
            holder.durationView.text = "Task Active"
        }
    }

    override fun getItemCount(): Int = taskList.size

    fun updateList(newList : List<TasksEntity>) {
        taskList.clear()
        taskList.addAll(newList)
        notifyDataSetChanged()
    }
    fun formatTime(millis : Long) : String{

        Log.i("Time", "$millis")
        val hrs = TimeUnit.MILLISECONDS.toHours(millis)
        val mins = (TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)))

        return  "$hrs Hours $mins Minutes"
    }

    interface TaskOnClickListener {
        fun onButtonClicked(task : TasksEntity)
    }
}