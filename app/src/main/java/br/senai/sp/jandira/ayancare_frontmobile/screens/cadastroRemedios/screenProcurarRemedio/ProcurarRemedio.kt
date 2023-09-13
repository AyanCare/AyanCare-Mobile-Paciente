package br.senai.sp.jandira.ayancare.componentes.screen.screenProcurarRemedio

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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.ayancare.R
import br.senai.sp.jandira.ayancare.componentes.ButtonPadrao
import br.senai.sp.jandira.ayancare.componentes.SearchViewList


@Composable
fun TelaProcurarRemedio(){

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {


            Column(
                modifier = Modifier.fillMaxHeight(),

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(8.dp)
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },


                        ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                            contentDescription = ""
                        )

                    }

                }
                Spacer(modifier = Modifier.height(15.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.procurar_remedio)
                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    SearchViewList(list = listOf(
                    ))

                }
                Spacer(modifier = Modifier.height(450.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ){
                    ButtonPadrao(
                        text = stringResource(id = R.string.proximo),
                        onClick = {
                        },
                    )


                }
            }
    }

}


@Preview
@Composable
fun ScreenAdicionarRemedioPreview(){
    TelaProcurarRemedio()

}

