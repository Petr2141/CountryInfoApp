package com.example.countryinfoapp.data.network.model

import kotlinx.serialization.Serializable


@Serializable
data class NetworkLanguage (

    var code : String = "",
    var name : String = ""

)