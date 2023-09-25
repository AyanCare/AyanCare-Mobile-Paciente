package br.senai.sp.jandira.ayancare_frontmobile.screens.emergencia.registroEmergencia.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton

@Composable
fun ViewEmergencyScreen() {

    Surface(
        color = Color(214, 69, 69)
    ) {
        Column(
            //verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Ligações de",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center

            )

            Text(
                text = "Emergência",
                fontSize = 48.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4.copy(
                    shadow = Shadow(
                        offset = Offset(10f, 10f),
                        blurRadius = 30f
                    )
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            DefaultButton(onClick = { /*TODO*/ }, text = "Adicionar um novo contato")

        }
    }
}

@Preview
@Composable
fun ViewEmergencyScreenPreview() {
    ViewEmergencyScreen()
}