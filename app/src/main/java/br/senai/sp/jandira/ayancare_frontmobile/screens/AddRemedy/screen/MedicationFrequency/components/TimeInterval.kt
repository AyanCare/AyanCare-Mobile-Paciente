package br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.MedicationFrequency.components


import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.MedicationFrequency.service.Alarme
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.criacaoTabela.AlarmeTbl
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.alarmeRepository
import java.text.SimpleDateFormat
import java.util.Calendar

@Composable
fun TimeInterval(
    width: Int,
    localStorage: Storage
) {
    val context = LocalContext.current
    var selectedTime by remember { mutableStateOf(Calendar.getInstance()) }
    var showTimePicker by remember { mutableStateOf(false) }
    val currentTime = selectedTime
    val hourOfDay = currentTime.get(Calendar.HOUR_OF_DAY)
    val minute = currentTime.get(Calendar.MINUTE)
    val time = localStorage.lerValor(context, "id_intervalo")

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = formatTime(currentTime),
            onValueChange = { /* Não implementado aqui */ },
            modifier = Modifier
                .width(width.dp),
            singleLine = true,
            readOnly = true,
            leadingIcon = {
                IconButton(
                    onClick = {
                        showTimePicker = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.LockClock,
                        contentDescription = ""
                    )
                }
            }
        )

        BackHandler(enabled = showTimePicker) {
            showTimePicker = false
        }

        if (showTimePicker) {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, min ->
                selectedTime.set(Calendar.HOUR_OF_DAY, hour)
                selectedTime.set(Calendar.MINUTE, min)
                showTimePicker = false

                val alarme = AlarmeTbl(
                    dia = "Segunda",
                    horario = formatTime(selectedTime)
                )

                val alarmeId = alarmeRepository(context).saveAlarm(alarme)

                Log.d("Alarme", "ID do alarme: $alarmeId")

                // Comparar o horário atual com o horário selecionado.
                val currentCalendar = Calendar.getInstance()
                if (selectedTime.after(currentCalendar)) {
                    // O horário selecionado é no futuro.
                    // Calcular o atraso até o horário selecionado.
                    val delayMillis = selectedTime.timeInMillis - currentCalendar.timeInMillis

                    // Usar um Handler para agendar a notificação após o atraso.
                    Handler(Looper.getMainLooper()).postDelayed({
                        if (time != null) {
                            configureRepeatingNotification(context, selectedTime, time.toInt() )
                        }

                    }, delayMillis)
                }
            }

            // Mostrar o TimePickerDialog para selecionar o horário.
            TimePickerDialog(
                context,
                timeSetListener,
                hourOfDay,
                minute,
                true
            ).show()
        }
    }
}


@SuppressLint("SimpleDateFormat")
private fun formatTime(calendar: Calendar): String {
    val sdf = SimpleDateFormat("HH:mm")
    return sdf.format(calendar.time)
}

private fun configureRepeatingNotification(context: Context, selectedTime: Calendar, time: Int) {
    val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, Alarme::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    val currentTime = Calendar.getInstance()
    val startTime = selectedTime.timeInMillis

    // Calcula o intervalo em que a notificação será repetida (a cada 2 minutos)
    val intervalMillis = time * 60 * 1000


    // Agenda a notificação repetida, começando no horário selecionado
    alarmMgr.setRepeating(
        AlarmManager.RTC_WAKEUP,
        startTime,
        intervalMillis.toLong(),
        pendingIntent
    )
}
