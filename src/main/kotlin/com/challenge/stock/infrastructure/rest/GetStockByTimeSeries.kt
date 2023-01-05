package com.challenge.stock.infrastructure.rest


import com.challenge.stock.application.StockService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GetStockByTimeSeries (private val stockService: StockService){

    @GetMapping("/stocks/{name}/time-series")
    fun execute(
        @PathVariable name: String,
        @RequestParam type: String,
    ) = stockService.getCompanyTimeSeries(name, type).fold(
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