package com.example.kursy.view.exchangeList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kursy.R
import com.example.kursy.domain.CurrencyPair

class ExchangeListAdapter(
    val pairList: MutableList<CurrencyPair>,
    val context: Context) : RecyclerView.Adapter<ExchangeListViewHolder>() {

    override fun onBindViewHolder(holder: ExchangeListViewHolder, position: Int) {
        holder.textViewCurrencyFrom.text = "1 " + pairList[position].rateFrom.currency
        holder.textViewCurrencyTo.text = "${pairList[position].rate} ${pairList[position].rateTo.currency}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeListViewHolder {
        return ExchangeListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recycler_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = pairList.size
}