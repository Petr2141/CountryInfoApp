package com.example.countryinfoapp

import com.example.countryinfoapp.data.network.model.NetworkCountries
import com.example.countryinfoapp.data.network.model.NetworkCurrency
import com.example.countryinfoapp.data.repository.CountriesRepository
import com.example.countryinfoapp.data.repository.CountriesRepositoryImpl
import com.example.countryinfoapp.fake.FakeCountriesNetworkDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CountriesRepositoryTest {
    private lateinit var fakeNetworkDataSource: FakeCountriesNetworkDataSource
    private lateinit var countriesRepository: CountriesRepository

    @Before
    fun setup() {
        val fakeCountriesList = listOf(
            NetworkCountries("Country1", "Region1", NetworkCurrency("Code1")),
            NetworkCountries("Country2", "Region2", NetworkCurrency("Code2"))
        )

        fakeNetworkDataSource = FakeCountriesNetworkDataSource(fakeCountriesList)
        countriesRepository = CountriesRepositoryImpl(fakeNetworkDataSource)
    }

    // testCountries flow returns data from fakeNetworkDataSource
    @Test
    fun networkCountriesRepository_getCountries_verifyCountries() = runTest {
        val result = countriesRepository.countries.first()

        assertEquals(listOf(
            NetworkCountries("Country1", "Region1", NetworkCurrency("Code1")),
            NetworkCountries("Country2", "Region2", NetworkCurrency("Code2"))
        ), result)
    }

    // test countries flow returns empty list when networkDataSource is empty
    @Test
    fun test_countries_flow_returns_empty_list_when_networkDataSource_is_empty() = runTest {
        fakeNetworkDataSource = FakeCountriesNetworkDataSource(emptyList())
        countriesRepository = CountriesRepositoryImpl(fakeNetworkDataSource)

        val result = countriesRepository.countries.first()
        assertTrue(result.isEmpty())
    }
}