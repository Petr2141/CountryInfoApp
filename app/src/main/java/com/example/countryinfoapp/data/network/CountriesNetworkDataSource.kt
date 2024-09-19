package com.example.countryinfoapp.data.network

import com.example.countryinfoapp.data.network.model.NetworkCountries

interface CountriesNetworkDataSource {
    suspend fun getCountries(): List<NetworkCountries>
}