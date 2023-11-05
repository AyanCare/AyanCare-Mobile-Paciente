package br.senai.sp.jandira.ayancare_frontmobile.sqlite.criacaoTabela

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_Alarme")
data class AlarmeTbl(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dia: String = "",
    val intervalo: Int = 0,
    val horario: String = "",
    val id_medicamento: Long = 0,
    val selectedHour: Int = 0,       // Hora selecionada no TimePicker
    val selectedMinute: Int = 0      // Minutos selecionados no TimePicker
)
