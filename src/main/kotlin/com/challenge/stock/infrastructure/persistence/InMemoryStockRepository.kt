package com.challenge.stock.infrastructure.persistence

import com.challenge.stock.domain.Stock
import com.challenge.stock.domain.StockRepository
import java.time.LocalDateTime
import kotlin.random.Random

class InMemoryStockRepository : StockRepository {

    private val stockInventory = mutableMapOf<String, Stock>()
    override fun save(stock: Stock) {
        stockInventory[stock.name.toString()] = stock
    }

    override fun update(stock: Stock) {
        stockInventory[stock.name.value].let {
            stockInventory[stock.name.value] = stock
        }
    }

    override fun findAll(): List<Stock> {
        return stockInventory.values.toList()
    }

    override fun findByName(stockName: String): Result<Stock> {
        return stockInventory[stockName]?.let {
            Result.success(it)
        } ?: Result.failure(NoSuchElementException("Stock with name $stockName not found"))
    }

    override fun getSharePriceForHour(stock: Stock): Result<List<Double>> {
        val now = LocalDateTime.now()
        return (0..23).map {
            // Fetch share price for the given hour
            val hour = now.minusHours(it.toLong())
            val sharePrice = MockedTimeSeriesPrices.getSharePriceFor(stock.name.value, hour)
            sharePrice ?: stock.price.value // if share price not available, return current share price
        }.let { Result.success(it) }
    }

    override fun getSharePriceForDay(stock: Stock): Result<List<Double>> {
        val now = LocalDateTime.now()
        return (0..6).map {
            // Fetch share price for the given day
            val day = now.minusDays(it.toLong())
            val sharePrice = MockedTimeSeriesPrices.getSharePriceFor(stock.name.value, day)
            sharePrice ?: stock.price.value // if share price not available, return current share price
        }.let { Result.success(it) }
    }

    override fun getSharePriceForWeek(stock: Stock): Result<List<Double>> {
        val now = LocalDateTime.now()
        return (0..3).map {
            // Fetch share price for the given week
            val week = now.minusWeeks(it.toLong())
            val sharePrice = MockedTimeSeriesPrices.getSharePriceFor(stock.name.value, week)
            sharePrice ?: stock.price.value // if share price not available, return current share price
        }.let { Result.success(it) }
    }


}
class MockedTimeSeriesPrices {
    companion object {
        fun getSharePriceFor(name: String, time : LocalDateTime) = Random.nextDouble(0.1, 500.1)
    }
}
