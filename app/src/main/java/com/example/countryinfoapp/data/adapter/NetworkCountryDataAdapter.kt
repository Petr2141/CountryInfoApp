package com.example.countryinfoapp.data.adapter

import com.example.countryinfoapp.data.local.database.model.CountryEntity
import com.example.countryinfoapp.data.network.model.NetworkCountries

data class NetworkCountryDataAdapter (
    private val networkCountry: NetworkCountries
) : CountryDataModelInterface {
    override val name: String
        get() = networkCountry.name
    override val region: String
        get() = networkCountry.region
    override val code: String
        get() = networkCountry.code
    override val capital: String
        get() = networkCountry.capital
}

fun NetworkCountryDataAdapter.asCountryEntity() = CountryEntity(
    name = name,
    region = region,
    code = code,
    capital = capital
)