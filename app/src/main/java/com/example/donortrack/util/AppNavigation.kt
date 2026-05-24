package com.example.donortrack.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature_add_donation.screen.AddDonationApp
import com.example.feature_auth.ui.screen.EditProfileApp
import com.example.feature_auth.ui.screen.ProfileApp
import com.example.feature_auth.ui.screen.RegisterApp
import com.example.feature_auth.viewmodel.ProfileViewModel
import com.example.feature_common.util.navigation.Routes
import com.example.feature_main.ui.screen.MainApp

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

        composable(Routes.Registration.route) {
            RegisterApp(modifier = modifier)
        }

        composable(Routes.AddDonation.route) {
            AddDonationApp(modifier = modifier)
        }
    }
}
