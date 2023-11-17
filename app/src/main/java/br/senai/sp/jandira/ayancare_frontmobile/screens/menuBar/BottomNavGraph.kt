package br.senai.sp.jandira.ayancare_frontmobile.screens.menuBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile.screens.calendary.screen.CalendaryScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.estoque.verEstoque.screen.EstoqueScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.home.screen.HomeScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.ProfileScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    navRotasController: NavController
) {

    val localStorage: Storage = Storage()

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
    ){
        composable(route = BottomBarScreen.Stock.route){
            EstoqueScreen(navRotasController = navRotasController, navController = navController)
        }
        composable(route = BottomBarScreen.Cadendary.route){
            CalendaryScreen(navController = navController, navRotasController = navRotasController, localStorage = localStorage)
        }
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navRotasController = navRotasController)
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(navController = navController, navRotasController = navRotasController)
        }
    }
}
