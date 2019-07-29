package com.example.kursy.di

import com.example.kursy.view.main.MainActivity
import com.example.kursy.di.modules.*
import com.example.kursy.view.exchangeList.ExchangeListActivity
import dagger.Component

@Component(modules = [
    PresenterModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    ServicesModule::class,
    RxModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: ExchangeListActivity)
}