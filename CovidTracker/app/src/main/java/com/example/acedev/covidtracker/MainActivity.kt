package com.example.acedev.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.acedev.covidtracker.APIClasses.Client
import com.example.acedev.covidtracker.Adapters.StateListAdapter
import com.example.acedev.covidtracker.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : StateListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        recyclerView = binding.recyclerView
        adapter = StateListAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        GlobalScope.launch (Dispatchers.Main){
            val response = withContext(Dispatchers.IO) { Client.api.getData()}

            if(response.isSuccessful) {
                Log.i("network", "${response.body()}")
                withContext(Dispatchers.Main) {
                    adapter.updateList(response.body()!!.statewise)
                    binding.totalActiveCount.text = response!!.body()!!.statewise[0].active
                    binding.totalConfirmCount.text = response!!.body()!!.statewise[0].confirmed
                    binding.totalDeathCount.text = response!!.body()!!.statewise[0].deaths
                    binding.totalRecoveredCount.text = response!!.body()!!.statewise[0].recovered

                    val lastUpdateTime = response!!.body()!!.statewise[0].lastupdatedtime
                    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                    binding.tvLastUpdate.text = getLastUpdateTime(simpleDateFormat.parse(lastUpdateTime))
                }
            }
            else {
                Log.i("network", "Failed")
            }
        }
    }
    fun getLastUpdateTime(updateTime : Date) : String {
        val currentTime = Date()
        val seconds = TimeUnit.MILLISECONDS.toSeconds(currentTime.time - updateTime.time)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(currentTime.time - updateTime.time)
        val hours = TimeUnit.MILLISECONDS.toHours(currentTime.time - updateTime.time)

        return when {
            seconds < 60 -> {
                "Few seconds ago"
            }
            minutes < 60 -> {
                "$minutes Minutes ago"
            }
            hours < 24 -> {
                "$hours Hours ago"
            }
            else -> {
                SimpleDateFormat("dd/MM/yyyy, hh:mm a").format(updateTime).toString()
            }
        }
    }
}