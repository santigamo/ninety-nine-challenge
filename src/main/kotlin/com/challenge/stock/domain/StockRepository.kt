package com.challenge.stock.domain

interface StockRepository {
    fun save(stock: Stock)
    fun update(stock: Stock)
    fun findAll(): List<Stock>
    fun findByName(stockName: String): Result<Stock>
    fun getSharePriceForHour(stock: Stock): Result<List<Double>>
    fun getSharePriceForDay(stock: Stock): Result<List<Double>>
    fun getSharePriceForWeek(stock: Stock): Result<List<Double>>
}