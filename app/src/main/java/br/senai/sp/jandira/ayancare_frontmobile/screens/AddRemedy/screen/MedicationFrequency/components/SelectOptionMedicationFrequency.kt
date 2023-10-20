package br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.MedicationFrequency.components

//SelectOptionMedicationFrequency

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import java.security.cert.CertStore

@Composable
fun SelectOptionMedicationFrequency(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier,
    localStorage: Storage
) {
    var context = LocalContext.current

    var selectedValue by rememberSaveable { mutableStateOf("") }

    val isSelect = localStorage.lerValor(context, "jeito_medicamento")

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        options.forEach { item ->
            Row(
                modifier = Modifier
                    .selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000)
                )
                Spacer(modifier = Modifier.height(30.dp))
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            if (isSelect == "Intervalos fixos (8 em 8h, 6 em 6h...)"){
                Text(text = "ksdkfhsvd")
            }else if (isSelect == "Horários livres (Uma vez por dia,\nduas vezes....)"){

            }
        }
    }
}