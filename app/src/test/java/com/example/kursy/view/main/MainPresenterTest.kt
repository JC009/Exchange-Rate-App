package com.example.kursy.view.main

import com.example.kursy.domain.CurrencyTable
import com.example.kursy.domain.ExchangeRate
import com.example.kursy.services.CurrencyTableApi
import com.example.kursy.services.ExchangeApi
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class MainPresenterTest {

    val listExchangeRate = ExchangeRate("1", "1", 1f)
    val listTable = CurrencyTable("1", "1", "1", listOf(listExchangeRate))

    val mockListTable = listOf(listTable)
    val mockMainApi: CurrencyTableApi = mock{
        on { getTable() } doReturn Observable.just(mockListTable)
    }
    val mockView = mock<MainContract.View>()
    val mockFloat: Float = 112f
    val mockExchangeApi: ExchangeApi = mock{
        on { calculateExchangeRate(1f,1f,2f) } doReturn(mockFloat)
    }

    val systemUnderTest: MainContract.Presenter = MainPresenter(
        mockMainApi,
        mockExchangeApi,
        Schedulers.trampoline(),
        Schedulers.trampoline()
    )

    @Test
    fun `should get table of currency when attached`() {
        systemUnderTest.onAttached(mock())
        verify(mockMainApi).getTable()
    }

    @Test
    fun `should calculate something on button pressed` () {
        systemUnderTest.onAttached(mockView)
        systemUnderTest.onButtonPressed("1", 1f, 2f)
        verify(mockExchangeApi).calculateExchangeRate(1f, 1f,2f)
        verify(mockView).showConversionResult(mockFloat)
    }

    @Test
    fun `should do nothing on button pressed when input is null or blank` () {
        systemUnderTest.onButtonPressed("", 1f, 2f)
        verifyZeroInteractions(mockExchangeApi)
    }
}