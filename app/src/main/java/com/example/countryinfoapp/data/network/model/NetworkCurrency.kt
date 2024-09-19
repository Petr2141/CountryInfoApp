package com.example.countryinfoapp.data.network.model

import kotlinx.serialization.Serializable


@Serializable
data class NetworkCurrency (

    var code   : String = "",
    var name   : String = "",
    var symbol : String = ""

)