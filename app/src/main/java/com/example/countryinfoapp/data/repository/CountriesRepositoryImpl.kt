package com.example.countryinfoapp.data.repository

import com.example.countryinfoapp.data.adapter.CountryDataModelInterface
import com.example.countryinfoapp.data.adapter.NetworkCountryDataAdapter
import com.example.countryinfoapp.data.local.database.CountriesDataBaseDataSource
import com.example.countryinfoapp.data.network.CountriesNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

//TODO Если у нас в ui есть diffUtil, то обновляться будут только ячейки с изменениями
//Тогда получается если мы отобразим из БД а потом этоже из сетки, то UI не моргнет
//А если данных в бд нет, будем ждать в ui
//Если сетка отвалилась, то покажем из бд без апдейта из сети

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
            dataBaseDataSource.insertCountries( countriesList.map { NetworkCountryDataAdapter(it) })
            emit( countriesList.map { NetworkCountryDataAdapter(it) })
        }
    }
}