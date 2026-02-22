package com.example.donortrack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.donortrack.ui.screen.MainApp
import com.example.donortrack.ui.screen.ProfileApp

sealed class Routes(val route: String) {
    object Profile: Routes("profile")
    object Main: Routes("main")
}


@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Routes.Main.route) {
        composable(Routes.Profile.route) {
            ProfileApp()
        }
        composable(Routes.Main.route) {
            MainApp()
        }
    }
}

