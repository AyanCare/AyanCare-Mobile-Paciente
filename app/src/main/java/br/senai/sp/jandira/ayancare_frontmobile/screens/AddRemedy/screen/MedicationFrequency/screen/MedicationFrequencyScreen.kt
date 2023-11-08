package br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.MedicationFrequency.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.MedicationFrequency.components.SelectOptionMedicationFrequency
import br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.MedicationFrequency.components.configureRepeatingNotification
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.criacaoTabela.AlarmeTbl
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.alarmeRepository
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar

@Composable
fun MedicationFrequencyScreen(
    navController: NavController,
    localStorage: Storage
) {
    var context = LocalContext.current
    val data = LocalDate.now()

    var isSelectState by remember {
        mutableStateOf("")
    }

    var selectedTime by remember { mutableStateOf(Calendar.getInstance()) }

    val options = listOf(
        "Intervalos fixos (8 em 8h, 6 em 6h...)",
        "Horários livres (Uma vez por dia,\nduas vezes....)",
        "Somente quando necessário"
    )
    val selectedOptions = remember { mutableStateListOf<Boolean>() }
    selectedOptions.addAll(List(options.size) { false })

    val nome = localStorage.lerValor(context, "nome_medicamento")
    val medida = localStorage.lerValor(context, "medida_medicamento")

    Surface(
        color = Color(248, 240, 236)
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier.size(30.dp),
                tint = Color(0xFF35225F)
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 60.dp)
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.calendario),
                    contentDescription = "calendario",
                    modifier = Modifier
                        .size(71.dp)
                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Com que frequência você toma \neste medicamento? ",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                ) {
                    Text(
                        text = nome.toString(),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF35225F)
                    )
                    Text(
                        text = medida.toString(),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF35225F)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                SelectOptionMedicationFrequency(
                    options = options,
                    onSelectionChanged = {
                        isSelectState = it
                    },
                    localStorage = localStorage,
                    selectedTime = selectedTime
                )
                localStorage.salvarValor(context, isSelectState, "jeito_medicamento")
                Log.e("tag", "MedicationFrequencyScreen: $isSelectState")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultButton(
                onClick = {

                    val id_intervalo = localStorage.lerValor(context, "id_intervalo")

                    configureRepeatingNotification(context, selectedTime, id_intervalo!!.toInt())

                    val hora = selectedTime.get(Calendar.HOUR_OF_DAY)
                    val minutos = selectedTime.get(Calendar.MINUTE)

                    val alarme = AlarmeTbl(
                        dia = data.toString(),
                        horario = formatTime(selectedTime),
                        intervalo = id_intervalo.toInt(),
                        selectedHour = hora,
                        selectedMinute = minutos
                    )

                    Log.e("TAG", "MedicationFrequencyScreen: $alarme", )

                    val alarmeId = alarmeRepository(context).saveAlarm(alarme)

                    navController.navigate("add_stock_screen")
                },
                text = "Proximo"
            )
        }
    }
}

@SuppressLint("SimpleDateFormat")
private fun formatTime(calendar: Calendar): String {
    val sdf = SimpleDateFormat("HH:mm")
    return sdf.format(calendar.time)
}