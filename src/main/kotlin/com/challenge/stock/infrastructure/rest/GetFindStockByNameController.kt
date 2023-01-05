package com.challenge.stock.infrastructure.rest

import com.challenge.stock.application.StockService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class GetFindStockByNameController (private val stockService: StockService){

    @GetMapping("/stocks/{name}")
    fun execute(
        @PathVariable name: String
    ) = stockService.byName(name).fold(
        onSuccess = {
            ResponseEntity.ok(it)
        },
        onFailure = {
            when(it) {
                is NoSuchElementException -> ResponseEntity.notFound().build()
                else -> ResponseEntity.internalServerError().build()
            }
        }
    )
}