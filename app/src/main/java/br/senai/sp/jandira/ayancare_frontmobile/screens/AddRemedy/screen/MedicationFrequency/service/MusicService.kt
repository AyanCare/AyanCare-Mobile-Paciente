package br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.MedicationFrequency.service

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper

class CancelSoundReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Verifique se o MediaPlayer está tocando
        if (Alarme.mediaPlayer?.isPlaying == true) {
            // Pare a reprodução e libere os recursos do MediaPlayer
            Alarme.mediaPlayer?.stop()
            Alarme.mediaPlayer?.release()
            Alarme.mediaPlayer = null
        }

    }
}

class DelayAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        try {
            // Lógica para adiar o alarme em 5 minutos

            // Pausar a reprodução do som
            if (Alarme.mediaPlayer?.isPlaying == true) {
                Alarme.mediaPlayer?.pause()
            }

            // Configurar o alarme para tocar novamente em 5 minutos
            val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarmeIntent = Intent(context, Alarme::class.java).let { intent ->
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            }

            val currentTime = System.currentTimeMillis()
            val delayMillis = 5 * 60 * 1000L // 5 minutos em milissegundos
            val triggerAtMillis = currentTime + delayMillis

            alarmMgr.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, alarmeIntent)

            // Usar um Handler para retomar a reprodução após o atraso
            Handler(Looper.getMainLooper()).postDelayed({
                if (Alarme.mediaPlayer != null) {
                    Alarme.mediaPlayer?.start()
                }
            }, delayMillis)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}


