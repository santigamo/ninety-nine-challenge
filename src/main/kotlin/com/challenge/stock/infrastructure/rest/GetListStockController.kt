package com.challenge.stock.infrastructure.rest

import com.challenge.stock.application.StockService
import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetListStockController (private val stockService: StockService){

    @GetMapping("/stocks")
    fun execute() = stockService.all().fold(
        { ResponseEntity.ok(it) },
        { ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(it.message) }
    )
}