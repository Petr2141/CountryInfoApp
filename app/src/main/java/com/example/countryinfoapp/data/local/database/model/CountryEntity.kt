package com.example.countryinfoapp.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity (
    @PrimaryKey
    val code: String,
    val name: String,
    val region: String,
    val capital: String
)