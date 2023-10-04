package br.senai.sp.jandira.ayancare_frontmobile.sqlite.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.criacaoTabela.Paciente

@Dao
interface PacienteDao {

    @Insert
    fun save(paciente:Paciente): Long

    @Update
    fun update(paciente: Paciente): Int

}