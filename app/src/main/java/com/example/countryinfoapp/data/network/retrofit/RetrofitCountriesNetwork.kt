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


// TODO maybe need rename, because it is DataSource
internal class RetrofitCountriesNetwork constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
    baseUrl: String
) : CountriesNetworkDataSource {
    private val networkApi = trace("RetrofitCountriesNetwork") {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory { okhttpCallFactory.newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(RetrofitCountriesNetworkApi::class.java)
    }

    override suspend fun getCountries(): List<NetworkCountries> =
        networkApi.getCountries()
}

fun okHttpCallFactory(): Call.Factory = trace("CountriesOkHttpClient") {
    OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    // TODO() add BuildConfig
                    //if (BuildConfig.DEBUG) {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                    //}
                },
        )
        .build()
}

