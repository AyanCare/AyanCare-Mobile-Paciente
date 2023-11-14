package br.senai.sp.jandira.ayancare_frontmobile.screens

import android.content.Context
import android.content.SharedPreferences
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.calendario.service.Alarmes

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
}