package br.senai.sp.jandira.ayancare_frontmobile.sqlite.DAO.AlarmeDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.criacaoTabela.AlarmeTbl

@Dao
interface AlarmeDao {

    @Insert
    fun save(Alarme: AlarmeTbl): Long

    @Update
    fun update(alarm: AlarmeTbl): Int

    @Query("SELECT * FROM tbl_alarme")
    fun findAlarm(): List<AlarmeTbl>

    @Query("DELETE FROM tbl_alarme")
    fun deleteAll(): Int
}
