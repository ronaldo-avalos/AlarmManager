package mx.vecinio.notificationsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import mx.vecinio.notificationsapp.data.ProgramadorDeAlarma
import mx.vecinio.notificationsapp.model.AlarmItem
import mx.vecinio.notificationsapp.ui.theme.NotificationsAppTheme
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Suppress("UNUSED_EXPRESSION")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var alarmItem: AlarmItem? by remember {
                        mutableStateOf(null)
                    }
                    val programador = ProgramadorDeAlarma(applicationContext)
                    var tiempo by remember {
                        mutableStateOf("")
                    }

                    Greeting(
                        value = tiempo,
                        onValueChange = { tiempo = it },
                        onClick = {
                            alarmItem = AlarmItem(
                                id = "caracol",
                                time = LocalDateTime.now().plusSeconds(tiempo.toLong()),
                                message = "mendaje de alarma"
                            )
                            alarmItem?.let { programador.programarAlarma(it) }
                            tiempo = ""
                        },
                        onClickCancel = {
                            alarmItem?.let { programador.cancelarAlarma(it) }
                        }
                    )

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(
    value: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    onClickCancel: () -> Unit
) {


    val timeDialogState = rememberMaterialDialogState()
    var pickedTime by remember { mutableStateOf(LocalTime.NOON) }
    val formattedTime by remember {

        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm a")
                .format(pickedTime)
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = {
            timeDialogState.show()
        }) {
            Text(text = "Pick time")
        }
        OutlinedTextField(value = value, onValueChange = onValueChange)
        Text(text = formattedTime)
        Button(onClick = { onClick() }) {
            Text(text = "Programar notificacion")
        }
        Button(onClick = { onClickCancel() }) {
            Text(text = "Cancelar notificacion")
        }
        Text("Horas seleccionadas:")
        LazyColumn {
//            items(selectedTimes) { time ->
//                Text(text = DateTimeFormatter.ofPattern("hh:mm a").format(time))
//            }
        }

    }
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "Ok") {
//                createNotification(pickedTime)
//                selectedTimes.toMutableList().add(pickedTime)
            }
            negativeButton(text = "Cancel")
        }
    ) {
        timepicker(
            initialTime = LocalTime.NOON,
            title = "Pick a time",
        ) {
            pickedTime = it
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    NotificationsAppTheme {

    }
}