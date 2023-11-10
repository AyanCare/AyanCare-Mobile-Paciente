package br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.AddStock.screen

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton

@Composable
fun AddStockScreen(
    navController: NavController
) {
    var quantidadeState by remember {
        mutableStateOf("")
    }

    var limiteState by remember {
        mutableStateOf("")
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
                    OutlinedTextField(
                        value = quantidadeState ,
                        onValueChange = {
                                        quantidadeState = it
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
                        value = limiteState ,
                        onValueChange = {
                                        limiteState = it
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
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                DefaultButton(
                    onClick = {
                        //navController.navigate("main_screen")
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
