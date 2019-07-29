package com.example.kursy.services

import com.example.kursy.repository.CurrencyTableRepository
import javax.inject.Inject

class CurrencyTableService @Inject constructor(
    private val repository: CurrencyTableRepository
) : CurrencyTableApi {

    override fun getTable() = repository.getCurrencyTable()
}
