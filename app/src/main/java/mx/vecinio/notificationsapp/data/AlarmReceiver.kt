package mx.vecinio.notificationsapp.data

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import mx.vecinio.notificationsapp.MainActivity
import mx.vecinio.notificationsapp.Myapp.Companion.CHANNEL_ID
import mx.vecinio.notificationsapp.R
import mx.vecinio.notificationsapp.model.AlarmItem
import java.time.ZoneId

class AlarmReceiver : BroadcastReceiver() {
    companion object{
         val notificationId = 1 //"idOncalls"
    }



    @SuppressLint("ScheduleExactAlarm")
    override fun onReceive(context: Context, intent: Intent?) {
        createSimpleNotification(context)
        val id = intent?.getStringExtra("ID") ?: return

        // Programar una nueva alarma para 5 segundos después de que se muestre la notificación
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val newIntent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", "Mensaje de la nueva alarma")
            putExtra("ID", id)
        }

        val newPendingIntent = PendingIntent.getBroadcast(
            context,
            id.hashCode(),
            newIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val tiempoDisparo = System.currentTimeMillis() + 5000
        // 5 segundos en milisegundos
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            tiempoDisparo,
            newPendingIntent
        )


    }

    private fun createSimpleNotification(context: Context) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(context, 0, intent, flag)
        val bitmapLargeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.cita)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("title")
            .setContentText("content")
            .setContentIntent(pendingIntent)
            .setLargeIcon(bitmapLargeIcon)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        val notificationManager = NotificationManagerCompat.from(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(notificationId, notification)
    }


}