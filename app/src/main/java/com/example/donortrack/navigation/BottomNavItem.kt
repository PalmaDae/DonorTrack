package com.example.donortrack.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.donortrack.R

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val bottomItems = listOf(
    BottomNavItem("Главная", Icons.Filled.Home, "main"),
    BottomNavItem("Профиль", Icons.Filled.Person, "profile")
)