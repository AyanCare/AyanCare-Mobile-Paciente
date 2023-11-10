package br.senai.sp.jandira.ayancare_frontmobile.screens.esquecerSenha.redefinirSenha.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile.components.Wave

@Composable
fun RedefinirSenhaScreen(navController: NavController) {
    Surface(
        color = Color(248, 240, 236)
    ) {
        Wave()
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Redefinir Senha",
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),

                    )
                Text(
                    text = "Digite sua nova senha",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF9A9A9A),
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(100.dp))

            Column (
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
            ){
                DefaultTextField(valor = "", label = "Nova Senha", onValueChange = {}) {}

                Spacer(modifier = Modifier.height(50.dp))

                DefaultTextField(valor = "", label = "Confirmação de Senha", onValueChange = {}) {}
            }

            Spacer(modifier = Modifier.height(200.dp))

            DefaultButton(text = "Enviar", onClick = {
                navController.navigate("login_screen")
            })
        }
    }
}
