package com.example.countryinfoapp.data.network.retrofit

import com.example.countryinfoapp.data.network.CountriesNetworkDataSource
import com.example.countryinfoapp.data.network.model.NetworkCountries
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import androidx.tracing.trace

private const val COUNTRIES_BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"

internal class RetrofitCountriesNetwork constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : CountriesNetworkDataSource {
    private val networkApi = trace("RetrofitCountriesNetwork") {
        Retrofit.Builder()
            .baseUrl(COUNTRIES_BASE_URL)
            .callFactory { okhttpCallFactory.newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(RetrofitCountriesNetwork::class.java)
    }

    override suspend fun getCountries(): List<NetworkCountries> =
        networkApi.getCountries()
}

private fun okHttpCallFactory(): Call.Factory = trace("CountriesOkHttpClient") {
    OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    //if (BuildConfig.DEBUG) {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                    //}
                },
        )
        .build()
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object CountriesApi {
    val retrofitService: CountriesNetworkDataSource by lazy {
        RetrofitCountriesNetwork(Json, okHttpCallFactory())
    }
}

