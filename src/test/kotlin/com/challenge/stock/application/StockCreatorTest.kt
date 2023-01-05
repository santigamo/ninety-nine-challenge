package com.challenge.stock.application

import com.challenge.stock.domain.*
import com.challenge.stock.BaseTest
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class StockCreatorTest : BaseTest() {

    private lateinit var stockRepository: StockRepository
    private lateinit var stockService: StockService

    @BeforeEach
    fun setUp() {
        stockRepository = mockk(relaxUnitFun = true)
        stockService = StockService(stockRepository)
    }

    @Test
    fun `should create a stock successfully`() {
        givenFixedDate(fixedDate)
        stockService.create(name, price)

        thenTheStockShouldBeSaved()
    }

    @Test
    fun `should fail with invalid name`() {
        givenFixedDate(fixedDate)

        assertThrows<InvalidStockNameException> { stockService.create("  ", price) }
    }

    @Test
    fun `should fail with invalid price`() {
        givenFixedDate(fixedDate)

        assertThrows<InvalidStockPriceException> { stockService.create(name, -1.0) }
    }

    private fun thenTheStockShouldBeSaved() {
        verify {stockRepository.save(
            Stock.from(name, price, fixedDate)
        )
        }
    }

    companion object {
        private const val name = "Ninety Nine Challenge"
        private const val price = 2.0
        private val fixedDate = LocalDateTime.parse("2022-12-20T10:15:30")
    }
}