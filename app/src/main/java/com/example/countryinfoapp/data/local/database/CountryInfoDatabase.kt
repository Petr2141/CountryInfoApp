package com.example.countryinfoapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.countryinfoapp.data.local.database.dao.CountryDao

@Database(entities = [CountryDao::class], version = 1)
abstract class CountryInfoDatabase : RoomDatabase() {
    abstract fun сountriesDataBaseDataSource(): CountriesDataBaseDataSource
}