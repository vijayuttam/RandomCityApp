package com.vijay.randomcity.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import com.vijay.randomcity.data.entity.EmissionEntity
import com.vijay.randomcity.ui.common.CityViewModel
import java.time.format.DateTimeFormatter
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController, viewModel: CityViewModel) {
    val emissions by viewModel.emissions.collectAsState()

    val configuration = LocalConfiguration.current
    // 2 represents landscape orientation
    val isLandscape = configuration.orientation == 2

    // State to keep track of the selected city for details view
    var selectedCity by remember { mutableStateOf<EmissionEntity?>(null) }

    if (isLandscape) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Master list on the left
            Column(modifier = Modifier.weight(1f)) {
                CityList(emissions) { cityEmission ->
                    // Update the selected city on click
                    selectedCity = cityEmission
                }
            }

            // onclick details on the right
            Box(modifier = Modifier.weight(2f)) {
                selectedCity?.let { emission ->
                    DetailsScreen(city = emission.city, color = emission.color)
                } ?: run {
                    Text(text = "Select a city", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    } else {
        // Portrait Mode
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Random City App") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        ) {
            CityList(emissions) { cityEmission ->
                // navigate to a separate details screen
                navController.navigate("details_screen/${cityEmission.city}/${cityEmission.color}")
            }
        }
    }
}

@Composable
fun CityList(
    emissions: List<EmissionEntity>,
    onCityClick: (EmissionEntity) -> Unit
) {
    LazyColumn {
        items(emissions) { emission ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onCityClick(emission)
                    }
                    .padding(16.dp)
            ) {
                Text(
                    text = emission.city,
                    color = getColorFromName(emission.color),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(text = emission.timestamp.format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    fontSize = 18.sp,
                )
            }
        }
    }
}

fun getColorFromName(colorName: String): Color {
    return when (colorName) {
        "Yellow" -> Color.Yellow
        "White" -> Color.White
        "Green" -> Color.Green
        "Blue" -> Color.Blue
        "Red" -> Color.Red
        "Black" -> Color.Black
        else -> Color.Gray
    }
}
