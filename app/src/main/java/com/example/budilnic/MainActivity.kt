package com.example.budilnic

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


@SuppressLint("ByteOrderMark")
class MainActivity : AppCompatActivity() {

    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager =
            getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager
        createNotificationChannel(
            "com.example.budilnic.news",
            "NotifyDemo News",
            "Example News Channel"
        )


        val saveData = SaveData(applicationContext)

        tvShowTime.text = saveData.getHour().toString() + ":" + saveData.getMinute().toString()

    }

    private fun createNotificationChannel(
        id: String, name: String,
        description: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance)

            channel.description = description
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern =
                longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager!!.createNotificationChannel(channel)
        } else {
            return
        }


    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun sendNotification(view: View)
//
//    {
//
//        val notificationID = 101
//
//        val channelID = "com.example.budilnic.news"
//
//
//        val notification = Notification.Builder(
//                this@MainActivity,
//                channelID
//            )
//                .setContentTitle("Example Notification")
//                .setContentText("This is an  example notification.")
//                .setSmallIcon(android.R.drawable.ic_dialog_info)
//                .setChannelId(channelID)
//                .build()
//
//
//        notificationManager!!.notify(notificationID, notification)
//
//    }﻿


    fun BuSetTime(view: View){
        val popTime= PopTime()
        val fm=fragmentManager

        popTime.show(fm,"Select time")


    }

    fun SetTime(Hours:Int, Minute:Int){

        tvShowTime.text= Hours.toString() + ":" + Minute.toString()

        val saveData=SaveData(applicationContext)
        saveData.SaveData(Hours,Minute)
        saveData.setAlarm()


    }

}
