package com.example.acedev.tasktracker.Utils

import android.content.Context
import android.os.CountDownTimer
import android.widget.TextView
import com.example.acedev.tasktracker.Entity.TasksEntity
import java.util.concurrent.TimeUnit

class TimerClass(val task : TasksEntity, val timeView : TextView, val context : Context) {
    private lateinit var mCountDownTimer : CountDownTimer
    private var hr = 0L
    private var min = 0L
    private val notification = NotificationClass(task.title, context)
    private val millis = task.duration - task.elapsedTime

    fun startTimer() {
        mCountDownTimer = object : CountDownTimer(millis, 1000*60) {
            override fun onTick(millisUntilFinished: Long) {
                hr = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
                min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(hr)
                val timeRemaining = "$hr Hours $min Minutes remaining"
                timeView.text = timeRemaining
                task.elapsedTime = task.elapsedTime + (1000*60)
            }

            override fun onFinish() {
                timeView.text = "Task Completed"
                notification.sendNotification()
            }
        }
        mCountDownTimer.start()
    }
    fun stopTimer() {
        mCountDownTimer.cancel()
    }
}