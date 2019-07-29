package com.example.kursy.services

import com.example.kursy.domain.CurrencyTable
import io.reactivex.Observable

interface CurrencyTableApi {

    fun getTable(): Observable<List<CurrencyTable>>
}