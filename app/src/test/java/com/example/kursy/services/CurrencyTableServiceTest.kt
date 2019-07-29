package com.example.kursy.services

import com.example.kursy.domain.ExchangeRate
import com.example.kursy.domain.CurrencyTable
import com.example.kursy.repository.CurrencyTableRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test

class CurrencyTableServiceTest {

    val listExchangeRate = ExchangeRate("1", "1", 1f)
    val listTable = CurrencyTable("1", "1", "1", listOf(listExchangeRate))

    val mockList = listOf(listTable)
    val mockMainRepository: CurrencyTableRepository = mock{
        on {getCurrencyTable()} doReturn Observable.just(mockList)
    }
    val systemUnderTest = CurrencyTableService(mockMainRepository)

    @Test
    fun `just get the table`() {
        systemUnderTest.getTable().test().apply {
            assertComplete()
            assertValue{
                it[0] == listTable
            }
        }
    }
}