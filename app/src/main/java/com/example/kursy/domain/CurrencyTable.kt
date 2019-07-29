package com.example.kursy.domain

import com.google.gson.annotations.SerializedName

data class CurrencyTable(
    @SerializedName("table")
    val table: String,
    @SerializedName("no")
    val no: String,
    @SerializedName("effectiveDate")
    val effectiveDate: String,
    @SerializedName("rates")
    val rates: List<ExchangeRate>
)