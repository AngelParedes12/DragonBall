package edu.ucne.dragonball.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.dragonball.presentation.tareas.details.DetailPlaetScreen
import edu.ucne.dragonball.presentation.tareas.list.ListPlanetScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.PlanetList
    ) {
        composable<Screen.PlanetList> {
            ListPlanetScreen(
                onPlanetClick = { planetId ->
                    navController.navigate(Screen.PlanetDetail(planetId))
                }
            )
        }

        composable<Screen.PlanetDetail> {
            DetailPlaetScreen(
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}