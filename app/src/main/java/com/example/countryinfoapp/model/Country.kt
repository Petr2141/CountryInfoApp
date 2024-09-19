package com.example.countryinfoapp.model

import kotlinx.serialization.Serializable


@Serializable
data class Country (
    var capital  : String   = "",
    var code     : String   = "",
    var name     : String   = "",
    var region   : String   = "",
    var currency : String   = "",
    var language : String   = "",
)
