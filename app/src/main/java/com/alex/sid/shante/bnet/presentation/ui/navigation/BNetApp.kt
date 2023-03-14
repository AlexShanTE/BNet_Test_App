package com.alex.sid.shante.bnet.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alex.sid.shante.bnet.presentation.ui.details.DetailsScreen
import com.alex.sid.shante.bnet.presentation.ui.home.HomeScreen

@Composable
fun BNetApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.DetailsScreen.route + "/{drugsId}",
            arguments = listOf(
                navArgument("drugsId") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { entry ->
            DetailsScreen(
                navController = navController,
                drugsId = entry.arguments?.getInt("drugsId")
            )
        }
    }
}

sealed class Screen(val route: String) {
    object HomeScreen: Screen(route = "Home")
    object DetailsScreen: Screen(route = "Details")

    fun withArgs(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}