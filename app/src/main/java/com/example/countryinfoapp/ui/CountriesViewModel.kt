package com.example.countryinfoapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.countryinfoapp.CountriesApplication
import com.example.countryinfoapp.data.network.model.NetworkCountries
import com.example.countryinfoapp.data.repository.CountriesRepository
import com.example.countryinfoapp.model.Country
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val repository: CountriesRepository
) : ViewModel() {

    private val _countries = MutableLiveData<List<NetworkCountries>>()
    //TODO Remove LiveData
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CountriesApplication)
                val countriesRepository = application.container.countriesRepository
                CountriesViewModel(repository = countriesRepository)
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
