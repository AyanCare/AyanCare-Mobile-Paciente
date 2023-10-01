package br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service

import java.util.Date

data class PatientList(
    val id: Int,
    val nome: String,
    val data_nascimento: String,
    val email: String,
    val senha: String,
    val cpf: String,
    val foto: String,
    val historico_medico: String,
    val doencas_cronicas: List<DoencasCronicasList>,
    val comorbidades: List<ComorbidadesList>
)
