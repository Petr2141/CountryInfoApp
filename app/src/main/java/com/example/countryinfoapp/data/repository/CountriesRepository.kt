package com.example.countryinfoapp.data.repository

import com.example.countryinfoapp.data.adapter.CountryDataModelInterface
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    val countries: Flow<List<CountryDataModelInterface>>
}
