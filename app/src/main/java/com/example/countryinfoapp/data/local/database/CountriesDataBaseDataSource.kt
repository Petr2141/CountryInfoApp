package com.example.countryinfoapp.data.local.database

import androidx.room.Dao
import com.example.countryinfoapp.data.local.database.dao.CountryDao

@Dao
interface CountriesDataBaseDataSource : CountryDao {
}