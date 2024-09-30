package com.example.countryinfoapp.data.repository

import com.example.countryinfoapp.data.adapter.CountryDataModelInterface
import com.example.countryinfoapp.data.adapter.NetworkCountryDataAdapter
import com.example.countryinfoapp.data.adapter.asCountryEntity
import com.example.countryinfoapp.data.local.database.CountriesDataBaseDataSource
import com.example.countryinfoapp.data.network.CountriesNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class CountriesRepositoryImpl(
    private val networkDataSource: CountriesNetworkDataSource,
    private val dataBaseDataSource: CountriesDataBaseDataSource
) : CountriesRepository {
    override val countries: Flow<List<CountryDataModelInterface>> = flow {
        val cachedCountries = dataBaseDataSource.getAllCountries().firstOrNull()
        if (cachedCountries != null && cachedCountries.isNotEmpty()) {
            emit(cachedCountries)
        } else {
            val countriesList = networkDataSource.getCountries()
            dataBaseDataSource.insertCountries( countriesList
                .map { NetworkCountryDataAdapter(it).asCountryEntity() })
            emit( countriesList.map { NetworkCountryDataAdapter(it) })
        }
    }
}