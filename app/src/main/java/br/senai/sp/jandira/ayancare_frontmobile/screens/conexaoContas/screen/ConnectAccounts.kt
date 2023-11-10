package br.senai.sp.jandira.ayancare_frontmobile.screens.conexaoContas.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.components.Wave

@Composable
fun ConnectAccounts() {
    Surface(
        color = Color(248, 240, 236)
    ) {
        Wave()
        Column(
            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 80.dp)
                .fillMaxSize()
        ) {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = ""
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                //verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp)
            ) {
                Text(
                    text = "Conectar contas",
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Digite o código de conexão\nde contas do paciente",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF9986BD),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(100.dp))

                TextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFF9986BD),
                            shape = RoundedCornerShape(size = 4.dp)
                        )
                        .padding(0.5.dp)
                        .width(200.dp)
                        .height(50.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 4.dp)
                        ),
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Adicionar codigo",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFFAFAFAF),
                            textAlign = TextAlign.Center
                        )

                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center // Centraliza o texto horizontalmente
                    )
                )

                Spacer(modifier = Modifier.height(100.dp))

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 60.dp, end = 60.dp)
                ){
                    DefaultButton(
                        onClick = { /*TODO*/ },
                        text = "Conectar"
                    )
                }
            }
        }
    }
}
