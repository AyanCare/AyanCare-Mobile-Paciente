package br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.MedicationFrequency.components

//SelectOptionMedicationFrequency

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.TextFieldNumber
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile.screens.finalizarCadastro.components.DropdownGender

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

    var number by rememberSaveable { mutableStateOf("") }

    var additionalRows by remember { mutableIntStateOf(0) }

    val scrollState = rememberScrollState()

    val isSelect = localStorage.lerValor(context, "jeito_medicamento")

    Column(
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .selectable(
                    selected = selectedValue == options[0],
                    onClick = {
                        selectedValue = options[0]
                        onSelectionChanged(options[0])
                    }
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = options[0],
                fontSize = 15.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000)
            )
            Spacer(modifier = Modifier.height(30.dp))
            RadioButton(
                selected = selectedValue == options[0],
                onClick = {
                    selectedValue = options[0]
                    onSelectionChanged(options[0])
                }
            )
        }
        if (selectedValue == options[0]) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DropdownGender(context = context, gender = "", onValueChange = {})
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TimeMedication(width = 150)
                    Spacer(modifier = Modifier.width(60.dp))
                    TextFieldNumber(
                        valor = number,
                        label = "Quantidade",
                        onValueChange = { number = it }
                    )
                }
            }

        }

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier
                .selectable(
                    selected = selectedValue == options[1],
                    onClick = {
                        selectedValue = options[1]
                        onSelectionChanged(options[1])
                    }
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = options[1],
                fontSize = 15.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000)
            )
            Spacer(modifier = Modifier.height(30.dp))
            RadioButton(
                selected = selectedValue == options[1],
                onClick = {
                    selectedValue = options[1]
                    onSelectionChanged(options[1])
                }
            )
        }
        if (selectedValue == options[1]) {
            if (1 + 1 <= additionalRows) {
                repeat(additionalRows) {
                    AddNewRow(1)
                }
            }
            Text(
                text = "+ Adicionar novo horário",
                fontSize = 15.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF35225F),
                modifier = Modifier.clickable {
                    additionalRows++
                }
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier
                .selectable(
                    selected = selectedValue == options[2],
                    onClick = {
                        selectedValue = options[2]
                        onSelectionChanged(options[2])
                    }
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = options[2],
                fontSize = 15.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000)
            )
            Spacer(modifier = Modifier.height(30.dp))
            RadioButton(
                selected = selectedValue == options[2],
                onClick = {
                    selectedValue = options[2]
                    onSelectionChanged(options[2])
                }
            )
        }
    }
}

@Composable
fun AddNewRow(index: Int) {
    var isRowVisible by remember { mutableStateOf(true) }

    if (isRowVisible) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Novo horário",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF35225F)
                )
                IconButton(
                    onClick = {
                        isRowVisible = false
                    }
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "")
                }
            }

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                TimeMedication(width = 150)
                Spacer(modifier = Modifier.width(60.dp))
                TextFieldNumber(
                    valor = "number",
                    label = "",
                    onValueChange = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun fsdfs() {
    AddNewRow(index = 1)
}