package br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.MedicationFrequency.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository.AlarmeRepository
import br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.MedicationFrequency.components.SelectOptionMedicationFrequency
import br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.MedicationFrequency.components.configureRepeatingNotification
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.criacaoTabela.AlarmeTbl
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.alarmeRepository
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar

@Composable
fun MedicationFrequencyScreen(
    navController: NavController,
    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope
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
    val id_medicamento = localStorage.lerValor(context, "id_medicamento")
    Log.i("id_medicamento_medication", "medicamento: $id_medicamento ")

    fun alarmeUnitario(
        quantidade: Int,
        id_alarme_medicamento: Int,
        horario: String
    ){
        val medicamentoRepository = AlarmeRepository()
        lifecycleScope.launch {

            val response = medicamentoRepository.registerAlarmeUnitario(
                quantidade,
                id_alarme_medicamento,
                horario
            )

            if (response.isSuccessful) {
                Log.e(MainActivity::class.java.simpleName, "alarmeUnitario bem-sucedido")
                Log.e("alarmeUnitario", "alarmeUnitario: ${response.body()}")
                val checagem = response.body()?.get("status")

                if (checagem.toString() == "404") {
                    Toast.makeText(context, "algo está invalido", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Sucesso!!", Toast.LENGTH_SHORT).show()
                    //navController.navigate("add_stock_screen")
                }
            } else {
                val errorBody = response.errorBody()?.string()

                Log.e(MainActivity::class.java.simpleName, "Erro durante o alarmeUnitario: $errorBody")
                Toast.makeText(context, "algo está invalido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun alarme(
        dia: String,
        intervalo: Int,
        horario: String,
        id_medicamento: Int
    ) {
        val medicamentoRepository = AlarmeRepository()
        lifecycleScope.launch {

            Log.e("Luizão Maldoso", "$dia + $intervalo + $horario + $id_medicamento")

            val response = medicamentoRepository.registerAlarme(
                dia,
                intervalo,
                horario,
                id_medicamento
            )

            if (response.isSuccessful) {
                Log.e(MainActivity::class.java.simpleName, "alarme bem-sucedido")
                Log.e("alarme", "alarme: ${response.body()}")
                val checagem = response.body()?.get("status")

                val jsonString = response.body().toString()
                val jsonObject = JSONObject(jsonString)
                val alarmeObject = jsonObject.getJSONObject("alarme")
                val id = alarmeObject.getInt("id")

                Log.i("id", "alarme: $id")


                if (checagem.toString() == "404") {
                    Toast.makeText(context, "algo está invalido", Toast.LENGTH_LONG).show()
                } else {

                    val id_intervalo = localStorage.lerValor(context, "id_intervalo")
                    Log.i("id_intervalo", "alarme: $id_intervalo")

                    val hora = selectedTime.get(Calendar.HOUR_OF_DAY)
                    val minutos = selectedTime.get(Calendar.MINUTE)
                    Log.i("hora", "hora: $hora")
                    Log.i("minutos", "minutos: $minutos")





                    Log.i("id do retorno do alarme", "alarme: $id")
                    alarmeUnitario(
                        quantidade = 1,
                        id_alarme_medicamento = id,
                        horario = "$hora:$minutos"
                    )



                    Toast.makeText(context, "Sucesso!!", Toast.LENGTH_SHORT).show()
                    navController.navigate("add_stock_screen")
                }
            } else {
                val errorBody = response.errorBody()?.string()

                Log.e(MainActivity::class.java.simpleName, "Erro durante o alarme: ${errorBody.toString()}")
                Log.e(MainActivity::class.java.simpleName, "Erro durante o alarme: $errorBody")
                Toast.makeText(context, "algo está invalido", Toast.LENGTH_SHORT).show()
            }
        }
    }



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
                Log.e("tag", "MedicationFrequencyScreen: $selectedTime")

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

                    var id_intervalo = localStorage.lerValor(context, "id_intervalo")

                    if (id_intervalo == null){
                        id_intervalo = "1"
                    }

                    configureRepeatingNotification(context, selectedTime, id_intervalo!!.toInt())
                    val hora = selectedTime.get(Calendar.HOUR_OF_DAY)
                    val minutos = selectedTime.get(Calendar.MINUTE)
                    Log.e("tag", "MedicationFrequencyScreen: ${formatTime(selectedTime)}")
                    Log.e("tag", "MedicationFrequencyScreen: $hora")
                    Log.e("tag", "MedicationFrequencyScreen: $minutos")

                    val alarme = AlarmeTbl(
                        dia = data.toString(),
                        horario = formatTime(selectedTime),
                        intervalo = id_intervalo!!.toInt(),
                        selectedHour = hora,
                        selectedMinute = minutos
                    )

                    Log.e("Alarme", "MedicationFrequencyScreen: $alarme", )

                    val alarmeId = alarmeRepository(context).saveAlarm(alarme)

                    val array = alarmeRepository(context = context).findAlarm(alarmeId)
                    val alarmes = array[0]

                    alarme(
                        dia = alarme.dia,
                        intervalo = alarme.intervalo,
                        horario = alarme.horario,
                        id_medicamento = id_medicamento!!.toInt()
                    )

                    //navController.navigate("add_stock_screen")
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