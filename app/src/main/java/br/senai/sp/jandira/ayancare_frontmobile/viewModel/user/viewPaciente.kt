package br.senai.sp.jandira.ayancare_frontmobile.viewModel.user

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.ComorbidadesList
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.DoencasCronicasList


class PacienteView: ViewModel() {
    var id: Int? = null
    var nome: String? = ""
    var data_nascimento: String? = ""
    var email: String? = ""
    var senha: String? = ""
    var cpf: String? = ""
    var foto: String? = ""
    var genero: String = ""
    var doencas_cronicas: MutableList<DoencasCronicasList> = mutableListOf()
    var comorbidades: MutableList<ComorbidadesList> = mutableListOf()
}