package br.senai.sp.jandira.ayancare_frontmobile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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

@Composable
fun CustomTextFieldNumber(
    value: String,
    aoMudar: (String) -> Unit,
    label: String,
    showError: Boolean,
    errorMessage: String = ""
) {
    var number by remember(value) {
        mutableStateOf(value)
    }

    OutlinedTextField(
        value = number,
        onValueChange = { newText ->
            if (newText.length <= 5) { // Limita o usuário a 5 caracteres
                val unformattedText = newText.replace(Regex("[^\\d]"), "")
                aoMudar(unformattedText)
                number = unformattedText
            }
        },
        label = {
            Text(
                text = label,
                color = Color(0xFF64748B)
            )
        },
        modifier = Modifier
            .padding(bottom = 10.dp)
            .width(160.dp)
            .height(70.dp)
            .background(Color(255, 255, 255, 1), shape = RoundedCornerShape(4.dp)),
        isError = showError,
        trailingIcon = {
            if (showError)
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "Icone de Erro",
                    tint = Color(187, 0, 0, 255)
                )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xFF000000),
            unfocusedBorderColor = Color(0xFF64748B),
            focusedBorderColor = Color(0xFF6650A4),
            errorBorderColor = Color.Red
        ),
    )
    if (showError) {
        Text(
            text = errorMessage,
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(start = 8.dp)
                .offset(y = (-8).dp)
                .fillMaxWidth(0.9f)
        )
    }
}