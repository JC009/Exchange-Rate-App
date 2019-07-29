package com.example.kursy.view.exchangeList

import com.example.kursy.domain.CurrencyPair

interface ExchangeListContract {
    interface View {
        fun showList(exchangeRates: List<CurrencyPair>)
        fun swapView()
    }

    interface Presenter {
        fun onAttached (view: View, filter: Boolean)
        fun onButtonPressed()
        fun refreshList(view: View, filter: Boolean)
    }
}