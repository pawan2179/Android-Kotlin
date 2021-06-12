package com.example.acedev.tasktracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.acedev.tasktracker.Entity.TasksEntity
import com.example.acedev.tasktracker.Utils.FinalObjects
import com.example.acedev.tasktracker.ViewModel.TaskViewModel
import com.example.acedev.tasktracker.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add)

        val titleET = binding.etaddTaskTitle
        val remarksET = binding.etaddTaskRemarks
        val priority = binding.spinneraddTaskTextPriority
        val hrs = binding.spinnerHrs
        val minutes = binding.spinnerMinutes

        setSpinner()
        val viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
                TaskViewModel::class.java
            )

        binding.btnaddTaskAdd.setOnClickListener {

            if(titleET.text.isNullOrEmpty() or remarksET.text.isNullOrEmpty()) {
                Toast.makeText(this, "Enter valid Title and Remarks", Toast.LENGTH_SHORT).show()
            }
            else {
                val time = calculateTimeMillis(hrs.selectedItem.toString().trim().toInt(), minutes.selectedItem.toString().trim().toInt())
                val task = TasksEntity(titleET.text.toString(),
                    remarksET.text.toString(),
                    priority.selectedItem.toString().toInt(),
                    time)

                viewModel.insertTask(task)
                Toast.makeText(this, "Task Added Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun setSpinner() {
        val hrsAdapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, FinalObjects.getSpinnerHourList())
        val minuteAdapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, FinalObjects.getSpinnerMinuteList())
        val priorityAdapter = ArrayAdapter<Int>(this, R.layout.support_simple_spinner_dropdown_item, FinalObjects.getSpinnerPriorityList())

        binding.spinnerHrs.adapter = hrsAdapter
        binding.spinnerHrs.setSelection(0)
        binding.spinnerMinutes.adapter = minuteAdapter
        binding.spinnerMinutes.setSelection(0)
        binding.spinneraddTaskTextPriority.adapter = priorityAdapter
        binding.spinneraddTaskTextPriority.setSelection(0)
    }

    private fun calculateTimeMillis(hrs : Int, minutes : Int) : Long{
        return ((60*60*hrs*1000) + (60*minutes*1000)).toLong()
    }
}