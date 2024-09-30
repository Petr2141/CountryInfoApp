package com.example.countryinfoapp.ui.adapter

import com.example.countryinfoapp.domain.adapter.CountryDomainModelInterface

data class CountryUIModelAdapter (
    private val countryDomainModel : CountryDomainModelInterface
) : CountryUIModelInterface {
    override val name: String
        get() = countryDomainModel.name
    override val region: String
        get() = countryDomainModel.region
    override val code: String
        get() = countryDomainModel.code
    override val capital: String
        get() = countryDomainModel.capital
}