package br.senai.sp.jandira.ayancare_frontmobile.screens.AddRemedy.screen.addRemedyNonExistent.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage

@Composable
fun AddRemedyNonExistentScreen(
    navController: NavController,
    localStorage: Storage
) {
    var context = LocalContext.current

    val focusManager = LocalFocusManager.current

    var nomeState by remember {
        mutableStateOf("")
    }

    Surface(
        color = Color(248, 240, 236)
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier.size(30.dp),
                tint = Color(0xFF35225F)
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 60.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(id = R.drawable.remedios),
                    contentDescription = "",
                    modifier = Modifier
                        .size(71.dp)
                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Qual o nome do medicamento?",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(40.dp))
                OutlinedTextField(
                    value = nomeState ,
                    onValueChange = {
                        nomeState = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Nome do medicamento")
                    }
                )
            }
            DefaultButton(
                onClick = {
                    if (nomeState.isEmpty()){
                        Toast.makeText(context, "Nome inválido", Toast.LENGTH_LONG).show()
                    }else{
                        localStorage.salvarValor(context, nomeState, "nome_medicamento")
                        navController.navigate("form_medicine_screen")
                    }
                },
                text = "Proximo"
            )
        }
    }
}