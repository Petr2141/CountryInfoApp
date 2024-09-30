package com.example.countryinfoapp.data

import android.content.Context
import androidx.room.Room
import com.example.countryinfoapp.data.local.database.CountryInfoDatabase

object DatabaseProvider {
    private var INSTANCE: CountryInfoDatabase? = null

    fun getDatabase(context: Context): CountryInfoDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context,
                CountryInfoDatabase::class.java,
                "country_database")
                .build()
            INSTANCE = instance
            instance
        }
    }
}