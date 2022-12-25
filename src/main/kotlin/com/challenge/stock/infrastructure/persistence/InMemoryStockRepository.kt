package com.challenge.stock.infrastructure.persistence

import com.challenge.stock.domain.Stock
import com.challenge.stock.domain.StockRepository
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
}