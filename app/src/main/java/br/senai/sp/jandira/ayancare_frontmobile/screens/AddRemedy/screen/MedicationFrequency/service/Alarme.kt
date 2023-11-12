package br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.MedicationFrequency.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import br.senai.sp.jandira.ayancare_frontmobile.R

class Alarme : BroadcastReceiver() {
    companion object {
        var mediaPlayer: MediaPlayer? = null
    }

    override fun onReceive(context: Context, intent: Intent?) {

        try {

            // Iniciar a reprodução de som
            mediaPlayer = MediaPlayer.create(context, R.raw.lofi_study_112191)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()

            showNotification(context, "AyanCare", "Agua")

        } catch (e: Exception) {
            Log.d("Receive Exception", e.printStackTrace().toString())
        }
    }

    private fun showNotification(context: Context, title: String, description: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelName = "message_channel"
        val channelId = "message_id"

        // Intent para adiar o alarme em 5 minutos
        val delayIntent = Intent(context, DelayAlarmReceiver::class.java)
        val delayPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            delayIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        Log.d("ERRO 5 MINUTOS", "$delayPendingIntent")

        // Intent para cancelar o som do alarme
        val cancelSoundIntent = Intent(context, CancelSoundReceiver::class.java)
        val cancelSoundPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            cancelSoundIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.logo)
            .addAction(0, "Mais 5 minutos", delayPendingIntent)
            .addAction(0, "cancelar", cancelSoundPendingIntent)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        manager.notify(1, builder.build())
    }

}




