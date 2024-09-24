package com.example.countryinfoapp.data

import com.example.countryinfoapp.data.network.retrofit.RetrofitCountriesNetwork
import com.example.countryinfoapp.data.network.retrofit.okHttpCallFactory
import com.example.countryinfoapp.data.repository.CountriesRepository
import com.example.countryinfoapp.data.repository.CountriesRepositoryImpl
import kotlinx.serialization.json.Json


/**
 * Dependency Injection container at the application level.
 */

interface AppContainer {
    val countriesRepository: CountriesRepository
}

class DataAppContainer : AppContainer {
    private val countriesBaseUrl = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"

    private val json = Json {
        coerceInputValues = true  // replaces `null` with a default value
        ignoreUnknownKeys = true  // Ignore unknown fields in JSON
    }

    private val netDataSource =
        RetrofitCountriesNetwork(json, okHttpCallFactory(), countriesBaseUrl)

    override val countriesRepository: CountriesRepository by lazy {
        // TODO if we will have local data base,
        //  we can make CountriesRepositoryImpl(netDataSource,DataBaseDataSource)
        //  + business logic at the CountriesRepositoryImpl
        CountriesRepositoryImpl(netDataSource)
    }

}