package br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.Medicamentos

data class MedicamentosResponse(
    val status: Int,
    val medicamentos: List<Medicamentos>
)
