package br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.AddStock.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.CustomTextFieldNumber
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository.MedicamentoRepository
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStockScreen(
    navController: NavController,
    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope
) {

    var context = LocalContext.current

    var id_estoque = localStorage.lerValor(context, "id_medicamento")

    var quantidadeState by remember {
        mutableStateOf("")
    }

    var limiteState by remember {
        mutableStateOf("")
    }

    //VALIDAÇÕES
    var validateQuantidade by rememberSaveable {
        mutableStateOf(true)
    }
    var validateLimite by rememberSaveable {
        mutableStateOf(true)
    }

    val validateQuantidadeError = "Quantidade está em branco"
    val validateLimiteError = "Limite está em branco"

    fun validateData(): Boolean {
        val quantidadeText = quantidadeState.trim()
        val limiteText = limiteState.trim()

        validateQuantidade = quantidadeText.isNotEmpty() && quantidadeText.all { it.isDigit() }
        validateLimite = limiteText.isNotEmpty() && limiteText.all { it.isDigit() }

        return validateQuantidade && validateLimite
    }


//    fun updateEstoque(
//        quantidade: Int,
//        limite: Int,
//        id_medicamento: Int
//    ) {
//        if (validateData()){
//            val EstoqueRepository = MedicamentoRepository()
//            lifecycleScope.launch {
//
//                val response = EstoqueRepository.updateMedicamento(
//                    quantidade,
//                    limite,
//                    id_medicamento
//                )
//
//                Log.e("response", "medicamneto: $response")
//
//                if (response.isSuccessful) {
//
//                    Log.d(MainActivity::class.java.simpleName, "Registro bem-sucedido")
//
//                    navController.navigate("main_screen")
//
//                } else {
//
//                    val errorBody = response.errorBody()?.string()
//                    Log.e(MainActivity::class.java.simpleName, "Erro durante o registro: $errorBody")
//                    Toast.makeText(context, "Erro durante o registro", Toast.LENGTH_SHORT).show()
//
//                }
//            }
//        }else{
//            Toast.makeText(context, "Por favor, reolhe suas caixas de texto", Toast.LENGTH_SHORT).show()
//        }
//
//    }

    fun updateEstoque(
        quantidade: Int,
        limite: Int,
        id_medicamento: Int
    ) {
        if (validateData()) {
            val EstoqueRepository = MedicamentoRepository()
            lifecycleScope.launch {
                val response = EstoqueRepository.updateMedicamento(
                    quantidade,
                    limite,
                    id_medicamento
                )

                Log.e("response", "medicamento: $response")

                if (response.isSuccessful) {
                    Log.d(MainActivity::class.java.simpleName, "Registro bem-sucedido")
                    navController.navigate("main_screen")
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e(MainActivity::class.java.simpleName, "Erro durante o registro: $errorBody")
                    Toast.makeText(context, "Erro durante o registro", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Por favor, preencha todas as caixas de texto corretamente", Toast.LENGTH_SHORT).show()
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
                Image(
                    painter = painterResource(id = R.drawable.relogio),
                    contentDescription = "",
                    modifier = Modifier
                        .size(71.dp)
                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "GOSTARIA DE RECEBER LEMBRETES \nPARA REPOR O ESTOQUE DE REMÉDIOS ?",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center
                )
            }
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
                    CustomTextFieldNumber(
                        value = quantidadeState,
                        aoMudar = {
                            quantidadeState = it
                        },
                        label = "",
                        showError = !validateQuantidade,
                        errorMessage = validateQuantidadeError
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
                    CustomTextFieldNumber(
                        value = limiteState,
                        aoMudar = {
                            limiteState = it
                        },
                        label = "",
                        showError = !validateLimite,
                        errorMessage = validateLimiteError
                    )
                }
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                DefaultButton(
                    onClick = {
                        updateEstoque(
                            quantidadeState.toInt(),
                            limiteState.toInt(),
                            id_estoque!!.toInt()
                        )
                    },
                    text = "Proximo"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Pular",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        navController.navigate("main_screen")
                    }
                )
            }
        }
    }
}
