package com.example.countryinfoapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.countryinfoapp.CountriesApplication
import com.example.countryinfoapp.data.repository.CountriesRepository
import com.example.countryinfoapp.domain.CountriesSortField
import com.example.countryinfoapp.domain.GetSortCountriesUseCase
import com.example.countryinfoapp.ui.adapter.CountryUIModelAdapter
import com.example.countryinfoapp.ui.adapter.CountryUIModelInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val repository: CountriesRepository
) : ViewModel() {

    private val uiState = MutableStateFlow<MainActivityUiState>(MainActivityUiState.Loading)
    public val getUiState: StateFlow<MainActivityUiState> = uiState

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                GetSortCountriesUseCase(repository)(CountriesSortField.Region)
                    .collect { countriesList ->
                        uiState.value = MainActivityUiState
                            .Success( countriesList
                                .map { CountryUIModelAdapter (it) })
                }
            } catch (e: Throwable) {
                uiState.value = MainActivityUiState.Error(e)
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

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class  Success(val countries: List<CountryUIModelInterface>) : MainActivityUiState
    data class  Error(val throwable: Throwable) : MainActivityUiState
}
