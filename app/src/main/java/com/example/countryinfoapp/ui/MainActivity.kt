package com.example.countryinfoapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countryinfoapp.R
import com.example.countryinfoapp.ui.recyclerview.CountriesAdapter

class MainActivity : AppCompatActivity() {

    private val countriesViewModel: CountriesViewModel by viewModels {
        CountriesViewModel.Factory
    }

    private lateinit var countriesAdapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countriesViewModel.countries.observe(this) { countriesList ->
            Log.d("main", "Countries list size = " + countriesList.size)
            // TODO Make a data update in the adapter
            //countriesAdapter.submitList(countriesList)
            countriesAdapter = CountriesAdapter(countriesList)
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.ac_main_rv_countries)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = countriesAdapter
    }
}