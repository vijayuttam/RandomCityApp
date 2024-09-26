package com.vijay.randomcity.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.vijay.randomcity.maps.GoogleMapView
import com.vijay.randomcity.workers.scheduleWelcomeMessage
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(city: String, color: String) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        // Schedule the Toast after 5 seconds
        scheduleWelcomeMessage(city, context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(city) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = getColorFromName(color)
                )
            )
        }
    ) {
        // show the map on selected city
        GoogleMapView(city)
    }
}
