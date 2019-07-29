package com.example.kursy.services

import com.example.kursy.domain.CurrencyPair
import com.example.kursy.domain.CurrencyTable
import com.example.kursy.domain.ExchangeRate
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class ExchangeService @Inject constructor(): ExchangeApi {

    override fun calculateExchangeRate(input: Float, rateFrom: Float, rateTo: Float): Float =
            (input * rateFrom / rateTo).format()

    override fun convertCurrencyTableToPairs(currencyTable: List<CurrencyTable>, filterDuplicates: Boolean) =
        combineExchangeRatePairs(currencyTable, filterDuplicates)
            .map { CurrencyPair(it.first, it.second, calculateExchangeRate(it)) }

    private fun calculateExchangeRate(pair: Pair<ExchangeRate, ExchangeRate>) = pair.first.mid / pair.second.mid

    private fun combineExchangeRatePairs(currencyTable: List<CurrencyTable>, filterDuplicates: Boolean): List<Pair<ExchangeRate, ExchangeRate>> {
        val pairs = currencyTable
            .map { first ->
                first.rates.flatMap { map ->
                    currencyTable.flatMap { cTable ->
                        cTable.rates.map {
                            Pair(map, it)
                        }

                    }
                }
            }.flatten()

        return if (filterDuplicates){
            filterDuplicates(pairs)
        } else {
            pairs
        }
    }

    private fun filterDuplicates(pairs: List<Pair<ExchangeRate, ExchangeRate>>): List<Pair<ExchangeRate, ExchangeRate>> {
        var list: List<Pair<ExchangeRate, ExchangeRate>> = emptyList()
        var setIndex = 0
        val size = (Math.sqrt(pairs.size.toDouble())).toInt()

        for (listIndex in 0..pairs.size - 1) {
            if (listIndex % size > setIndex) {
                list += pairs[listIndex]
            }
            if (listIndex % size == 4) {
                setIndex++
            }
        }

        return list
    }

    private fun Float.format(): Float =
        DecimalFormat("#.##").apply { roundingMode = RoundingMode.HALF_UP }
            .format(this)
            .toFloat()
}