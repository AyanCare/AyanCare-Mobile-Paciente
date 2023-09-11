package br.senai.sp.jandira.ayancare_frontmobile.screens.finalizarCadastro.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile.components.Wave
import br.senai.sp.jandira.ayancare_frontmobile.components.SocialMedia
import br.senai.sp.jandira.ayancare_frontmobile.components.TextFieldPassword
import br.senai.sp.jandira.ayancare_frontmobile.screens.cadastro.components.ProgressBar

@Composable
fun FinalizarCadastroScreen(){

    Surface (
        color = Color(248, 240, 236)
    ) {
        Wave()
        Column (
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Finalizar Cadastro",
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
                Text(
                    text = "Preencha os dados restantes para finalizar seu cadastro.",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF9E8BC1),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Column (
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Box(
                        modifier = Modifier.size(100.dp),
                        contentAlignment = Alignment.BottomEnd
                    ){
                        Card(
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.Center),
                            shape = CircleShape,
                            border = BorderStroke(
                                width = 2.dp,
                                Brush.horizontalGradient(
                                    listOf(
                                        colorResource(id = R.color.purple_200),
                                        Color.White
                                    )
                                )
                            )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.google),//painter,
                                contentDescription = "",
                                //colorFilter = ColorFilter.tint(colorResource(id = R.color.pink_login)),
                                modifier = Modifier.size(64.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Image(
                            painter = painterResource(
                                id = R.drawable.baseline_camera_alt_24
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .align(alignment = Alignment.BottomEnd)
                                .offset(x = 3.dp, y = 3.dp)
                                .size(30.dp)
                                .clickable {
                                    //launcher.launch("image/*")
                                },
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    DefaultTextField(valor = "", label = "Nome") {}

                    Spacer(modifier = Modifier.height(25.dp))

                    DefaultTextField(valor = "", label = "Sobrenome") {}

                    Spacer(modifier = Modifier.height(25.dp))

                    DefaultTextField(valor = "", label = "Data de Nascimento") {}

                    Spacer(modifier = Modifier.height(25.dp))

                    DefaultTextField(valor = "", label = "Genêro") {}

                }
            }


            Column (
                modifier = Modifier.width(190.dp)
            ) {
                DefaultButton(text = "Finalizar") {}
            }
            ProgressBar(text = "3 / 3", valor = 330)

        }
    }

}

@Preview
@Composable
fun FinalizarCadastroPreview() {
    FinalizarCadastroScreen()
}