package br.senai.sp.jandira.ayancare.componentes.screen.screenAdicionarRemedio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile.components.Wave


@Composable
fun TelaAdicionarRemedio() {

    var textRemedio by remember {

        mutableStateOf("")
    }



    Surface(
        color = Color(248, 240, 236)
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),

                ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(5.dp),
                    verticalArrangement = Arrangement.Center

                ) {
                    Row {

                        IconButton(
                            onClick = { /*TODO*/ },


                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                                contentDescription = ""
                            )

                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.remedios),
                            contentDescription = "",
                            modifier = Modifier
                                .size(width = 200.dp, height = 200.dp)
                                .fillMaxWidth()
                        )

                    }
                }
                Spacer(modifier = Modifier.height(30.dp))

                Column(
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Text(
                        text = stringResource(id = R.string.nome_remedio)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    DefaultTextField(
                        label = "Nome do Medicamento",
                        valor = textRemedio,
                        aoMudar = {
                            textRemedio
                        }

                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        DefaultButton(text = stringResource(id = R.string.Next)) {

                        }
                    }

                }


            }


        }

    }
}

@Preview
@Composable
fun ScreenAdicionarRemedioPreview(){

 TelaAdicionarRemedio()
}