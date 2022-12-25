package com.challenge.stock.application

import com.challenge.stock.domain.Stock
import com.challenge.stock.domain.StockRepository
import java.time.LocalDateTime

class StockCreator(private val repository: StockRepository) {
    fun create(name: String, price: Double) {
        Stock.from(name, price, LocalDateTime.now()).let {
            repository.save(it)
        }
    }
}
