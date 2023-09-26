package br.senai.sp.jandira.ayancare_frontmobile.screens.menuBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.ayancare_frontmobile.screens.emergencia.adicionarResponsavel.screen.AddResponsibleScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.estoque.verEstoque.screen.EstoqueScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.home.screen.HomeScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.ProfileScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.testeHumor.screen.HumorTestScreen

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
            //SettingsScreen(navController = navController)
            //AddResponsibleScreen(navController = navController)
            HumorTestScreen()
        }
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }
    }
}