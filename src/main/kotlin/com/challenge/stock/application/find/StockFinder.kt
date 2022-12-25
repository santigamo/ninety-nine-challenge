package com.challenge.stock.application.find

import com.challenge.stock.domain.Stock
import com.challenge.stock.domain.StockRepository
import java.time.LocalDateTime

class StockFinder(private val stockRepository: StockRepository) {

    fun byName(name: String): Result<StockResponse> =
        stockRepository.findByName(name)
            .fold(
                onSuccess = { Result.success(StockResponse.fromStock(it)) },
                onFailure = { Result.failure(it) }
            )


    fun all(): Result<List<StockResponse>> {
        val stockList = stockRepository.findAll()
        return Result.
            success(stockList.map { StockResponse.fromStock(it) }).
            onFailure { return Result.failure(it) }
    }
}


data class StockResponse(val name: String, val price: Double, val updatedAt: LocalDateTime) {
    companion object {
        fun fromStock(stock: Stock) = with(stock) {
            StockResponse(
                name = name.value, price = price.value, updateTime
            )
        }
    }
}