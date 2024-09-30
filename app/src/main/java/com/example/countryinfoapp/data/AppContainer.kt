package com.example.countryinfoapp.data

import android.content.Context
import androidx.room.DeleteTable
import com.example.countryinfoapp.data.local.database.CountriesDataBaseDataSource
import com.example.countryinfoapp.data.network.retrofit.RetrofitCountriesNetworkDataSource
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

class DataAppContainer(context: Context) : AppContainer {
    private val countriesBaseUrl = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"

    private val json = Json {
        coerceInputValues = true  // replaces `null` with a default value
        ignoreUnknownKeys = true  // Ignore unknown fields in JSON
    }

    private val netDataSource =
        RetrofitCountriesNetworkDataSource(json, okHttpCallFactory(), countriesBaseUrl)

    private val databaseDataSource =
        DatabaseProvider.getDatabase(context).сountriesDataBaseDataSource()

    override val countriesRepository: CountriesRepository by lazy {
         /* If we add a local database in the future,
          * we can update CountriesRepositoryImpl to take both netDataSource
          * and DataBaseDataSource, and handle the business logic in CountriesRepositoryImpl.*/
        CountriesRepositoryImpl(netDataSource, databaseDataSource)
    }

}