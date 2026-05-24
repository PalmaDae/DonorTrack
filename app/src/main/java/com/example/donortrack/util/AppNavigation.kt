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
import com.example.feature_auth.ui.screen.LoginApp
import com.example.feature_auth.ui.screen.ConfirmApp
import com.example.feature_auth.viewmodel.ProfileViewModel
import com.example.feature_auth.viewmodel.RegistrationViewModel
import com.example.feature_common.util.navigation.Routes
import com.example.feature_main.ui.screen.MainApp

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    val profileViewModel: ProfileViewModel = viewModel()
    val registrationViewModel: RegistrationViewModel = viewModel()

    NavHost(
        navController = navController, startDestination = Routes.Registration.route
    ) {
        composable(Routes.Registration.route) {
            RegisterApp(
                modifier = modifier,
                viewModel = registrationViewModel,
                onNavigateToLogin = {
                    navController.navigate(Routes.Login.route)
                },
                onNavigateToConfirm = { email, login ->
                    navController.navigate(Routes.Confirm.createRoute(email, login))
                }
            )
        }

        composable(Routes.Login.route) {
            LoginApp(
                modifier = modifier,
                onNavigateToRegister = {
                    navController.navigate(Routes.Registration.route)
                },
                onLoginSuccess = {
                    navController.navigate(Routes.Main.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Confirm.route) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val login = backStackEntry.arguments?.getString("login") ?: ""

            ConfirmApp(
                modifier = modifier,
                viewModel = registrationViewModel,
                email = email,
                login = login,
                onConfirmSuccess = {
                    navController.navigate(Routes.Main.route) {
                        popUpTo(Routes.Registration.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Main.route) {
            MainApp(modifier = modifier)
        }

        composable(Routes.Profile.route) {
            ProfileApp(navController = navController, viewModel = profileViewModel, modifier = modifier)
        }

        composable(Routes.EditProfile.route) {
            EditProfileApp(modifier = modifier, viewModel = profileViewModel)
        }

        composable(Routes.AddDonation.route) {
            AddDonationApp(modifier = modifier)
        }
    }
}