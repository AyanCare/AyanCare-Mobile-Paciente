package br.senai.sp.jandira.ayancare_frontmobile.screens.event.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
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

@Composable
fun ButtonCancel_CreateEvent(
    navController: NavController,
    doneNavController: String
) {

    Row {
        Button(
            onClick = {
                      navController.popBackStack()
            },
            colors = buttonColors(Color(0xFFE7EAEE))
        ) {
            Text(
                text = "Cancelar",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = Color(0xFF64748B),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp)
            )
        
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(
            onClick = {
                navController.navigate("$doneNavController")
            },
            colors = buttonColors(Color(0xFF35225F))
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "",
                tint = Color(0xFFFFFFFF)
            )
            //Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Criar Evento",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 6.dp)
                    .fillMaxWidth()
            )

        }
    }

}

//@Preview
//@Composable
//fun cd() {
//    ButtonCancel_CreateEvent()
//}