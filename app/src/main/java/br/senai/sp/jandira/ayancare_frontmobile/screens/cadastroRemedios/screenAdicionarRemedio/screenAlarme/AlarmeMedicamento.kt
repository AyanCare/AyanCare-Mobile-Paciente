package br.senai.sp.jandira.ayancare.screen.screenAlarme

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.ayancare.R
import br.senai.sp.jandira.ayancare.componentes.ButtonPadrao

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Alarme(){

    Surface (
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.relogio) ,
                        contentDescription ="",
                        modifier = Modifier
                            .size(width = 200.dp, height = 200.dp)
                            .fillMaxWidth()
                    )

                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                 modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "GOSTARIA DE RECEBER LEMBRETES ")


                Text(text = "PARA REPOR O ESTOQUE DE REMÉDIOS ?")
            }

            Spacer(modifier = Modifier.height(50.dp))

            Column (
                modifier = Modifier
                    .padding(15.dp)
                    .height(200.dp),
                verticalArrangement = Arrangement.Center
            ){

                Row {

                    Text(text = "ESTOQUE ATUAL")
                }

                Spacer(modifier = Modifier.height(30.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text(text = "Quantidade")

                    OutlinedTextField(
                        value = "30 comprimido(s)" ,
                        onValueChange ={},
                        modifier = Modifier
                            .width(160.dp)
                            .height(50.dp)
                    )

                }

                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    Text(text = "DEFINICÃO DE  LIMITE ")
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text(text = "Limite")

                    OutlinedTextField(
                        value = "10 comprimido(s)" ,
                        onValueChange ={},
                        modifier = Modifier
                            .width(160.dp)
                            .height(50.dp)
                    )
                }

            }
            Spacer(modifier = Modifier.height(300.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row {
                    ButtonPadrao(
                        text = "Proximo",
                        onClick = {},
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(text = "Limite")
                }
            }

        }
    }

}

@Preview
@Composable
fun AlarmePreview(){

    Alarme()

}
