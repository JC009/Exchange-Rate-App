package com.example.kursy.services

import com.example.kursy.domain.CurrencyTable
import com.example.kursy.domain.ExchangeRate
import org.assertj.core.api.Assertions.*
import org.junit.Test

class ExchangeServiceTest {

    val systemUnderTest: ExchangeService = ExchangeService()

    val listExchangeRate = ExchangeRate("1", "1", 1f)
    val listTable = listOf(CurrencyTable("1", "1", "1", listOf(listExchangeRate)))

    val listExchangeRate2 = listOf(ExchangeRate("1", "1", 1f),
                                                        ExchangeRate("2", "2", 2f),
                                                        ExchangeRate("3", "3", 3f))
    val listTable2 = listOf(CurrencyTable("1", "1", "1", listExchangeRate2))

    @Test
    fun `should calculate exchange value when button pressed for different rates 1`() {
        val result = systemUnderTest.calculateExchangeRate(1f, 1.5f, 0.5f)
        assertThat(result).isEqualTo(3f)
    }

    @Test
    fun `should calculate exchange values when button pressed for different rates 2`() {
        val result = systemUnderTest.calculateExchangeRate(1f, 0.5f, 0.3f)
        assertThat(result).isEqualTo(1.67f)
    }

    @Test
    fun `should calculate exchange values when button pressed for different rates 3`() {
        val result = systemUnderTest.calculateExchangeRate(1f, 0.5f, 1.5f)
        assertThat(result).isEqualTo(0.33f)
    }

    @Test
    fun `should calculate exchange values when button pressed for different rates 4`() {
        val result = systemUnderTest.calculateExchangeRate(1f, 1.5f, 2f)
        assertThat(result).isEqualTo(0.75f)
    }

    @Test
    fun `should convert currency table to pairs with filter and get 0 results` () {
        val result = systemUnderTest.convertCurrencyTableToPairs(listTable, true)
        assertThat(result.size).isEqualTo(0)
    }

    @Test
    fun `should convert currency table to pairs with filter and get 6 results` () {
        val result = systemUnderTest.convertCurrencyTableToPairs(listTable2, true)
        assertThat(result.size).isEqualTo(6)
    }

    @Test
    fun `should convert currency table to pairs without filter and get 1 result` () {
        val result = systemUnderTest.convertCurrencyTableToPairs(listTable, false)
        assertThat(result.size).isEqualTo(1)
    }

    @Test
    fun `should convert currency table to pairs without filter and get 9 results` () {
        val result = systemUnderTest.convertCurrencyTableToPairs(listTable2, false)
        assertThat(result.size).isEqualTo(9)
    }
}