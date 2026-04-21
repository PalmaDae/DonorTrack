package com.example.feature_common.util.navigation

sealed class Routes(val route: String) {
    object Profile: Routes("profile")
    object Main: Routes("main")
    object EditProfile: Routes("editprofile")
    object Registration: Routes("registration")
    object AddDonation: Routes("add_donation")
}

