package com.vijay.randomcity.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import com.vijay.randomcity.ui.common.CityViewModel


@Composable
fun SplashScreen(navController: NavController, viewModel: CityViewModel) {
    val emissions by viewModel.emissions.collectAsState()

    LaunchedEffect(emissions.isNotEmpty()) {
        if (emissions.isNotEmpty()) {
            navController.navigate("main_screen")
        }
    }

    // Simple splash screen UI
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Random City App", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}