package com.example.countryinfoapp.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countryinfoapp.R
import com.example.countryinfoapp.ui.adapter.CountryUIModelInterface

class CountriesAdapter(private val dataSet: List<CountryUIModelInterface> ) :
    RecyclerView.Adapter<CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_country, parent, false)

        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        with(dataSet[position]) {
            holder.name.text    = name
            holder.region.text  = if (region.isBlank()) "" else ", $region"
            holder.code.text    = code
            holder.capital.text = capital
        }
    }

    override fun getItemCount(): Int = dataSet.size

}