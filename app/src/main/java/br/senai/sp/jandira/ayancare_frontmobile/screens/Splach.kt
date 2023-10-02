package br.senai.sp.jandira.ayancare_frontmobile.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.ayancare_frontmobile.screens.cadastro.screen.CadastroScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.esquecerSenha.codigoRecuperacao.screen.CodigoRecuperacaoScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.esquecerSenha.recuperacaoEmail.screen.RecuperacaoEmailScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.esquecerSenha.redefinirSenha.screen.RedefinirSenhaScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.finalizarCadastro.screen.FinalizarCadastroScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.finalizarCadastro.screen.PatientAddressScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.login.screen.LoginScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.menuBar.MainScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.screen.EditProfileScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.SettingsScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.codigoPaciente.screen.PatientCodeScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.responsible.AddResponsibleScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.responsible.ResponsibleScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.sugestao.SuggestionScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.telaPrincipal.screen.TelaPrincipalScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.telasInstrucoes.telaInstrucao1.screen.TelaInstrucao1Screen
import br.senai.sp.jandira.ayancare_frontmobile.screens.telasInstrucoes.telaInstrucao2.screen.TelaInstrucao2Screen
import br.senai.sp.jandira.ayancare_frontmobile.screens.telasInstrucoes.telaInstrucao3.screen.TelaInstrucao3Screen
import br.senai.sp.jandira.ayancare_frontmobile.screens.testeHumor.screen.HumorTestScreen
import br.senai.sp.jandira.ayancare_frontmobile.ui.theme.AyanCareFrontMobileTheme
import br.senai.sp.jandira.ayancare_frontmobile.viewModel.user.CreateAccountView

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AyanCareFrontMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    var viewModelCreateAccount = viewModel<CreateAccountView>()

                    NavHost(
                        navController = navController,
                        startDestination = "tela_principal_screen"
                    ) {
                        composable("tela_principal_screen") {
                            TelaPrincipalScreen(navController = navController)
                        }

                        composable("login_screen") {
                            LoginScreen(navController = navController, lifecycleScope = lifecycleScope, viewModel = viewModelCreateAccount)
                        }

                        composable("cadastro_screen") {
                            CadastroScreen(navController = navController, lifecycleScope = lifecycleScope)
                        }

                        composable("finalizar_cadastro_screen"){
                            FinalizarCadastroScreen(navController = navController)
                        }

                        composable("endereco_paciente_screen"){
                            PatientAddressScreen(navController = navController)
                        }

                        composable("tela_instrucao1_screen") {
                           TelaInstrucao1Screen(navController = navController)
                        }

                        composable("tela_instrucao2_screen") {
                            TelaInstrucao2Screen(navController = navController)
                        }

                        composable("tela_instrucao3_screen") {
                            TelaInstrucao3Screen(navController = navController)
                        }

                        composable("main_screen") {
                            MainScreen(navRotasController = navController)
                        }

                        composable("esquecer_senha_screen") {
                            RecuperacaoEmailScreen(navController = navController, onEmailEntered = {_: String ->}, lifecycleScope = lifecycleScope )
                        }

                        composable("codigo_senha_screen") {
                            CodigoRecuperacaoScreen(navController = navController)
                        }

                        composable("redefinir_senha_screen") {
                            RedefinirSenhaScreen(navController = navController)
                        }

                        composable("setting_screen"){
                            SettingsScreen(navController = navController, navRotasController = navController)
                        }

                        composable("responsible_screen"){
                            ResponsibleScreen(navController = navController, navRotasController = navController)
                        }

                        composable("add_responsible_screen"){
                            AddResponsibleScreen(navController = navController, navRotasController = navController)
                        }

                        composable("edit_profile_screen"){
                            EditProfileScreen(navController = navController, navRotasController = navController)
                        }

                        composable("codigo_paciente_screen"){
                            PatientCodeScreen(navController = navController, navRotasController = navController, viewModel = viewModelCreateAccount)
                        }

                        composable("sugestoes_screen"){
                            SuggestionScreen(navController = navController, navRotasController = navController)
                        }

                        composable("humor_test_screen"){
                            HumorTestScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}