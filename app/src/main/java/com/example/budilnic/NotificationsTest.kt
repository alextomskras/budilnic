package com.example.budilnic


import android.R
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat


class NotificationsTest {

    val NOTIFIYTAG = "new request"
    fun Notify(context: Context, message: String, number: Int) {
        val intent = Intent(context, MainActivity::class.java)

        val builder = NotificationCompat.Builder(context)
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentTitle("New request")
            .setContentText(message)
            .setNumber(number)
            .setSmallIcon(R.drawable.ic_popup_reminder)
            .setContentIntent(
                PendingIntent.getActivity(
                    context
                    , 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .setAutoCancel(true)

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.notify(NOTIFIYTAG, 0, builder.build())
        } else {
            nm.notify(NOTIFIYTAG.hashCode(), builder.build())
        }

    }

}

//fun showNotification(heading: String, description: String, imageUrl: String, intent: Intent) {
//    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//    createChannel()
//    val pendingIntent = PendingIntent.getActivity(
//        this, 0 /* Request code */, intent,
//        PendingIntent.FLAG_ONE_SHOT
//    )
//    val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//
//    val notificationBuilder = NotificationCompat.Builder(this, "channelID")
//        .setSmallIcon(R.drawable.ic_popup_reminder)
//        .setContentTitle(heading)
//        .setContentText(description)
//        .setAutoCancel(true)
//        .setSound(defaultSoundUri)
//        .setContentIntent(pendingIntent)
//
//    val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager?
//    val notificationId = (Date().getTime() / 1000L % Integer.MAX_VALUE) as Int
//    notificationManager!!.notify(notificationId, notificationBuilder.build())
//}
//
//fun createChannel() {
//    if (Build.VERSION.SDK_INT < 26) {
//        return
//    }
//    val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//    val channel = NotificationChannel("channelID", "name", NotificationManager.IMPORTANCE_DEFAULT)
//    channel.description = "Description"
//    notificationManager.createNotificationChannel(channel)
//}
