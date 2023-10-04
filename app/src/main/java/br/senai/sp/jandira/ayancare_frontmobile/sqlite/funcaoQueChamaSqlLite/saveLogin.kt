package br.senai.sp.jandira.ayancare_frontmobile.sqlite.funcaoQueChamaSqlLite

import android.content.Context
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.criacaoTabela.Paciente
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository

fun saveLogin (
    context: Context,
    id: Long,
    nome: String,
    token: String,
    //dataNascimento: String,
    //genero: String,
    email: String,
    senha: String,
//    cpf: String,
//    foto: String,
//    idEndereco: Int,
//    cep: String,
//    logradouro: String,
//    bairro: String,
//    cidade: String,
//    estado: String,
//    numero: String
) : Long {

    val newPaciente = Paciente(
        id = id,
        nome = nome,
        token = token,
        email = email,
        senha = senha
//        cep = cep,
//        idEndereco = idEndereco,
//        foto = foto,
//        dataNascimento = dataNascimento,
//        logradouro = logradouro,
//        bairro = bairro,
//        cidade = cidade,
//        estado = estado,
//        cpf = cpf,
//        numero = numero,
//        genero = genero
    )

    return PacienteRepository(context).save(newPaciente)
}