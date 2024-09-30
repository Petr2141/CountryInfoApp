package com.example.countryinfoapp.domain.adapter

import com.example.countryinfoapp.data.adapter.CountryDataModelInterface

data class CountryDataModelAdapter (
    private val countryDataModel : CountryDataModelInterface
) : CountryDomainModelInterface {
    override val name: String
        get() = countryDataModel.name
    override val region: String
        get() = countryDataModel.region
    override val code: String
        get() = countryDataModel.code
    override val capital: String
        get() = countryDataModel.capital

}