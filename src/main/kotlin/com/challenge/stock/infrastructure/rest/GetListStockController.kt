package com.challenge.stock.infrastructure.rest

import com.challenge.stock.application.find.StockFinder
import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetListStockController (private val stockFinder: StockFinder){

    @GetMapping("/stock")
    fun execute() = stockFinder.all().fold(
        { ResponseEntity.ok(it) },
        { ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(it.message) }
    )
}