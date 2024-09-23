package com.example.countryinfoapp.ui.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.countryinfoapp.R

class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.tv_item_country_name)
    val region: TextView = itemView.findViewById(R.id.tv_item_country_region)
    val code: TextView = itemView.findViewById(R.id.tv_item_country_code)
    val capital: TextView = itemView.findViewById(R.id.tv_item_country_capital)
}