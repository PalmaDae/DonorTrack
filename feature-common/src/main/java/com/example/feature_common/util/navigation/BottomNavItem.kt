package com.example.feature_common.util.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val bottomItems = listOf(
    BottomNavItem("Главная", Icons.Filled.Home, "main"),
    BottomNavItem("Профиль", Icons.Filled.Person, "profile"),
    BottomNavItem("Регистрация", Icons.Filled.Create, route = "registration")
)