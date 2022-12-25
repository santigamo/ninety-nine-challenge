package com.challenge.stock.infrastructure.rest

import com.challenge.stock.application.find.StockFinder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class GetFindStockByNameController (private val stockFinder: StockFinder){

    @GetMapping("/stock/{name}")
    fun execute(
        @PathVariable name: String
    ) = stockFinder.byName(name).fold(
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