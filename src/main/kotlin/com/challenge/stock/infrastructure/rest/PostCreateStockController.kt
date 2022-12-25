package com.challenge.stock.infrastructure.rest

import com.challenge.stock.application.StockCreator
import com.challenge.stock.domain.InvalidStockNameException
import com.challenge.stock.domain.InvalidStockPriceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostCreateStockController(private val stockCreator: StockCreator) {
    @PostMapping("/stock")
    fun execute(
        @RequestBody request: CreateStockRequest
    ): ResponseEntity<String> {
        return try {
            stockCreator.create(request.name, request.price)
            ResponseEntity.ok().build()
        } catch (exception: Throwable) {
            when (exception) {
                is InvalidStockPriceException,
                is InvalidStockNameException -> ResponseEntity.status(
                        HttpStatus.BAD_REQUEST
                    ).body(exception.message)

                else -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred")
            }
        }
    }
}

data class CreateStockRequest(val id: String, val name: String, val price: Double)