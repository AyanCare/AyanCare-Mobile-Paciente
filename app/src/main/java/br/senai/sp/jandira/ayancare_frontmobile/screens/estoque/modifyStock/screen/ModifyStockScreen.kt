package br.senai.sp.jandira.ayancare_frontmobile.screens.estoque.modifyStock.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.MedicamentoResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.Medicamento
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.Medicamentos
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository.MedicamentoRepository
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ModifyStockScreen(
    navController: NavController,
    navRotasController: NavController,
    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope
) {

    val context = LocalContext.current


    val id_estoque = localStorage.lerValor(context, "id_estoque")
    val nome_estoque = localStorage.lerValor(context, "nome_estoque")

    var quantidadeState by remember {
        mutableStateOf("")
    }

    var limiteState by remember {
        mutableStateOf("")
    }

    Log.i("id_estoque", "ModifyStockScreen:$id_estoque ")

    var listEstoque by remember {
        mutableStateOf(
                Medicamento(0, "", 0, "", 0, 0, 0, "")
        )
    }

    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getAlarme().getMedicamentosById(id_estoque!!.toInt())

    call.enqueue(object : Callback<MedicamentoResponse> {
        override fun onResponse(
            call: Call<MedicamentoResponse>,
            response: Response<MedicamentoResponse>
        ) {
            Log.i("teste de conexao", "onResponse: ${response.body()}")
            if (response.body()!!.status == 404) {
                Log.e("TAG", "a resposta está nula")
                //listEstoque = emptyList()
            } else {
                listEstoque = response.body()!!.medicamento

                quantidadeState = listEstoque.quantidade.toString()
                limiteState = listEstoque.limite.toString()
            }
        }
        override fun onFailure(call: Call<MedicamentoResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }

    })

    fun updateEstoque(
        quantidade: Int,
        limite: Int,
        id_medicamento: Int
    ) {
        val EstoqueRepository = MedicamentoRepository()
        lifecycleScope.launch {

            val response = EstoqueRepository.updateMedicamento(
                quantidade,
                limite,
                id_medicamento
            )

            Log.e("response", "medicamneto: $response")

            if (response.isSuccessful) {

                Log.d(MainActivity::class.java.simpleName, "Registro bem-sucedido")

                navRotasController.navigate("main_screen")

            } else {

                val errorBody = response.errorBody()?.string()
                Log.e(MainActivity::class.java.simpleName, "Erro durante o registro: $errorBody")
                Toast.makeText(context, "Erro durante o registro", Toast.LENGTH_SHORT).show()

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
                .padding(top = 50.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
                //verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$nome_estoque",
                    fontSize = 36.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Column (){
                Text(
                    text = "ESTOQUE ATUAL",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Quantidade",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000)
                    )
                    OutlinedTextField(
                        value = quantidadeState,
                        onValueChange = {
                            //quantidadeState = it
                            if (it.length <= 5) {
                                quantidadeState = it
                            }
                        },
                        modifier = Modifier
                            .width(160.dp)
                            .height(50.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )

                }

                Spacer(modifier = Modifier.height(54.dp))

                Text(
                    text = "DEFINIÇÃO DE LIMITE",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Limite",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000)
                    )
                    OutlinedTextField(
                        value = limiteState,
                        onValueChange = {
                            //limiteState = it
                            if (it.length <= 5) {
                                limiteState = it
                            }
                        },
                        modifier = Modifier
                            .width(160.dp)
                            .height(50.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            }
            Column (
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ){
                DefaultButton(
                    onClick = {
                        Log.e("teste muryllo", "ModifyStockScreen: ${quantidadeState.toInt()}," +
                                " ${limiteState.toInt()}, ${id_estoque.toInt()}", )
                        updateEstoque(
                            quantidadeState.toInt(),
                            limiteState.toInt(),
                            id_estoque.toInt()
                        )
                    },
                    text = "Renovar Estoque"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Cancelar",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        navRotasController.popBackStack()
                    }
                )
            }
        }
    }
}