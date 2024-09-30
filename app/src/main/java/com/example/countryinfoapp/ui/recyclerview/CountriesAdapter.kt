package com.example.countryinfoapp.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.countryinfoapp.R
import com.example.countryinfoapp.ui.adapter.CountryUIModelInterface


class CountriesAdapter : RecyclerView.Adapter<CountryViewHolder>() {

    companion object CountryDiffUtilCallback : DiffUtil.ItemCallback<CountryUIModelInterface>() {
        override fun areItemsTheSame(
            oldItem: CountryUIModelInterface,
            newItem: CountryUIModelInterface
        ): Boolean =
            oldItem.code == newItem.code

        override fun areContentsTheSame(
            oldItem: CountryUIModelInterface,
            newItem: CountryUIModelInterface
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.capital == newItem.capital &&
                    oldItem.region == newItem.region &&
                    oldItem.code == newItem.code
        }
    }

    private val mDiffer =
        AsyncListDiffer<CountryUIModelInterface>(this, CountryDiffUtilCallback)

    fun updateCountries(newCountries: List<CountryUIModelInterface> ) {
        mDiffer.submitList(newCountries)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_country, parent, false)

        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        with(mDiffer.currentList[position]) {
            holder.name.text    = name
            holder.region.text  = if (region.isBlank()) "" else ", $region"
            holder.code.text    = code
            holder.capital.text = capital
        }
    }

    override fun getItemCount(): Int = mDiffer.currentList.size



}


