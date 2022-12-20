package com.codely.api.infrastructure.persistence

import com.codely.api.domain.Stock
import com.codely.api.domain.StockRepository

class DatabaseConnectionProperties(var username: String = "", var password: String = "")
class InMemoryStockRepository(private val connectionProperties: DatabaseConnectionProperties) : StockRepository {
    override fun save(stock: Stock) {
        TODO("Not yet implemented")
    }
}