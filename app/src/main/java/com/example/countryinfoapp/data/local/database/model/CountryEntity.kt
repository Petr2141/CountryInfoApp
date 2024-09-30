package com.example.countryinfoapp.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.countryinfoapp.data.adapter.CountryDataModelInterface

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey
    override val code: String,
    override val name: String,
    override val region: String,
    override val capital: String
) : CountryDataModelInterface