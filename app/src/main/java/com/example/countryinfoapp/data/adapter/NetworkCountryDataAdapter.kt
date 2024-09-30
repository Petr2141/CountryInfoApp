package com.example.countryinfoapp.data.adapter

import com.example.countryinfoapp.data.network.model.NetworkCountries

class NetworkCountryDataAdapter (
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