package com.example.kursy.view.exchangeList

import com.example.kursy.di.annotations.SchedulerType
import com.example.kursy.di.annotations.Type
import com.example.kursy.doNothing
import com.example.kursy.domain.CurrencyPair
import com.example.kursy.services.CurrencyTableApi
import com.example.kursy.services.ExchangeApi
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ExchangeListPresenter @Inject constructor(
    private val currencyApi: CurrencyTableApi,
    private val exchangeApi: ExchangeApi,
    @SchedulerType(Type.MAIN_THREAD) private val observeOnScheduler: Scheduler,
    @SchedulerType(Type.IO) private val subscribeOnScheduler: Scheduler
)  : ExchangeListContract.Presenter {

    private lateinit var view: ExchangeListContract.View

    private lateinit var disposable: Disposable

    override fun onAttached(view: ExchangeListContract.View, filter: Boolean) {
        currencyApi
            .getTable()
            .map { exchangeApi.convertCurrencyTableToPairs(it, filter) }
            .let { displayList(it) }

        currencyApi.getTable()

        this.view = view
    }

    override fun refreshList(view: ExchangeListContract.View, filter: Boolean) {
        disposable.dispose()
        currencyApi
            .getTable()
            .map { exchangeApi.convertCurrencyTableToPairs(it, filter) }
            .let { displayList(it) }

        currencyApi.getTable()

        this.view = view
    }

    override fun onButtonPressed() {
        view.swapView()
    }

    private fun displayList(observable: Observable<List<CurrencyPair>>){
        disposable = observable
            .observeOn(observeOnScheduler)
            .subscribeOn(subscribeOnScheduler)
            .subscribe({
                view.showList(it)
            }, { doNothing() })
    }
}