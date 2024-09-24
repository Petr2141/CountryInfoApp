package com.example.countryinfoapp.data.repository

import android.util.Log
import com.example.countryinfoapp.data.network.CountriesNetworkDataSource
import com.example.countryinfoapp.data.network.model.NetworkCountries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface CountriesRepository {
    val countries: Flow<List<NetworkCountries>>
}

class CountriesRepositoryImpl(
    private val networkDataSource: CountriesNetworkDataSource
) : CountriesRepository {
    override val countries: Flow<List<NetworkCountries>> = flow {
        val countriesList = networkDataSource.getCountries()
        emit(countriesList)
    }
}
