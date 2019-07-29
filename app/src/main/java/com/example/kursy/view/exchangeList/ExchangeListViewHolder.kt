package com.example.kursy.view.exchangeList

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.recycler_view.view.*

class ExchangeListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textViewCurrencyFrom = view.textViewCurrencyFrom
    val textViewCurrencyTo = view.textViewCurrencyTo
}