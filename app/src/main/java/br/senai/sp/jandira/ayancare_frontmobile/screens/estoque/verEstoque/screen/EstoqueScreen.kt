package br.senai.sp.jandira.ayancare_frontmobile.screens.estoque.verEstoque.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.MedicamentosResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.Medicamentos
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile.screens.estoque.verEstoque.components.CardEstoque
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun EstoqueScreen(
    navController: NavController,
    navRotasController: NavController,
    localStorage: Storage
) {

    val context = LocalContext.current

    val array = PacienteRepository(context = context).findUsers()

    val paciente = array[0]
    var id = paciente.id.toLong()

    var listEstoque by remember {
        mutableStateOf(
            listOf(
                Medicamentos(0, "", 0, "", 0, 0, 0, "")
            )
        )
    }

    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getAlarme().getMedicamentosByIdPaciente(id.toInt())

    call.enqueue(object : Callback<MedicamentosResponse> {
        override fun onResponse(
            call: Call<MedicamentosResponse>,
            response: Response<MedicamentosResponse>
        ) {
            Log.i("teste de conexao", "onResponse: ${response.body()}")
            if (response.body()!!.status == 404) {
                Log.e("TAG", "a resposta está nula")
                listEstoque = emptyList()
            } else {
                listEstoque = response.body()!!.medicamentos
            }
        }
        override fun onFailure(call: Call<MedicamentosResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }

    })


    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            //verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 30.dp, start = 15.dp, end = 15.dp, bottom = 80.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Estoque de \nRemédio",
                fontSize = 36.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )
            Row (
                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
            ){
                DefaultTextField(
                    valor = "",
                    label = "Pesquisa",
                    aoMudar = {},
                    onValueChange = {}
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(listEstoque) {
                    CardEstoque(
                        nome_remedio = "${it.nome}",
                        quantidade = it.quantidade,
                        onClick = {
                            localStorage.salvarValor(context, it.nome, "nome_estoque")
                            localStorage.salvarValor(context, it.id_medicamento.toString(), "id_estoque")
                            navRotasController.navigate("modify_stock_screen")
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}