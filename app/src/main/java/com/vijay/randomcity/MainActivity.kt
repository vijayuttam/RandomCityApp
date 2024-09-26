package com.vijay.randomcity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.vijay.randomcity.navigation.AppNavigation
import com.vijay.randomcity.ui.common.CityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomCityApp()
        }
    }
}

@Composable
fun RandomCityApp() {
    val navController = rememberNavController()
    val viewModel: CityViewModel = hiltViewModel() // Assuming Hilt is used

    // Set up the navigation host
    AppNavigation(navController = navController, viewModel = viewModel)
}

