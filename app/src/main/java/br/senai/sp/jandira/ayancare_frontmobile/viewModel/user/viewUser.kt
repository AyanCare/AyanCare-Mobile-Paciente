package br.senai.sp.jandira.ayancare_frontmobile.viewModel.user

import androidx.lifecycle.ViewModel

class CreateAccountView: ViewModel() {
    var id: Int = 0
    var nome: String? = ""
    var dataNascimento: String? = ""
    var email: String? = ""
    var senha: String? = ""
    var genero: String = ""
}