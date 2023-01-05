package com.challenge.stock.infrastructure.rest

import com.challenge.stock.application.StockService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class GetStockByTimeSeriesTest {
    private lateinit var stockService: StockService
    private lateinit var controller: GetStockByTimeSeries

    @BeforeEach
    fun setUp() {
        stockService = mockk()
        controller = GetStockByTimeSeries(stockService)
    }

    @Test
    fun `should return a successful response`() {
        // Given
        val timeSeries = listOf(123.45, 122.34, 121.56)
        every { stockService.getCompanyTimeSeries("ACME", "hourly") } returns Result.success(timeSeries)

        // When
        val response = controller.execute("ACME", "hourly")

        // Then
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(timeSeries, response.body)
    }

    @Test
    fun `should return a not found response`() {
        // Given
        every { stockService.getCompanyTimeSeries("ACME", "hourly") } returns Result.failure(NoSuchElementException("Stock not found"))
        // When
        val response = controller.execute("ACME", "hourly")

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun `should return a internal server error response`() {
        // Given
        every { stockService.getCompanyTimeSeries("ACME", "hourly") } returns Result.failure(Exception("Error fetching stocks"))

        // When
        val result = controller.execute("ACME", "hourly")

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.statusCode)
    }
}