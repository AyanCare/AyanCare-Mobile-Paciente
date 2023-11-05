package br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.MedicationFrequency.components

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownInterval(
    selectValue: String,
    onValueChange: (String) -> Unit,
    localStorage: Storage
) {
    var context = LocalContext.current
    var isExpanded by remember {
        mutableStateOf(false)
    }

    val options = listOf(
        Pair("1x ao dia (de 24 em 24 horas)", "1"),
        Pair("2x ao dia (de 12 em 12 horas)", "2"),
        Pair("3x ao dia (de 8 em 8 horas)", "3"),
        Pair("4x ao dia (de 6 em 6 horas)", "4"),
        Pair("6x ao dia (de 4 em 4 horas)", "6"),
        Pair("12x ao dia (de 2 em 2 horas)", "12"),
        Pair("24x ao dia (de 1 em 1 horas)", "24")
    )

    var value = selectValue

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it}
        ) {

            TextField(
                value = value,
                onValueChange = {
                                value = it
                },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                options.forEach {(displayText, dataValue) ->
                    DropdownMenuItem(
                        text = { Text(text = displayText, color = Color.Black) },
                        onClick = {
                            localStorage.salvarValor(context, dataValue, "id_intervalo")
                            value = displayText
                            onValueChange(displayText)
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}
