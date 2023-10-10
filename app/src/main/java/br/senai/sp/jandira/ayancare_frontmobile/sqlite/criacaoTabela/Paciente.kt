package br.senai.sp.jandira.ayancare_frontmobile.sqlite.criacaoTabela

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_paciente")
data class Paciente(
    @PrimaryKey val id: Long = 0,
    val nome: String = "",
    val token: String = "",
    val dataNascimento: String = "",
    val genero: String = "",
    val email: String = "",
    val senha: String = "",
    val tipo_usuario: String = "",
    val cpf: String = "",
    val foto: String = "",
    val idEndereco: Int = 0,
    val cep: String = "",
    val logradouro: String = "",
    val bairro: String = "",
    val cidade: String = "",
    val estado: String = "",
    val numero: String = ""
)
