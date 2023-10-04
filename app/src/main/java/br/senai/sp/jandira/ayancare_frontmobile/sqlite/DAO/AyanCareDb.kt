package br.senai.sp.jandira.ayancare_frontmobile.sqlite.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.criacaoTabela.Paciente

@Database(entities = [Paciente::class], version = 1)
abstract class AyanCareDb : RoomDatabase() {

    abstract fun pacienteDao() : PacienteDao

    companion object {

        private lateinit var instanceDb: AyanCareDb

        fun getDatabase(context: Context): AyanCareDb {
            // :: =  entregar uma instancia do objeto, me devolve um false se não existir nada

            //se isso for verdade
            if (!::instanceDb.isInitialized) {
                //.databaseBuilder =  criar um banco de dados
                // criar a instancia
                instanceDb = Room
                    .databaseBuilder(
                        context, //contexto da minha aplicação
                        AyanCareDb::class.java, //ja esta criando automaticamente a instancia
                        "db_ayan_care" // nome do banco
                    ).allowMainThreadQueries().build()
            }
            return instanceDb

        }
    }
}