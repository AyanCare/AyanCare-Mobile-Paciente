package br.senai.sp.jandira.ayancare_frontmobile.screens.menuBar

import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.ayancare_frontmobile.screens.cadastro.screen.CadastroScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.confirmacaoEmail.screen.ConfirmacaoEmailScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.estoque.verEstoque.screen.EstoqueScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.finalizarCadastro.screen.FinalizarCadastroScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.home.screen.HomeScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.login.screen.LoginScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.ProfileScreen

@Composable
fun ButtonNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
    ){
        composable(route = BottomBarScreen.Stock.route){
            EstoqueScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Cadendary.route){

        }
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }
    }
}