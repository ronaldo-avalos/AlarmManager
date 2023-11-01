package mx.vecinio.notificationsapp.model

import java.time.LocalDateTime

data class AlarmItem(
    val id : String,
    val time: LocalDateTime,
    val message: String
)
