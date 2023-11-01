package mx.vecinio.notificationsapp.data

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class CreateNotification(private val context: Context) {


  /*  @SuppressLint("ServiceCast", "ScheduleExactAlarm")
    fun setAlarm(context: Context, alarmTime: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, Broadcast::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Configura la alarma
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            pendingIntent
        )

        // Inicia el servicio en segundo plano
        val serviceIntent = Intent(context, AlarmService::class.java)
        ContextCompat.startForegroundService(context, serviceIntent)
    }*/

}


/*

    private val notificationId = 1 //"idOncalls"

    @RequiresApi(Build.VERSION_CODES.O)
    fun createSimpleNotification(time: LocalTime) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(context, 0, intent, flag)

        val builder = NotificationCompat.Builder(context, Myapp.CHANNEL_ID)
            .setSmallIcon(R.drawable.notifications_ic)
            .setContentTitle("Guardias")
            .setContentText("Mañana es día de guardia a las ${DateTimeFormatter.ofPattern("hh:mm a").format(time)}")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(notificationId, builder.build())
        }
    }
}*/
