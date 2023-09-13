package br.senai.sp.jandira.ayancare.screen.screenEstoque

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.ayancare.R
import br.senai.sp.jandira.ayancare.componentes.ButtonPadrao
import br.senai.sp.jandira.ayancare.componentes.CustomRadioButton
import br.senai.sp.jandira.ayancare.componentes.screen.screenAdicionarRemedio.TelaAdicionarRemedio


@Composable
fun Estoque(){

    val options = listOf(
        "Comprimido",
        "Gota",
        "Grama" ,
        "Mililitro" ,
        "Unidades" ,
        "Injeção",
        "Aplicação"
    )
    val selectedOptions = remember { mutableStateListOf<Boolean>() }
    selectedOptions.addAll(List(options.size) { false })



    Surface(
        modifier = Modifier
            .fillMaxSize(),

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.Background_color))
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
                        painter = painterResource(id = R.drawable.medicamento) ,
                        contentDescription ="",
                        modifier = Modifier
                            .size(width = 300.dp, height = 300.dp)
                            .fillMaxWidth()
                    )

                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = stringResource(id = R.string.forma_medicamento),
                    color = colorResource(id = R.color.black)
                )

                Text(
                    text = stringResource(id = R.string.medicamento),
                    color = colorResource(id = R.color.black)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Amoxilina")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {


                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    options.forEachIndexed { index, option ->
                        CustomRadioButton(
                            text = option,
                            isSelected = selectedOptions[index],
                            onSelected = { newValue ->
                                selectedOptions[index] = newValue
                            }
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(90.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ButtonPadrao(
                    text = stringResource(id = R.string.proximo),
                    onClick = {}
                )
            }



        }

    }
}


@Preview
@Composable
fun ScreenAdicionarRemedioPreview(){

    Estoque()
}



