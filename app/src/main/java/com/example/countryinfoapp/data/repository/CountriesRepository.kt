package com.example.countryinfoapp.data.repository

import android.util.Log
import com.example.countryinfoapp.data.network.CountriesNetworkDataSource
import com.example.countryinfoapp.data.network.model.NetworkCountries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface CountriesRepository {
    val countries: Flow<List<NetworkCountries>>
}

//TODO create file for CountriesRepositoryImpl
class CountriesRepositoryImpl(
    private val networkDataSource: CountriesNetworkDataSource
) : CountriesRepository {
    override val countries: Flow<List<NetworkCountries>> = flow {
        try {
            val countriesList = networkDataSource.getCountries()
            emit(countriesList)
        } catch (e: Exception) {
            //TODO make better error
            Log.e("CountriesRepositoryImpl", e.toString())
            emit(emptyList())
        }
    }
}
