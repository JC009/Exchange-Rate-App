package com.example.kursy.mocks

import com.example.kursy.domain.CurrencyTable
import com.example.kursy.domain.ExchangeRate
import com.example.kursy.repository.CurrencyTableRepository
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class CurrencyTableRepositoryMock @Inject constructor() : CurrencyTableRepository {

    private val table = CurrencyTable(
        "D",
        "036/A/DAZN/2019",
        "2019-01-01",
        listOf()
    )

    override fun getCurrencyTable(): Observable<List<CurrencyTable>> {
        return Observable
            .interval(1, TimeUnit.SECONDS)
            .map {
                val rates = listOf(
                    ExchangeRate("pln", "PLN", 1f),
                    ExchangeRate("eur", "EUR", (45000 + (-100..100).random()).toFloat()/10000),
                    ExchangeRate("usd", "USD", (40000 + (-100..100).random()).toFloat()/10000),
                    ExchangeRate("php", "PHP", (1000 + (-100..100).random()).toFloat()/1000),
                    ExchangeRate("jpy", "JPY", (200 + (-10..10).random()).toFloat()/1000)
                )

                listOf(table.copy(rates = rates))
            }
    }
}