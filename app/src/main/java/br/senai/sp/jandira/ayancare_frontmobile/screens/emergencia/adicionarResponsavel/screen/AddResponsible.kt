package br.senai.sp.jandira.ayancare_frontmobile.screens.emergencia.adicionarResponsavel.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile.screens.emergencia.adicionarResponsavel.components.PhoneNumberTextField

@Composable
fun AddResponsibleScreen(navController: NavController) {


    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            //verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.PersonAddAlt1,
                contentDescription = "add responsible",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Adicionar Contato",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            DefaultTextField(
                valor = "",
                label = "Nome",
                onValueChange = {},
                aoMudar = {}
            )
            Spacer(modifier = Modifier.height(50.dp))
            DefaultTextField(
                valor = "",
                label = "Telefone",
                onValueChange = {},
                aoMudar = {}
            )
//            OutlinedTextField(
//                value = "",
//                onValueChange = {},
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(4.dp),
//                label = {
//                    Text(
//                        text = "Telefone"
//                    )
//                },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                visualTransformation = VisualTransformation.None
//            )
            PhoneNumberTextField()


            Column (
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                DefaultButton(
                    onClick = { /*TODO*/ },
                    text = "Adicionar"
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Cancelar",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline
                )
            }


        }
    }

}



//@Preview
//@Composable
//fun AddResponsibleScreenPreview() {
//    AddResponsibleScreen()
//}

