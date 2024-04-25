package com.example.humo_transfer_sample.models

import androidx.annotation.DrawableRes

data class CountryItem (
    val nameCountry:String,
    @DrawableRes val image:Int,
    val course:String,
    val nameCountryForTransfer: String,
    val initial:String
)