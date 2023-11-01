package mx.vecinio.notificationsapp.repository

import mx.vecinio.notificationsapp.model.AlarmItem

interface AlarmRepository {

    fun programarAlarma(item: AlarmItem)
    fun cancelarAlarma(item: AlarmItem)
}