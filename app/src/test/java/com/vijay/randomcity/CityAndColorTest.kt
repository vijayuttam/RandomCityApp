package com.vijay.randomcity

import com.vijay.randomcity.data.entity.EmissionEntity
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.LocalDateTime

class CityViewModelTest {
    private val mutableEmissions = MutableStateFlow<List<EmissionEntity>>(emptyList())
    private val emissions: StateFlow<List<EmissionEntity>> = mutableEmissions
    private val cities = listOf(
        "New York",
        "Los Angles",
        "Scranton",
        "Philadelphia",
        "Nashville",
        "Sanit Louis",
        "Miami"
    )
    private val colors = listOf("Yellow", "White", "Green", "Blue", "Red", "Black")

    @Test
    fun testCityAndColorAreValidTwo() = runTest {
        assertTrue(emissions.first().isEmpty())

        val city = cities.random()
        val color = colors.random()
        val emission = EmissionEntity(
            city = city,
            color = color,
            timestamp = LocalDateTime.now()
        )
        mutableEmissions.value = mutableEmissions.value.plus(emission).sortedBy { it.city }

        assertTrue(cities.contains(emission.city))
        assertTrue(colors.contains(emission.color))
    }
}
