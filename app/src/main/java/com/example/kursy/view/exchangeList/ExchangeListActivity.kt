package com.example.kursy.view.exchangeList

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import com.example.kursy.R
import com.example.kursy.di.DaggerAppComponent
import com.example.kursy.domain.CurrencyPair
import com.example.kursy.view.main.MainActivity

import kotlinx.android.synthetic.main.activity_exchange_list.*
import javax.inject.Inject

class ExchangeListActivity : AppCompatActivity(), ExchangeListContract.View {

    private val pairs: MutableList<CurrencyPair> = mutableListOf()
    private var filterDuplicates: Boolean = false

    @Inject
    lateinit var presenter: ExchangeListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_list)

        my_recycler_view.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = ExchangeListAdapter(
                pairs,
                this
            )
        }

        buttonViewSwap.setOnClickListener {
            swapView()
        }

        filterButton.setOnClickListener {
            filterDuplicates = !filterDuplicates
            presenter.refreshList(this, filterDuplicates)

        }

        presenter.onAttached(this, filterDuplicates)
    }

    override fun showList(exchangeRates: List<CurrencyPair>) {
        pairs.apply {
            clear()
            addAll(exchangeRates)
        }
        my_recycler_view.adapter?.notifyDataSetChanged()
    }

    override fun swapView() {
        Intent(this, MainActivity::class.java).let {
            startActivity(it)
        }
    }
}