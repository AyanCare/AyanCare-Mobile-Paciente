package br.senai.sp.jandira.ayancare_frontmobile.retrofit.cuidador

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.cuidador.service.Cuidador


data class CuidadorResponse(
    val status: Int,
    val cuidador: Cuidador
)
