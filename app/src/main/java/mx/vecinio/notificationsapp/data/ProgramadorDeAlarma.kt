package mx.vecinio.notificationsapp.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import mx.vecinio.notificationsapp.AlarmReceiver
import mx.vecinio.notificationsapp.model.AlarmItem
import mx.vecinio.notificationsapp.repository.AlarmRepository
import java.time.ZoneId

class ProgramadorDeAlarma(
    private val context: Context
) : AlarmRepository {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)
    override fun programarAlarma(item: AlarmItem) {
        val intent = Intent(context,AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", item.message)
            //TODO pasar los parametros de la notificación , como title, description ,url e icon
        }
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            item.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            PendingIntent.getBroadcast(
                context,
                item.hashCode(), //TODO cambiar por un id identificacle
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

            )
        )
    }

    override fun cancelarAlarma(item: AlarmItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.hashCode(), //TODO cambiar por un id identificacle
                Intent(context,AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

            )
        )
    }
}