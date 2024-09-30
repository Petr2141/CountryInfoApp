package com.example.countryinfoapp.data.adapter

import com.example.countryinfoapp.data.local.database.model.CountryEntity

class CountryDataEntityAdapter (
    private val entity: CountryEntity
) : CountryDataModelInterface {
    override val name: String
        get() = entity.name
    override val region: String
        get() = entity.region
    override val code: String
        get() = entity.code
    override val capital: String
        get() = entity.capital

}