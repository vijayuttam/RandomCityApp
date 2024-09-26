package com.vijay.randomcity.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vijay.randomcity.data.entity.EmissionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EmissionDao {
    @Query("SELECT * FROM emission_table ORDER BY city ASC")
    fun getAll(): Flow<List<EmissionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(emission: EmissionEntity)

    @Query("DELETE FROM emission_table")
    suspend fun deleteAllEmissions()
}

