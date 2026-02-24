package com.example.donortrack.util.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.donortrack.ui.screen.EditProfileApp

import com.example.donortrack.ui.screen.MainApp
import com.example.donortrack.ui.screen.ProfileApp
import com.example.donortrack.viewmodel.ProfileViewModel

sealed class Routes(val route: String) {
    object Profile: Routes("profile")
    object Main: Routes("main")
    object EditProfile: Routes("editprofile")
}


@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    val viewModel: ProfileViewModel = viewModel()

    NavHost(
        navController = navController, startDestination = Routes.Main.route
    ) {
        composable(Routes.Profile.route) {
            ProfileApp(navController = navController, viewModel = viewModel, modifier = modifier)
        }
        composable(Routes.Main.route) {
            MainApp(modifier = modifier)
        }

        composable(Routes.EditProfile.route) {
            EditProfileApp(modifier = modifier, viewModel = viewModel)
        }
    }
}

