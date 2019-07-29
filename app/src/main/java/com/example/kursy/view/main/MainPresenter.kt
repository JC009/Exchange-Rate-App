package com.example.kursy.view.main

import com.example.kursy.di.annotations.SchedulerType
import com.example.kursy.di.annotations.Type
import com.example.kursy.doNothing
import com.example.kursy.domain.CurrencyTable
import com.example.kursy.services.CurrencyTableApi
import com.example.kursy.services.ExchangeApi
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val apiCurrency: CurrencyTableApi,
    private val apiExchange: ExchangeApi,
    @SchedulerType(Type.MAIN_THREAD) private val observeOnScheduler: Scheduler,
    @SchedulerType(Type.IO) private val subscribeOnScheduler: Scheduler
) : MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun onAttached(view: MainContract.View) {
        displayExchangeRate(apiCurrency.getTable())
        this.view = view
    }

    override fun onButtonPressed(input: String, currencyFrom: Float, currencyTo: Float) {
        if (!input.isBlank()) {
            val amount = apiExchange.calculateExchangeRate(
                input = input.toFloat(),
                rateFrom = currencyFrom,
                rateTo = currencyTo
            )
            view.showConversionResult(amount)
        }
    }

    private fun displayExchangeRate(observable: Observable<List<CurrencyTable>>){
        observable
            .observeOn(observeOnScheduler)
            .subscribeOn(subscribeOnScheduler)
            .subscribe({
                view.showList(it)
            }, { doNothing() })
    }
}