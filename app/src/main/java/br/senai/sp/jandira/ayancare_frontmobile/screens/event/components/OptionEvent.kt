package br.senai.sp.jandira.ayancare_frontmobile.screens.event.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DateTextField
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile.screens.finalizarCadastro.components.Dropdown

@Composable
fun OptionEvent() {

    var descricaoState by remember {
        mutableStateOf("")
    }

    Column {

        Column {
            Text(
                text = "Título",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = Color(0xFF191D23)
            )
            DefaultTextField(
                valor = "",
                label = "Nome do Local",
                onValueChange = {},
                aoMudar = {}
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Paciente",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = Color(0xFF191D23)
            )

            Dropdown()

            Spacer(modifier = Modifier.height(40.dp))

            Column {
                Text(
                    text = "Descrição",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    color = Color(0xFF191D23)
                )
                TextField(
                    value = descricaoState,
                    onValueChange = { descricaoState = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .border(
                            width = 1.dp,
                            color = Color(167, 165, 164),
                            shape = RoundedCornerShape(4.dp)
                        ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(248, 240, 236)
                    )
                )
            }

        }


    }

}

@Preview
@Composable
fun ds() {
    OptionEvent()
}