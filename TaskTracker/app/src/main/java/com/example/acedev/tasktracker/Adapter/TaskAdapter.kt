package com.example.acedev.tasktracker.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.acedev.tasktracker.Entity.TasksEntity
import com.example.acedev.tasktracker.R
import com.example.acedev.tasktracker.databinding.ActivityMainBinding.inflate

class TaskAdapter(val context : Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder> () {

    val taskList = ArrayList<TasksEntity>()

    inner class TaskViewHolder(task : View) : RecyclerView.ViewHolder(task) {
        val titleView : TextView = task.findViewById(R.id.tvTitle)
        val remarksView : TextView = task.findViewById(R.id.tvRemarks)
        val durationView : TextView = task.findViewById(R.id.tvDuration)
        val priorityView : TextView = task.findViewById(R.id.tvPriority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val viewHolder = TaskViewHolder(LayoutInflater.from(context).inflate(R.layout.tasks_list_item, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.titleView.text = taskList[position].title
        holder.remarksView.text = taskList[position].remarks
        holder.durationView.text = taskList[position].duration.toString()
        holder.priorityView.text = taskList[position].priority.toString()
    }

    override fun getItemCount(): Int = taskList.size

    fun updateList(newList : List<TasksEntity>) {
        taskList.clear()
        taskList.addAll(newList)
        notifyDataSetChanged()
    }
}