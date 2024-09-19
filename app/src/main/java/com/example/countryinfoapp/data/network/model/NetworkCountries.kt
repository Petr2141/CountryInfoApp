package com.example.countryinfoapp.data.network.model


import kotlinx.serialization.Serializable


@Serializable
data class NetworkCountries (

    var capital  : String   = "",
    var code     : String   = "",
    var currency : NetworkCurrency = NetworkCurrency(),
    var flag     : String   = "",
    var language : NetworkLanguage = NetworkLanguage(),
    var name     : String   = "",
    var region   : String   = ""

)
