package com.challenge.stock.infrastructure.rest

import com.challenge.stock.application.StockCreator
import com.challenge.stock.domain.InvalidStockIdException
import com.challenge.stock.infrastructure.rest.CreateStockRequest
import com.challenge.stock.infrastructure.rest.PostCreateStockController
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals

class PostCreateStockControllerTest {

    private lateinit var stockCreator: StockCreator
    private lateinit var controller: PostCreateStockController

    @BeforeEach
    fun setUp() {
        stockCreator = mockk()
        controller = PostCreateStockController(stockCreator)
    }

    @Test
    fun `Should return a successful response`() {
        // Given
        every { stockCreator.create(any(), any(), any()) } returns Unit

        // When
        val response = controller.execute(CreateStockRequest("id", "name", 1.0))

        // Then
        assertEquals(ResponseEntity.ok().build(), response)
    }

    @Test
    fun `Should return a bad request response`() {
        // Given
        every { stockCreator.create(any(), any(), any()) } throws InvalidStockIdException("invalid", null)

        // When
        val response = controller.execute(CreateStockRequest("id", "name", 1.0))

        // Then
        assertEquals(ResponseEntity.badRequest().body("Invalid stock id: invalid"), response)
    }
}