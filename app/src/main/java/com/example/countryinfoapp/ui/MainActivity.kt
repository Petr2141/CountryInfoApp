package com.example.countryinfoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countryinfoapp.R
import com.example.countryinfoapp.ui.adapter.CountryUIModelInterface
import com.example.countryinfoapp.ui.recyclerview.CountriesAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val progressBar by lazy {
        findViewById<ProgressBar>(R.id.ac_main_pb_loading)
    }
    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.ac_main_rv_countries)
    }

    private val countriesViewModel: CountriesViewModel by viewModels {
        CountriesViewModel.Factory
    }

    private val countriesAdapter = CountriesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView(countriesAdapter)

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
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    private fun showCountries(countriesList: List<CountryUIModelInterface>) {
        if (countriesList.isEmpty()) {
            Log.e(this.javaClass.name, "Countries list is empty.")
            showError(Throwable("Countries list is empty."))
            return
        }
        countriesAdapter.updateCountries(countriesList)
        hideLoading()
    }

    private fun showError(error: Throwable) {
        // System errors in logs
        Log.e(this.javaClass.name, "Error occurred: ${error.message}", error)
        // Only localized errors can be in the UI
        Snackbar.make(findViewById(R.id.main), R.string.something_went_wrong, Snackbar.LENGTH_LONG).show()
        hideLoading()
    }

    private fun initRecyclerView(countriesAdapter: CountriesAdapter) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = countriesAdapter
    }
}