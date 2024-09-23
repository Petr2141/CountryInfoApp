package com.example.countryinfoapp.domain

import com.example.countryinfoapp.data.network.model.NetworkCountries
import com.example.countryinfoapp.data.repository.CountriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSortCountriesUseCase constructor(
    private val countriesRepository: CountriesRepository
) {
    operator fun invoke(sortBy: CountriesSortField = CountriesSortField.CountriesName): Flow<List<NetworkCountries>> =
        countriesRepository.countries.map { countries ->
            when (sortBy) {
                CountriesSortField.Capital       -> countries.sortedBy { it.capital }
                CountriesSortField.CountriesName -> countries.sortedBy { it.name }
                CountriesSortField.CurrencyName  -> countries.sortedBy { it.currency.name }
                CountriesSortField.LanguageName  -> countries.sortedBy { it.language.name }
            }
        }
}

sealed class CountriesSortField {
    data object CountriesName : CountriesSortField()
    data object Capital : CountriesSortField()
    data object CurrencyName : CountriesSortField()
    data object LanguageName : CountriesSortField()
}