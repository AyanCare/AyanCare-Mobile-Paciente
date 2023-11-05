package br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository

import android.content.Context
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.DAO.AyanCareDb
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.criacaoTabela.AlarmeTbl

class alarmeRepository(context: Context) {

    private val db = AyanCareDb.getDatabase(context)

    fun saveAlarm(alarme: AlarmeTbl): Long {
        return db.alarmeDao().save(alarme)
    }

    fun updateAlarm(alarme: AlarmeTbl): Long {
        return db.alarmeDao().update(alarme).toLong()
    }

    fun findAlarm(alarmeId: Long): List<AlarmeTbl> {
        return db.alarmeDao().findAlarm()
    }

    fun deleteAlarm(): Int {
        return db.alarmeDao().deleteAll()
    }
}