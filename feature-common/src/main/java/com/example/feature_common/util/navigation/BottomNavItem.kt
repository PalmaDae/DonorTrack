package com.example.feature_common.util.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange // Импортируем иконку календаря
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val bottomItems = listOf(
    BottomNavItem("Главная", Icons.Filled.Home, "main"),
    BottomNavItem("Карта OЗК", Icons.Filled.Place, "map"),
    BottomNavItem("Профиль", Icons.Filled.Person, "profile"),
    BottomNavItem("Календарь", Icons.Filled.DateRange, "calendar"),
    BottomNavItem("Сканер", Icons.Filled.QrCodeScanner, "scanner")
)