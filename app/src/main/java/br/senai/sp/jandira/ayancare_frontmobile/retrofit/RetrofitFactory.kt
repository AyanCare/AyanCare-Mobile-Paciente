package br.senai.sp.jandira.ayancare_frontmobile.retrofit

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.AlarmeService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.calendario.service.CalendarioService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.service.ConectarService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.cor.service.CorService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.cuidador.service.CuidadorService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.event.service.EventService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.genero.service.GeneroService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.medidas.service.MedidasService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.notificacao.service.NotificacaoService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.PacienteService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.remedy.service.RemedyService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.service.ResponsavelService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.testeHumor.service.TesteHumorService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.SSLServerSocketFactory

object RetrofitFactory {

    //private const val BASE_URL = "http://10.107.144.13:8080"
    //private const val BASE_URL = "https://ayancare-api.cyclic.cloud"
    //private const val BASE_URL = "http://10.107.144.20:8080"
    //private const val BASE_URL = "http://192.168.0.119:8080" //192.168.0.120
    private const val BASE_URL = "https://ayan-backend.azurewebsites.net/"


    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private var retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPatient(): PacienteService {
        return retrofitFactory.create(PacienteService::class.java)
    }

    fun getResponsible(): ResponsavelService{
        return retrofitFactory.create(ResponsavelService::class.java)
    }

    fun getGenero(): GeneroService{
        return retrofitFactory.create(GeneroService::class.java)
    }

    fun getConectar(): ConectarService{
        return retrofitFactory.create(ConectarService::class.java)
    }

    fun getRemedy(): RemedyService{
        return retrofitFactory.create(RemedyService::class.java)
    }

    fun getMedidas(): MedidasService {
        return retrofitFactory.create(MedidasService::class.java)
    }

    fun getTesteHumor(): TesteHumorService {
        return retrofitFactory.create(TesteHumorService::class.java)
    }

    fun getCuidador(): CuidadorService {
        return retrofitFactory.create(CuidadorService::class.java)
    }

    fun getEvent(): EventService {
        return retrofitFactory.create(EventService::class.java)
    }

    fun getCor(): CorService {
        return retrofitFactory.create(CorService::class.java)
    }

    fun getAlarme(): AlarmeService {
        return retrofitFactory.create(AlarmeService::class.java)
    }

    fun getCalendario(): CalendarioService{
        return retrofitFactory.create(CalendarioService::class.java)
    }

    fun getNotificacao(): NotificacaoService{
        return retrofitFactory.create(NotificacaoService::class.java)
    }

}