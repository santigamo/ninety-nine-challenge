package com.challenge.stock.infrastructure.rest

import com.challenge.stock.application.StockResponse
import com.challenge.stock.application.StockService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class GetListStockControllerTest {
    private lateinit var stockService: StockService
    private lateinit var controller: GetListStockController

    @BeforeEach
    fun setUp() {
        stockService = mockk()
        controller = GetListStockController(stockService)
    }

    @Test
    fun `Should return a successful response`() {
        // Given
        val companies = listOf(
            StockResponse( "Acme Corporation", 123.45, LocalDateTime.now()),
            StockResponse( "XYZ Inc.", 234.56, LocalDateTime.now()),
        )
        every { stockService.all()} returns Result.success(companies)

        // When
        val response = controller.execute()

        // Then
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(companies, response.body)
    }

    @Test
    fun `Should return a internal server error response`() {
        // Given
        every { stockService.all() } returns Result.failure(NoSuchElementException("Error fetching stocks"))

        // When
        val response = controller.execute()

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.statusCode)
        assertEquals("Error fetching stocks", response.body)
    }
}