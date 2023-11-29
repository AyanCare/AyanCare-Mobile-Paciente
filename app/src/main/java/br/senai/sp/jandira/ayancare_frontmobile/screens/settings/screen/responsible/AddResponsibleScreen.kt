package br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.responsible

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.CustomTextAreaValidate
import br.senai.sp.jandira.ayancare_frontmobile.components.CustomTextFieldValidate
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.components.ModalSuccess
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository.ResponsibleRepository
import br.senai.sp.jandira.ayancare_frontmobile.screens.emergencia.adicionarContato.components.TextFieldTelefone
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddResponsibleScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {

    var isDialogVisibleSuccess by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val array = PacienteRepository(context = context).findUsers()
    val focusManager = LocalFocusManager.current

    val paciente = array[0]
    var id = paciente.id.toLong()

    var nomeState by remember {
        mutableStateOf("")
    }

    var telefoneState by remember {
        mutableStateOf("")
    }

    var localState by remember {
        mutableStateOf("")
    }

    var validateName by rememberSaveable {
        mutableStateOf(true)
    }
    var validateTelefone by rememberSaveable {
        mutableStateOf(true)
    }
    var validateLocal by rememberSaveable {
        mutableStateOf(true)
    }

    val validateNameError = "Nome está em branco"
    val validateTelefoneError = "Telefone está em branco"
    val validateLocalError = "Local está em branco"

    fun validateData(
        name: String,
        local: String,
        numero: String
    ): Boolean {

        validateName = name.isNotBlank()
        validateLocal = local.isNotBlank()
        validateTelefone = numero.isNotBlank()

        return validateName && validateLocal && validateTelefone
    }

    fun responsible(
        nome: String,
        numero: String,
        local: String,
        id_paciente: Int,
        id_status_contato: Int
    ) {
        if (validateData(nome, local, numero)) {
            val responsibleRepository = ResponsibleRepository()
            lifecycleScope.launch {

                val response = responsibleRepository.registerResponsible(
                    nome,
                    numero,
                    local,
                    id_paciente,
                    id_status_contato
                )

                if (response.isSuccessful) {
                    Log.e(MainActivity::class.java.simpleName, "responsible bem-sucedido")
                    Log.e("add-responsible", "responsible: ${response.body()}")
                    val checagem = response.body()?.get("status")

                    Log.e("add-responsible", "responsible: ${checagem}")

                    if (checagem.toString() == "404") {
                        Toast.makeText(context, "algo está invalido", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Sucesso!!", Toast.LENGTH_SHORT).show()
                        //isDialogVisibleSuccess = true
                        navController.navigate("responsible_screen")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()

                    Log.e(
                        MainActivity::class.java.simpleName,
                        "Erro durante o add-responsible: $errorBody"
                    )
                    Toast.makeText(context, "algo está invalido", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Por favor, reolhe suas caixas de texto", Toast.LENGTH_SHORT).show()
        }

    }

    if (isDialogVisibleSuccess) {
        ModalSuccess(
            onDismiss = {}
        )
        LaunchedEffect(isDialogVisibleSuccess) {
            delay(4000) // Aguarda 4 segundos
            isDialogVisibleSuccess = false // Fecha o modal
        }
    }

    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
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
                text = "Adicionar Responsável",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomTextFieldValidate(
                value = nomeState,
                onValueChange = { nomeState = it },
                label = "Nome:",
                showError = !validateName,
                errorMessage = validateNameError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                unfocusedBorderColor = Color(0xFF64748B),
                focusedBorderColor = Color(0xFF6650A4),
                textColor = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(20.dp))

//            CustomTextFieldValidate(
//                value = telefoneState,
//                onValueChange = { telefoneState = it },
//                label = "Telefone:",
//                showError = !validateTelefone,
//                errorMessage = validateTelefoneError,
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Number,
//                    imeAction = ImeAction.Next
//                ),
//                keyboardActions = KeyboardActions(
//                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
//                ),
//                unfocusedBorderColor = Color(0xFF64748B),
//                focusedBorderColor = Color(0xFF6650A4),
//                textColor = Color(0xFF64748B)
//            )

            TextFieldTelefone(
                value = telefoneState,
                aoMudar = {
                    telefoneState = it
                },
                label = "Digite o Telefone:",
                showError = !validateTelefone,
                errorMessage = validateTelefoneError
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column {
                Text(
                    text = "Local:",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    color = Color(0xFF191D23)
                )
                CustomTextAreaValidate(
                    value = localState,
                    onValueChange = { localState = it },
                    label = "",
                    showError = !validateLocal,
                    errorMessage = validateLocalError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.clearFocus() }
                    ),
                    unfocusedBorderColor = Color(0xFF64748B),
                    focusedBorderColor = Color(0xFF6650A4),
                    textColor = Color(0xFF64748B),
                    height = 140
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DefaultButton(
                    onClick = {
                        responsible(
                            nome = nomeState,
                            numero = telefoneState,
                            local = localState,
                            id_paciente = id.toInt(),
                            id_status_contato = 3
                        )
                    },
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
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("responsible_screen")
                        }
                )
            }
        }
    }
}
