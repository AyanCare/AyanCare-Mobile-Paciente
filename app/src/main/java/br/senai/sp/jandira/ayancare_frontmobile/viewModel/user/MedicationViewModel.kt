package br.senai.sp.jandira.ayancare_frontmobile.viewModel.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicationViewModel : ViewModel() {

    private var medicationName: String? = null

    fun setMedicationName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            medicationName = name
        }
    }

    fun getMedicationName(): String? {
        return medicationName
    }
}