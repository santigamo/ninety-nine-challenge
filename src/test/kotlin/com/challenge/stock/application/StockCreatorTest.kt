package com.challenge.stock.application

import com.challenge.stock.application.StockCreator
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
    private lateinit var stockCreator: StockCreator

    @BeforeEach
    fun setUp() {
        stockRepository = mockk(relaxUnitFun = true)
        stockCreator = StockCreator(stockRepository)
    }

    @Test
    fun `should create a stock successfully`() {
        givenFixedDate(fixedDate)
        stockCreator.create(id, name, price)

        thenTheStockShouldBeSaved()
    }

     @Test
     fun `should fail with invalid id`() {
         givenFixedDate(fixedDate)

         assertThrows<InvalidStockIdException> { stockCreator.create("Invalid", name, price) }
     }

    @Test
    fun `should fail with invalid name`() {
        givenFixedDate(fixedDate)

        assertThrows<InvalidStockNameException> { stockCreator.create(id, "  ", price) }
    }

    @Test
    fun `should fail with invalid price`() {
        givenFixedDate(fixedDate)

        assertThrows<InvalidStockPriceException> { stockCreator.create(id, name, -1.0) }
    }

    private fun thenTheStockShouldBeSaved() {
        verify {stockRepository.save(
            Stock.from(id, name, price, fixedDate)
        )
        }
    }

    companion object {
        private const val id = "5e5b5e91-b3fe-480f-ad93-141fd5bd55fc"
        private const val name = "Ninety Nine Challenge"
        private const val price = 2.0
        private val fixedDate = LocalDateTime.parse("2022-12-20T10:15:30")
    }
}