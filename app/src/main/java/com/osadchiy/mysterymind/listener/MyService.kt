package com.example.mysterymind.listener


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.mysterymind.R
import com.example.mysterymind.controllerTest.classofscreen.MainActivity

@Suppress("DEPRECATION")
class MyService : Service() {
    private val handler = Handler()
    private lateinit var notificationManager: NotificationManager
    private val notificationId = 1
    private val channelId = "MyChannelId"

    private val maxNotifications = 2 // Кількість повідомлень, які потрібно відправити
    private var notificationCount = 0 // Лічильник відправлених повідомлень

    private val message = object : Runnable {
        override fun run() {
            if (notificationCount < maxNotifications) {
                sendNotification("Не забувайте, що ви можете у нас покаятися")
                notificationCount++
                handler.postDelayed(this, 20000)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.post(message)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(message)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "My Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(message: String) {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Карма")
            .setContentText(message)
            .setSmallIcon(R.drawable.god)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationId, notification)
    }
}



