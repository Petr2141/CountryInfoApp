package com.example.countryinfoapp.domain

import com.example.countryinfoapp.data.repository.CountriesRepository
import com.example.countryinfoapp.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSortCountriesUseCase constructor(
    private val countriesRepository: CountriesRepository
) {
    operator fun invoke(sortBy: CountriesSortField = CountriesSortField.None): Flow<List<Country>> =
        countriesRepository.getCountries().map { countries ->
            val sortedCountries = countries.map { country ->
                Country(
                    capital = country.capital,
                    code    = country.code,
                    name    = country.name,
                    region  = country.region,
                    currency = country.currency.name,
                    language = country.language.name
                )
            }
            when (sortBy) {
                CountriesSortField.None          -> sortedCountries
                CountriesSortField.Capital       -> sortedCountries.sortedBy { it.capital }
                CountriesSortField.CountriesName -> sortedCountries.sortedBy { it.name }
                CountriesSortField.CurrencyName  -> sortedCountries.sortedBy { it.currency }
                CountriesSortField.LanguageName  -> sortedCountries.sortedBy { it.language }
            }
        }
}

sealed class CountriesSortField {
    data object None : CountriesSortField()
    data object CountriesName : CountriesSortField()
    data object Capital : CountriesSortField()
    data object CurrencyName : CountriesSortField()
    data object LanguageName : CountriesSortField()
}