package br.senai.sp.jandira.ayancare_frontmobile.sqlite.funcaoQueChamaSqlLite

import android.content.Context
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.criacaoTabela.Paciente
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository

fun saveLogin (
    context: Context,
    id: Long,
    nome: String,
    token: String,
    dataNascimento: String,
    genero: String,
    email: String,
    //senha: String,
) : Long {

    val newPaciente = Paciente(
        id = id,
        nome = nome,
        token = token,
        email = email,
        //senha = senha,
        dataNascimento = dataNascimento,
        genero = genero
    )

    return PacienteRepository(context).save(newPaciente)
}
fun deleteUserSQLite(context: Context, id: Int): Int {
    return PacienteRepository(context).deleteUser()
}