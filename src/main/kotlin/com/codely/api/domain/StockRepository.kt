package com.codely.api.domain

interface StockRepository {
    fun save(stock: Stock)
}