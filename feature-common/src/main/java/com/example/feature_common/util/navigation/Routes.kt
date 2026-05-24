package com.example.feature_common.util.navigation

sealed class Routes(val route: String) {
    object Profile : Routes("profile")
    object Main : Routes("main")
    object EditProfile : Routes("editprofile")
    object Registration : Routes("registration")
    object Login : Routes("login")
    object AddDonation : Routes("add_donation")
    object Confirm : Routes("confirm/{email}/{login}") {
        fun createRoute(email: String, login: String) = "confirm/$email/$login"
    }
}