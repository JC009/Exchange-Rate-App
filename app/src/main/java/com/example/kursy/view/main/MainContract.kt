package com.example.kursy.view.main

import com.example.kursy.domain.CurrencyTable

interface MainContract {
    interface View {

        fun showList(exchangeRate: List<CurrencyTable>)
        fun showConversionResult(amount: Float)
    }

    interface Presenter {

        fun onAttached(view: View)
        fun onButtonPressed(input: String, currencyFrom: Float, currencyTo: Float)
    }
}