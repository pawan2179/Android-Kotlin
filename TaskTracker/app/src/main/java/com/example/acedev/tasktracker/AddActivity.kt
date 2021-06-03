package com.example.acedev.tasktracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.acedev.tasktracker.Utils.FinalObjects
import com.example.acedev.tasktracker.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add)

        setSpinner()
    }
    private fun setSpinner() {
        val hrsAdapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, FinalObjects.getSpinnerHourList())
        val minuteAdapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, FinalObjects.getSpinnerMinuteList())

        binding.spinnerHrs.adapter = hrsAdapter
        binding.spinnerMinutes.adapter = minuteAdapter
    }
}