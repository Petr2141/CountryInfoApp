package com.example.countryinfoapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.countryinfoapp.data.network.model.NetworkCountries
import com.example.countryinfoapp.data.repository.CountriesRepository
import com.example.countryinfoapp.model.Country
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val repository: CountriesRepository
) : ViewModel() {

    private val _countries = MutableLiveData<List<NetworkCountries>>()
    val countries: LiveData<List<NetworkCountries>> = _countries

    init {
        loadCountries()
    }

    fun loadCountries() {
        viewModelScope.launch {
            repository.countries.collect { countriesList ->
                _countries.value = countriesList
            }
        }
    }
}

// TODO add UiState to this app?
sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class  Success(val countries: List<Country>) : MainActivityUiState
    data class  Error(val throwable: Throwable) : MainActivityUiState
}


class CountriesViewModelFactory(private val repository: CountriesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CountriesViewModel(repository) as T
        }
        // TODO add catch Exception
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}