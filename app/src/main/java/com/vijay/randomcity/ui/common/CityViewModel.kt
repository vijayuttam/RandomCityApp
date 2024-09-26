package com.vijay.randomcity.ui.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vijay.randomcity.data.EmissionDatabase
import com.vijay.randomcity.data.dao.EmissionDao
import com.vijay.randomcity.data.entity.EmissionEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime


class CityViewModel(application: Application) : AndroidViewModel(application) {
    private var emissionDao: EmissionDao = EmissionDatabase.getDatabase(application).emissionDao()
    private val _emissions = MutableStateFlow<List<EmissionEntity>>(emptyList())
    val emissions: StateFlow<List<EmissionEntity>> = _emissions

    private val cities = listOf("New York", "Los Angles", "Scranton", "Philadelphia", "Nashville", "Sanit Louis", "Miami")
    private val colors = listOf("Yellow", "White", "Green", "Blue", "Red", "Black")

    init {
        startProducer()
    }

    private fun startProducer() {
        viewModelScope.launch {
            while (true) {
                delay(5000)
                val city = cities.random()
                val color = colors.random()
                val emission = EmissionEntity(
                    city = city,
                    color = color,
                    timestamp = LocalDateTime.now()
                )
                _emissions.value = _emissions.value.plus(emission).sortedBy { it.city }
               saveEmissionToDB(emission)
            }
        }
    }

    private suspend fun saveEmissionToDB(emission: EmissionEntity) {
        // Save the emission to a local database
        viewModelScope.launch {
            emissionDao.insert(emission)
        }
    }
}
