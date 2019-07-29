package com.example.kursy.di.modules

import com.example.kursy.view.exchangeList.ExchangeListContract
import com.example.kursy.view.exchangeList.ExchangeListPresenter
import com.example.kursy.view.main.MainContract
import com.example.kursy.view.main.MainPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class PresenterModule {

    @Binds
    abstract fun providesMainContractPresenter(presenter: MainPresenter): MainContract.Presenter

    @Binds
    abstract fun providesExchangeContractPresenter(presenter: ExchangeListPresenter): ExchangeListContract.Presenter
}