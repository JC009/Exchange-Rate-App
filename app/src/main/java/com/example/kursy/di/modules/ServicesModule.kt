package com.example.kursy.di.modules

import com.example.kursy.services.CurrencyTableApi
import com.example.kursy.services.CurrencyTableService
import com.example.kursy.services.ExchangeApi
import com.example.kursy.services.ExchangeService
import dagger.Binds
import dagger.Module

@Module
abstract class ServicesModule {

    @Binds
    abstract fun bindsCurrencyApi(service: CurrencyTableService): CurrencyTableApi

    @Binds
    abstract fun bindsExchangeApi(service: ExchangeService): ExchangeApi
}