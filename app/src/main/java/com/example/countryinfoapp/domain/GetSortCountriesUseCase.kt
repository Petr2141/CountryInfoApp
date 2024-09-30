package com.example.countryinfoapp.domain

import com.example.countryinfoapp.data.repository.CountriesRepository
import com.example.countryinfoapp.domain.CountriesSortField.Capital
import com.example.countryinfoapp.domain.CountriesSortField.CountriesName
import com.example.countryinfoapp.domain.CountriesSortField.LanguageName
import com.example.countryinfoapp.domain.CountriesSortField.Region
import com.example.countryinfoapp.domain.adapter.CountryDataModelAdapter
import com.example.countryinfoapp.domain.adapter.CountryDomainModelInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class GetSortCountriesUseCase constructor(
    private val countriesRepository: CountriesRepository
) {
    operator fun invoke(sortBy: CountriesSortField = CountriesName): Flow<List<CountryDomainModelInterface>> =
        countriesRepository.countries.transform { countries ->
            val adapterCountries = countries.map { CountryDataModelAdapter(it) }
            with (adapterCountries) {
                when (sortBy) {
                    CountriesName -> sortedBy { it.name }
                    LanguageName -> sortedBy { it.name }
                    Region -> sortedBy { it.region }
                    Capital -> sortedBy { it.capital }
                }
            }
        }
}

sealed class CountriesSortField {
    data object CountriesName : CountriesSortField()
    data object Capital : CountriesSortField()
    data object Region : CountriesSortField()
    data object LanguageName : CountriesSortField()
}