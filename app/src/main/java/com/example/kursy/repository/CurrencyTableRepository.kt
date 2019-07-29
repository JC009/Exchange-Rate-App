package com.example.kursy.repository

import com.example.kursy.domain.CurrencyTable
import io.reactivex.Observable
import retrofit2.http.GET

interface CurrencyTableRepository {

    @GET("A?format=json")
    fun getCurrencyTable(): Observable<List<CurrencyTable>>
}