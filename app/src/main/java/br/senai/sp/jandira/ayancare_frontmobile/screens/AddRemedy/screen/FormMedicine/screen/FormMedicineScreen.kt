package br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.FormMedicine.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.medidas.MedidasResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.medidas.service.Medidas
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository.MedicamentoRepository
import br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.FormMedicine.components.SelectOptionFormMedicine
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun FormMedicineScreen(
    navController: NavController,
    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope
) {
    var context = LocalContext.current

    val nome = localStorage.lerValor(context, "nome_medicamento")
    Log.e("nome", "FormMedicineScreen: $nome")

    val array = PacienteRepository(context = context).findUsers()
    val paciente = array[0]
    var id = paciente.id.toLong()

    var listMedidas by remember {
        mutableStateOf<List<Medidas>>(emptyList())
    }

    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getMedidas().getMedidas()

    call.enqueue(object : Callback<MedidasResponse> {
        override fun onResponse(
            call: Call<MedidasResponse>,
            response: Response<MedidasResponse>
        ) {
            //Log.e("TAG", "onResponse:${response.body()} ")
            listMedidas = response.body()!!.medidas
            //Log.e("TAG", "onResponse:$listMedidas")
        }

        override fun onFailure(call: Call<MedidasResponse>, t: Throwable) {
            Log.i("medidas", "onFailure: ${t.message}")
        }

    })

    val selectedOptions = remember { mutableStateListOf<Boolean>() }
    selectedOptions.addAll(List(listMedidas.size) { false })

    val optionIdMap = listMedidas.associateBy { it.tipo }
    var isSelectState by remember { mutableStateOf("") }
    var selectedId by rememberSaveable { mutableStateOf(0) }

    fun medicamento(
        nome: String,
        quantidade: Int,
        data_validade: String,
        estocado: Int,
        id_paciente: Int,
        id_medida: Int
    ) {
        val medicamentoRepository = MedicamentoRepository()
        lifecycleScope.launch {

            val response = medicamentoRepository.registerMedicamento(
                nome,
                quantidade,
                data_validade,
                estocado,
                id_paciente,
                id_medida
            )

            if (response.isSuccessful) {
                Log.e(MainActivity::class.java.simpleName, "medicamento bem-sucedido")
                //Log.e("medicamento", "medicamento: ${response.body()}")
                val checagem = response.body()?.get("status")

                val jsonString = response.body().toString()
                val jsonObject = JSONObject(jsonString)
                val medicamentoObject = jsonObject.getJSONObject("medicamento")

                val id = medicamentoObject.getInt("id")

                localStorage.salvarValor(context, id.toString(), "id_medicamento")

                if (checagem.toString() == "404") {
                    Toast.makeText(context, "algo está invalido", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Sucesso!!", Toast.LENGTH_SHORT).show()
                    navController.navigate("medication_frenquency_screen")
                }
            } else {
                val errorBody = response.errorBody()?.string()

                Log.e(MainActivity::class.java.simpleName, "Erro durante o medicamento: $errorBody")
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
            //verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.medicamento),
                contentDescription = "",
                modifier = Modifier
                    .size(71.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Qual é a forma deste \n medicamento?",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = Color(0xFF35225F),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = nome.toString().uppercase(),
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = Color(0xFF9986BD)
            )
            SelectOptionFormMedicine(
                options = optionIdMap,
                onSelectionChanged = { value, id ->
                    isSelectState = value
                    selectedId = id
                }
            )
            Spacer(modifier = Modifier.height(90.dp))
            DefaultButton(
                onClick = {
                    if (isSelectState.isEmpty()) {
                        Toast.makeText(context, "Selecione a medida", Toast.LENGTH_LONG).show()
                    } else {
                        localStorage.salvarValor(context, isSelectState, "medida_medicamento")

                        medicamento(
                            nome = "${nome!!.uppercase()}",
                            quantidade = 5,
                            data_validade = "2003-03-03",
                            estocado = 1,
                            id_paciente = id.toInt(),
                            id_medida = selectedId
                        )
                    }
                },
                text = "Próximo"
            )
        }
    }
}
