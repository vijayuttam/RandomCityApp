package com.vijay.randomcity.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMapView(city: String) {
    val cityLatLng = getLatLngForCity(city)

    val cameraPositionState = rememberCameraPositionState {
        // Adjust the zoom level accordingly
        position = CameraPosition.fromLatLngZoom(cityLatLng, 12f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = cityLatLng),
            title = city,
            snippet = "Welcome to $city!"
        )
    }
}

// LatLng for selected city
fun getLatLngForCity(city: String): LatLng {
    return when (city) {
        "New York" -> LatLng(40.712776, -74.005974)
        "Los Angles" -> LatLng(34.052235, -118.243683)
        "Scranton" -> LatLng(41.408969, -75.662412)
        "Philadelphia" -> LatLng(39.952583, -75.165222)
        "Nashville" -> LatLng(36.162663, -86.781601)
        "Sanit Louis" -> LatLng(38.627003, -90.199402)
        "Miami" -> LatLng(25.761681, -80.191788)
        // Default to (0,0) if the city is not recognized
        else -> LatLng(0.0, 0.0)
    }
}

