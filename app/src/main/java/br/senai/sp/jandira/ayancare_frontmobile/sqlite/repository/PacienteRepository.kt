package br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository

import android.content.Context
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.DAO.AyanCareDb
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.criacaoTabela.Paciente

class PacienteRepository(context: Context) {

    private val db = AyanCareDb.getDatabase(context)

    fun save(paciente: Paciente): Long {
        return db.pacienteDao().save(paciente)
    }

    fun update(paciente: Paciente): Long{
        return db.pacienteDao().update(paciente).toLong()
    }

    fun findUsers(): List<Paciente> {
        return db.pacienteDao().findUsers()
    }

    fun deleteUser(): Int{
        return  db.pacienteDao().deleteAll()
    }
}