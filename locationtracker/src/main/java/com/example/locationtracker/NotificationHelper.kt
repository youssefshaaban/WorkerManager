package com.example.locationtracker


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat


class NotificationHelper(context: Context) : ContextWrapper(context){
    private var manager: NotificationManager? = null
    val WAVE_CHANNEL = "default"
    init {
        var mChannel: NotificationChannel? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = NotificationChannel(
                WAVE_CHANNEL,
                getString(R.string.noti_channel_default), NotificationManager.IMPORTANCE_DEFAULT
            )

            mChannel.description = "no sound"
            mChannel.setSound(null, null)
            mChannel.enableLights(false);
            mChannel.lightColor = Color.GREEN
            mChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            getManager()?.createNotificationChannel(mChannel)
        }
    }


    fun getNotification(
        title: String?, body: String?,
        resultPendingIntent: PendingIntent?,
        idIcon: Int?
    ): NotificationCompat.Builder? {
        val mBuilder: NotificationCompat.Builder
        mBuilder = NotificationCompat.Builder(applicationContext, WAVE_CHANNEL)
        mBuilder.setSmallIcon(getSmallIcon())
        mBuilder.color = ContextCompat.getColor(applicationContext, R.color.purple_200)
        mBuilder.setContentTitle(title)
            .setContentText(body)
            .setContentIntent(resultPendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL).priority = NotificationCompat.PRIORITY_HIGH
        idIcon?.let {
            mBuilder.setSmallIcon(idIcon!!)
        }
        mBuilder.setVibrate(longArrayOf(0L))
        mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        return mBuilder
    }

    /**
     * Send a notification.
     *
     * @param id The ID of the notification
     * @param notification The notification object
     */
    fun notify(id: Int, notification: NotificationCompat.Builder) {
        getManager()?.notify(id, notification.build())
    }


    /**
     * Get the small icon for this app
     *
     * @return The small icon resource id
     */
    private fun getSmallIcon(): Int {
        return R.mipmap.ic_launcher
    }

    /**
     * Get the notification manager.
     *
     *
     * Utility method as this helper works with it a lot.
     *
     * @return The system service NotificationManager
     */
    private fun getManager(): NotificationManager? {
        if (manager == null) {
            manager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return manager
    }


    fun cancelNotification(notificationId: Int) {
        getManager()!!.cancel(notificationId)
    }

}