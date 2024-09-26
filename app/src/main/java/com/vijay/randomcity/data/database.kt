package com.vijay.randomcity.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vijay.randomcity.data.dao.EmissionDao
import com.vijay.randomcity.data.entity.EmissionEntity
import com.vijay.randomcity.utils.LocalDateTimeConverter

@Database(entities = [EmissionEntity::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class)
abstract class EmissionDatabase : RoomDatabase() {

    abstract fun emissionDao(): EmissionDao

    companion object {
        @Volatile
        private var INSTANCE: EmissionDatabase? = null

        fun getDatabase(context: Context): EmissionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmissionDatabase::class.java,
                    "emission_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}