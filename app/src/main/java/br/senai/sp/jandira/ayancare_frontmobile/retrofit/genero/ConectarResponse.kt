package br.senai.sp.jandira.ayancare_frontmobile.retrofit.genero

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.genero.service.Genero

data class ConectarResponse(
    val status: Int,
    val quantidade: Int,
    val pacientes: List<Genero>
)
