package br.senai.sp.jandira.ayancare_frontmobile.screens.emergencia.adicionarContato.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.ayancare_frontmobile.screens.MaskVisualTransformation
import br.senai.sp.jandira.ayancare_frontmobile.screens.emergencia.adicionarContato.components.NumberDefaults.MASKCPF
import br.senai.sp.jandira.ayancare_frontmobile.screens.emergencia.adicionarContato.components.NumberDefaults.MASKNUMBER

@Composable
fun TextFieldCpf(
    cpfState: String,
    aoMudar: (String) -> Unit,
    placeholder: String,
    isError: Boolean
) {
    var telefone by remember(cpfState) {
        mutableStateOf(cpfState)
    }

    telefone = cpfState

    OutlinedTextField(
        value = telefone,
        onValueChange = { newText ->
            if (newText.length <= 11) { // Limita o usuário a 11 caracteres
                val unformattedText = newText.replace(Regex("[^\\d]"), "")
                aoMudar(unformattedText)
                telefone = unformattedText
            }
        },
        placeholder = {
            Text(text = placeholder, color = Color(0xFF606060))
        },
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth(),
        isError = isError,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        shape = RoundedCornerShape(25.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Blue,
            //containerColor = Color(0xFF393939),
            unfocusedBorderColor = Color(0xFF393939),
            focusedBorderColor = Color.Black,
            cursorColor = Color.Blue
        ),
        visualTransformation = MaskVisualTransformation(MASKCPF)
    )
}