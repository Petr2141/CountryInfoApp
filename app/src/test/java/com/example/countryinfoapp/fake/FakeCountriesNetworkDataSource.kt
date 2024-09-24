package com.example.countryinfoapp.fake

import com.example.countryinfoapp.data.network.CountriesNetworkDataSource
import com.example.countryinfoapp.data.network.model.NetworkCountries

class FakeCountriesNetworkDataSource(
        private val fakeCountries: List<NetworkCountries>
    ) : CountriesNetworkDataSource {

    override suspend fun getCountries(): List<NetworkCountries> {
        return fakeCountries
    }
}
