package com.example.kursy.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule{

    @Provides
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    fun providesRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.nbp.pl/api/exchangerates/tables/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}