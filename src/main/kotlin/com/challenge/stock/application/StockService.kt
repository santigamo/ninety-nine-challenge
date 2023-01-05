package com.challenge.stock.application

import com.challenge.stock.domain.Stock
import com.challenge.stock.domain.StockRepository
import java.time.LocalDateTime

class StockService (private val repository: StockRepository) {

    fun create(name: String, price: Double) {
        Stock.from(name, price, LocalDateTime.now()).let {
            repository.save(it)
        }
    }
    fun update(name: String, price: Double) {
        Stock.from(name, price, LocalDateTime.now()).let {
            repository.update(it)
        }
    }

    fun byName(name: String): Result<StockResponse> =
        repository.findByName(name)
            .fold(
                onSuccess = { Result.success(StockResponse.fromStock(it)) },
                onFailure = { Result.failure(it) }
            )


    fun all(): Result<List<StockResponse>> {
        val stockList = repository.findAll()
        return Result.
        success(stockList.map { StockResponse.fromStock(it) }).
        onFailure { return Result.failure(it) }
    }

    fun getCompanyTimeSeries(name: String, type: String): Result<List<Double>> {
        val stock = repository.findByName(name).getOrElse { return Result.success(emptyList()) }
        return when (type) {
            "hourly" -> repository.getSharePriceForHour(stock)
            "daily" -> repository.getSharePriceForDay(stock)
            "weekly" -> repository.getSharePriceForWeek(stock)
            else -> throw NoSuchElementException("Invalid time series type")
        }
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