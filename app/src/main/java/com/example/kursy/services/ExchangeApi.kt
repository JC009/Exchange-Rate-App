package com.example.kursy.services

import com.example.kursy.domain.CurrencyPair
import com.example.kursy.domain.CurrencyTable

interface ExchangeApi {

    fun calculateExchangeRate(input: Float, rateFrom: Float, rateTo: Float): Float
    fun convertCurrencyTableToPairs(currencyTable: List<CurrencyTable>, filterDuplicates: Boolean): List<CurrencyPair>
}