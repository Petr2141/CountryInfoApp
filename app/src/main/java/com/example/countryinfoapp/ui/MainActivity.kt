package com.example.countryinfoapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countryinfoapp.R
import com.example.countryinfoapp.data.network.model.NetworkCountries
import com.example.countryinfoapp.ui.recyclerview.CountriesAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val countriesViewModel: CountriesViewModel by viewModels {
        CountriesViewModel.Factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            countriesViewModel.getUiState.collect { uiState ->
                when (uiState) {
                    MainActivityUiState.Loading -> showLoading()
                    is MainActivityUiState.Success -> showCountries(uiState.countries)
                    is MainActivityUiState.Error -> showError(uiState.throwable)
                }
            }
        }
    }

    private fun showLoading() {
    }

    private fun showCountries(countriesList: List<NetworkCountries>) {
        Log.d("main", "Countries list size = " + countriesList.size)
        val countriesAdapter = CountriesAdapter(countriesList)
        initRecyclerView(countriesAdapter)
    }

    private fun showError(error: Throwable) {
    }

    private fun initRecyclerView(countriesAdapter: CountriesAdapter) {
        val recyclerView: RecyclerView = findViewById(R.id.ac_main_rv_countries)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = countriesAdapter
    }
}