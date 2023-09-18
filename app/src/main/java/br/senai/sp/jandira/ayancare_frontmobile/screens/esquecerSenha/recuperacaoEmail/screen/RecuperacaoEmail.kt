package br.senai.sp.jandira.ayancare_frontmobile.screens.esquecerSenha.recuperacaoEmail.screen

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile.components.Wave
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository.ResetPasswordRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperacaoEmailScreen(navController: NavController, onEmailEntered: (String) -> Unit,  lifecycleScope: LifecycleCoroutineScope) {

    var textstate2 by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }

    var validateEmail by rememberSaveable {
        mutableStateOf(true)
    }

    val context = LocalContext.current

    fun validateData(email: String): Boolean {

        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        return validateEmail
    }

    fun resetPassword (
        email: String,
        onSuccess: () -> Unit // Adicione este parâmetro
    ) {
        if(validateData(email)){
            val resetPasswordRepository = ResetPasswordRepository()

            lifecycleScope.launch {
                val response = resetPasswordRepository.requestPasswordReset(email)

                if(response.isSuccessful){
                    Log.e(MainActivity::class.java.simpleName, "Email bem-sucedido" )
                    Log.e("Email", "Email: ${response.body()}", )
//                    val checagem = response.body()?.get("status")
//                    if (checagem.toString() == "404") {
//                        Toast.makeText(context, "Email ou senha inválido", Toast.LENGTH_LONG).show()
//                    } else {
//                        Toast.makeText(context, "Seja bem-vindo", Toast.LENGTH_SHORT).show()
//                        navController.navigate("loading")
//                    }
                    onSuccess()
                }else{
                    val errorBody = response.errorBody()?.string()

                    Log.e(MainActivity::class.java.simpleName, "Erro durante o reset da senha: $errorBody")
                    Toast.makeText(context, "Email inválido", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Por favor, reolhe suas caixas de texto", Toast.LENGTH_SHORT).show()
        }
    }

    Surface(
        color = Color(248, 240, 236)
    ) {
        Wave()

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Recuperação de Senha",
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),

                    )
                Text(
                    text = "Preencha os dados para realizar a recuperação de senha",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF9A9A9A),
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(100.dp))

            Column (
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
            ){
                OutlinedTextField(
                    value = email,
                    onValueChange = { newEmail ->
                        email = newEmail },
                    label = { Text(text = "email", fontSize = 15.sp)},
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(),
                    textStyle = TextStyle.Default.copy(fontSize = 15.sp)
                )
            }

            Spacer(modifier = Modifier.height(200.dp))


            DefaultButton(text = "Enviar", onClick = {

                resetPassword(email) {
                    // Navegar para outra tela após o envio bem-sucedido do email
                    navController.navigate("codigo_senha_screen")
                }
                //navController.navigate("codigo_senha_screen")
            })
        }
    }
}

//@Preview
//@Composable
//fun RecuperacaoEmailPreview() {
//    RecuperacaoEmailScreen()
//}