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
import com.example.countryinfoapp.data.network.model.NetworkCountries
import com.example.countryinfoapp.ui.recyclerview.CountriesAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll

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
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    private fun showCountries(countriesList: List<NetworkCountries>) {
        Log.d("main", "Countries list size = " + countriesList.size)
        hideLoading()
        val countriesAdapter = CountriesAdapter(countriesList)
        initRecyclerView(countriesAdapter)
    }

    private fun showError(error: Throwable) {
        Snackbar.make(findViewById(R.id.main), "Error: ${error.message}", Snackbar.LENGTH_LONG).show()
        hideLoading()
    }

    private fun initRecyclerView(countriesAdapter: CountriesAdapter) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = countriesAdapter
    }
}