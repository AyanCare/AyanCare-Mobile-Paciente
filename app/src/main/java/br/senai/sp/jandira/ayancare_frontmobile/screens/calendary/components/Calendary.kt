package br.senai.sp.jandira.ayancare_frontmobile.screens.calendary.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun Calendary() {
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }
    val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

    // Variáveis para rastrear a data selecionada
    var selectedYear by remember { mutableIntStateOf(selectedDate.get(Calendar.YEAR)) }
    var selectedMonth by remember { mutableIntStateOf(selectedDate.get(Calendar.MONTH) + 1) }
    var selectedDayOfWeek by remember { mutableStateOf("") }

    // Define um Locale para o idioma português
    val ptBrLocale = Locale("pt", "BR")

    Column(
        modifier = Modifier
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color(0, 0, 0),
                        Color(53, 34, 95),
                        //Color(0, 0, 0)
                    )
                ),
                shape = RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp)
            )
    ) {
        // Parte superior: nome do mês, ano e botões de navegação
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth(),
            //backgroundColor = Color(53, 34, 95)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = dateFormat.format(selectedDate.time).toUpperCase(),
                    fontSize = 24.sp,
                    //fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFF9F1ED),
                    textAlign = TextAlign.Center
                )

                Row {
                    IconButton(onClick = {
                        selectedDate.add(Calendar.MONTH, -1)
                        selectedYear = selectedDate.get(Calendar.YEAR)
                        selectedMonth = selectedDate.get(Calendar.MONTH) + 1
                    }) {
                        Icon(
                            Icons.Default.ArrowBackIos,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        selectedDate.add(Calendar.MONTH, 1)
                        selectedYear = selectedDate.get(Calendar.YEAR)
                        selectedMonth = selectedDate.get(Calendar.MONTH) + 1
                    }) {
                        Icon(
                            Icons.Default.ArrowForwardIos,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            TextEditCalendary("D")
            TextEditCalendary("S")
            TextEditCalendary("T")
            TextEditCalendary("Q")
            TextEditCalendary("Q")
            TextEditCalendary("S")
            TextEditCalendary("S")
        }

        // Parte do meio: LazyColumn com os dias do mês em blocos de 7
        val calendar = selectedDate.clone() as Calendar
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        val daysPerBlock = 7
        val numBlocks = (maxDays + daysPerBlock - 1) / daysPerBlock

        for (blockIndex in 0 until numBlocks) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (dayIndex in 0 until 7) {
                    val day = blockIndex * 7 + dayIndex - calendar.get(Calendar.DAY_OF_WEEK) + 2
                    if (day in 1..maxDays) {
                        val isSelected = selectedDate.get(Calendar.DAY_OF_MONTH) == day
                        if (isSelected) {
                            Card(
                                modifier = Modifier
                                    .size(45.dp),
                                backgroundColor = if (isSelected) Color(0xFFF9F1ED) else Color.Transparent,
                                shape = CircleShape
                            ) {
                                Text(
                                    text = day.toString(),
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        //.weight(1f)
                                        .padding(10.dp)
                                        .clickable {
                                            selectedDate.set(Calendar.DAY_OF_MONTH, day)
                                            selectedYear = selectedDate.get(Calendar.YEAR)
                                            selectedMonth = selectedDate.get(Calendar.MONTH) + 1
                                            selectedDayOfWeek =
                                                SimpleDateFormat(
                                                    "E",
                                                    ptBrLocale
                                                ).format(selectedDate.time)
                                        },
                                    textAlign = TextAlign.Center,
                                    color = if (isSelected) Color(0xFF35225F) else Color(0xFFF9F1ED)
                                )
                            }

                        } else {
                            Text(
                                text = day.toString(),
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(10.dp)
                                    .clickable {
                                        selectedDate.set(Calendar.DAY_OF_MONTH, day)
                                        selectedYear = selectedDate.get(Calendar.YEAR)
                                        selectedMonth = selectedDate.get(Calendar.MONTH) + 1
                                        selectedDayOfWeek =
                                            SimpleDateFormat(
                                                "E",
                                                ptBrLocale
                                            ).format(selectedDate.time)
                                    }
                                    .background(
                                        shape = CircleShape,
                                        color = if (isSelected) Color.Cyan else Color.Transparent,
                                        //border = if (isSelected) BorderStroke(2.dp, Color.White) else null
                                    )
                                    .size(30.dp), // Tamanho do círculo
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }

                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
        //não mexa nesse log
        Log.e("Calendary",
            "Calendary: " +
                    "Data selecionada: ${selectedDate.get(Calendar.DAY_OF_MONTH)}/$selectedMonth/$selectedYear\\n" +
                    "Ano: $selectedYear\n" +
                    "Mês: $selectedMonth\n" +
                    "Dia da semana: $selectedDayOfWeek"
        )
    }

}




