package br.senai.sp.jandira.ayancare_frontmobile.screens.calendary.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.event.EventResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.event.service.Event
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun OptionEventCalendary() {


    val context = LocalContext.current

    val array = PacienteRepository(context = context).findUsers()

    val paciente = array[0]
    var id = paciente.id.toLong()

    var listEvents by remember {
        mutableStateOf(
            listOf(
                Event(0, "", "", "", "", "", "", "")
            )
        )
    }

    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getEvent().getEventsByIdPaciente(id.toInt())

    call.enqueue(object : Callback<EventResponse> {
        override fun onResponse(
            call: Call<EventResponse>,
            response: Response<EventResponse>
        ) {

            Log.e("TAG", "onResponse: ${response.body()}")

            if (response.body()!!.status == 404) {
                Log.e("TAG", "a resposta está nula")
                listEvents = emptyList()
            } else {
                listEvents = response.body()!!.evento
            }

            Log.e("TAG", "onResponse: $listEvents")

        }

        override fun onFailure(call: Call<EventResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }

    })


    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
    ){
        Text(
            text = "Evento de hoje",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(400),
            color = Color(0xFF35225F)
        )
        Spacer(modifier = Modifier.height(10.dp))
        for (event in listEvents) {
            CardCalendary(
                value = event.dia,
                title = event.dia,
                subtitle = event.nome
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}