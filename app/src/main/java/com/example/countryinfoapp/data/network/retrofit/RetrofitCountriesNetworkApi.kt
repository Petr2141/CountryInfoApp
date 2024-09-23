package com.example.countryinfoapp.data.network.retrofit

import com.example.countryinfoapp.data.network.model.NetworkCountries
import retrofit2.http.GET

/**
 * Retrofit API declaration for Countries Network API
 */

interface RetrofitCountriesNetworkApi {
    @GET(value = "countries.json")
    suspend fun getCountries(): List<NetworkCountries>
}
