package com.vijay.randomcity.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "emission_table")
data class EmissionEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val city: String,
    val color: String,
    val timestamp: LocalDateTime
)