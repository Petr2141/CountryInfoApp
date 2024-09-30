package com.example.countryinfoapp.data.local.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.countryinfoapp.data.adapter.CountryDataModelInterface
import kotlinx.coroutines.flow.Flow

interface CountryDao {
    @Query("SELECT * FROM countries")
    fun getAllCountries(): Flow<List<CountryDataModelInterface>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryDataModelInterface>)

    @Query("DELETE FROM countries")
    suspend fun deleteAllCountries()
}