package com.example.donortrack.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.data.network.AuthEvent
import com.example.data.network.AuthEventBus
import com.example.data.repository.SessionManager
import com.example.feature_add_donation.screen.AddDonationApp
import com.example.feature_auth.ui.screen.EditProfileApp
import com.example.feature_auth.ui.screen.ProfileApp
import com.example.feature_auth.ui.screen.RegisterApp
import com.example.feature_auth.ui.screen.LoginApp
import com.example.feature_auth.ui.screen.ConfirmApp
import com.example.feature_auth.viewmodel.ProfileViewModel
import com.example.feature_auth.viewmodel.RegistrationViewModel
import com.example.feature_calendar.ui.CalendarApp
import com.example.feature_common.util.navigation.Routes
import com.example.feature_main.ui.screen.MainApp
import com.example.feature_map.screen.MapApp
import com.example.feature_scanner.ui.ScannerApp

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val sessionManager = remember { SessionManager(context) }
    val isLoggedIn by sessionManager.isLoggedIn.collectAsState()

    val profileViewModel: ProfileViewModel = viewModel()
    val registrationViewModel: RegistrationViewModel = viewModel()

    androidx.compose.runtime.LaunchedEffect(Unit) {
        AuthEventBus.events.collect { event ->
            when (event) {
                AuthEvent.UNAUTHORIZED -> {
                    sessionManager.setAuthorized(false)

                    navController.navigate(Routes.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Routes.Main.route else Routes.Registration.route
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
                    sessionManager.setAuthorized(true)
                    navController.navigate(Routes.Main.route) {
                        popUpTo(0) { inclusive = true }
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
                    sessionManager.setAuthorized(true)
                    navController.navigate(Routes.Main.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Main.route) {
            MainApp(modifier = modifier)
        }

        composable(Routes.Profile.route) {
            ProfileApp(
                navController = navController,
                viewModel = profileViewModel,
                modifier = modifier,
                onLogoutClick = {
                    sessionManager.setAuthorized(false)
                    navController.navigate(Routes.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.EditProfile.route) {
            EditProfileApp(
                modifier = modifier,
                viewModel = profileViewModel,
                onSuccessBack = {
                    profileViewModel.loadProfile()
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.AddDonation.route) {
            AddDonationApp(
                modifier = modifier,
                onSuccessBack = {
                    profileViewModel.loadProfile()
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.Map.route) {
            MapApp(modifier = modifier)
        }

        composable(Routes.Calendar.route) {
            CalendarApp()
        }

        composable(Routes.Scanner.route) {
            ScannerApp()
        }
    }
}