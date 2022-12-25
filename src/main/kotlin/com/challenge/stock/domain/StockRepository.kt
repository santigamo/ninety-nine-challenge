package com.challenge.stock.domain

interface StockRepository {
    fun save(stock: Stock)
    fun update(stock: Stock)
    fun findAll(): List<Stock>
    fun findByName(stockName: String): Result<Stock>
}