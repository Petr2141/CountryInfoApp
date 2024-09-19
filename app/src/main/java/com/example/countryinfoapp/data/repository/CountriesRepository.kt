package com.example.countryinfoapp.data.repository

import com.example.countryinfoapp.model.Country
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    fun getCountries(): Flow<List<Country>>
}