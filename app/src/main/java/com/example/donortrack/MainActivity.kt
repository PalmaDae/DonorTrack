package com.example.donortrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.donortrack.util.AppNavigation
import com.example.feature_common.ui.theme.DonorTrackTheme
import com.example.feature_common.util.navigation.Routes
import com.example.feature_common.util.navigation.bottomItems

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            DonorTrackTheme {
                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val isAuthScreen = currentRoute == Routes.Registration.route ||
                        currentRoute == Routes.Login.route ||
                        currentRoute == Routes.Confirm.route

                Scaffold(
                    topBar = {
                        if (!isAuthScreen) {
                            TopBarBack(navController = navController, currentRoute = currentRoute)
                        }
                    },
                    bottomBar = {
                        if (!isAuthScreen) {
                            BottomBarNavigate(navController = navController, currentRoute = currentRoute)
                        }
                    }
                ) { paddingValues ->
                    AppNavigation(
                        navController = navController,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBack(navController: NavController, currentRoute: String?) {
    val isRootScreen = bottomItems.any { it.route == currentRoute }

    if (!isRootScreen) {
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )
    } else {
        TopAppBar(
            title = { }
        )
    }
}

@Composable
fun BottomBarNavigate(navController: NavController, currentRoute: String?) {
    NavigationBar {
        bottomItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}