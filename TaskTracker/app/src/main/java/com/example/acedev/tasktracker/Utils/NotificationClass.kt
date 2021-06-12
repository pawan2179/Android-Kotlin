package com.example.acedev.tasktracker.Utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.acedev.tasktracker.MainActivity
import com.example.acedev.tasktracker.R

class NotificationClass(val title : String, val context : Context) {
    var notificationManager : NotificationManager? = null
    val description = "Task Completed"
    val NOTIFICATION_ID = 1234
    val id = "Task Completed Notification"

    fun sendNotification() {
        if(notificationManager == null) {
            notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            var channel = notificationManager!!.getNotificationChannel(id)

            if(channel == null) {
                channel = NotificationChannel(id, title, importance)
                channel.description = description
                channel.enableVibration(true)
                channel.enableLights(true)
                notificationManager!!.createNotificationChannel(channel)
            }
        }
        val builder = NotificationCompat.Builder(context, id)
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        builder.setContentTitle(title)
            .setContentText(description)
            .setTicker(description)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_launcher_foreground)

        val notification = builder.build()
        notificationManager!!.notify(NOTIFICATION_ID, notification)
    }
}