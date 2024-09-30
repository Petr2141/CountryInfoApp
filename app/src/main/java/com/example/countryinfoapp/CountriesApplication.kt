package com.example.countryinfoapp

import android.app.Application
import com.example.countryinfoapp.data.AppContainer
import com.example.countryinfoapp.data.DataAppContainer

class CountriesApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DataAppContainer(applicationContext)
    }
}