package com.example.donortrack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.donortrack.network.api.DonorAPI
import com.example.donortrack.network.RetrofitHelper
import com.example.donortrack.util.navigation.AppNavigation
import com.example.donortrack.util.navigation.bottomItems
import com.example.donortrack.ui.theme.DonorTrackTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val donorAPI = RetrofitHelper.getInstance().create(DonorAPI::class.java)


        enableEdgeToEdge()
        setContent {
            DonorTrackTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomBarNavigate(navController = navController) },
                    topBar = { TopBarBack(navController = navController) }
                ) { paddingValues ->
                    AppNavigation(navController = navController, modifier =  Modifier.padding(paddingValues))
                }
            }
        }
        GlobalScope.launch {
            val result = donorAPI.getCitiesInfo()
            if (result != null)
                Log.d("donorAPI", result.toString())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBack(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

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
            title = {  }
        )
    }
}

@Composable
fun BottomBarNavigate(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        bottomItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}