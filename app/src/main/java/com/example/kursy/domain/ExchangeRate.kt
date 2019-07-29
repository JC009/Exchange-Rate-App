package com.example.kursy.domain

import com.google.gson.annotations.SerializedName

data class ExchangeRate (
    @SerializedName("currency")
    val currency: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("mid")
    val mid: Float
)