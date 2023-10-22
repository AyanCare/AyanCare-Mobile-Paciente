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
    foto:String,
    tipo: String
) : Long {

    val newPaciente = Paciente(
        id = id,
        nome = nome,
        token = token,
        email = email,
        dataNascimento = dataNascimento,
        genero = genero,
        foto = foto,
        tipo_usuario = tipo
    )

    return PacienteRepository(context).save(newPaciente)
}
fun deleteUserSQLite(context: Context, id: Int): Int {
    return PacienteRepository(context).deleteUser()
}