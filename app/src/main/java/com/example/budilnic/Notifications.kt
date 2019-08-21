package com.example.budilnic

import android.R
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.media.RingtoneManager
import android.os.Build


class Notifications {

    val NOTIFIYTAG = "new request111"
    fun Notify(context: Context,message:String,number:Int){
        val intent=Intent(context,MainActivity::class.java)
        val channelID = "com.example.budilnic.news"
        val resultIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val icon: Icon = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Icon.createWithResource(context, android.R.drawable.ic_dialog_info)
        } else {
            return
        }
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val action: Notification.Action =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Notification.Action.Builder(icon, "Open", pendingIntent).build()
            } else {
                return
            }

        val builder = Notification.Builder(context, channelID)
            .setDefaults(Notification.DEFAULT_VIBRATE)
            .setContentTitle("New request222333")
                .setContentText(message)
                .setNumber(number)
            .setChannelId(channelID)
            .setSound(defaultSoundUri)
            .setSmallIcon(R.drawable.ic_lock_power_off)
            .setSubText(message)
            .addAction(action)
//                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentIntent(PendingIntent.getActivity(context
                        ,0,intent,PendingIntent.FLAG_UPDATE_CURRENT))

            .setAutoCancel(true)

        val nm=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.ECLAIR) {
            nm.notify(NOTIFIYTAG, 0, builder.build())
        }else{
            nm.notify(NOTIFIYTAG.hashCode(), builder.build())
        }
//        nm!!.notify(NOTIFIYTAG,0, builder.build())

    }

}
