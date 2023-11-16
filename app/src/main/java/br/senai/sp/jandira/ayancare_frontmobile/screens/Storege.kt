package br.senai.sp.jandira.ayancare_frontmobile.screens

import android.content.Context
import android.content.SharedPreferences
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.calendario.service.Alarmes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Storage {
    fun salvarValor(context: Context, valor: String, nome: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("NomeDoArquivo", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(nome, valor)
        editor.apply()
    }

    fun salvarValor(context: Context, valor: List<Alarmes>, nome: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("NomeDoArquivo", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(nome, valor.toString())
        editor.apply()
    }

    fun lerValor(context: Context, nome: String): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("NomeDoArquivo", Context.MODE_PRIVATE)
        return sharedPreferences.getString(nome, null)
    }

//    fun lerValorArray(context: Context, nome: String): List<Alarmes>? {
//        val sharedPreferences: SharedPreferences = context.getSharedPreferences("NomeDoArquivo", Context.MODE_PRIVATE)
//
//        // Obtém a string JSON armazenada
//        val jsonValor = sharedPreferences.getString(nome, null)
//
//        // Verifica se a string não é nula e não está vazia
//        if (!jsonValor.isNullOrBlank()) {
//            // Desserializa a string JSON de volta para a lista de objetos
//            val gson = Gson()
//            val type = object : TypeToken<List<Alarmes>>() {}.type
//            return gson.fromJson(jsonValor, type)
//        }
//
//        // Retorna null se a string for nula ou vazia
//        return null
//    }

}