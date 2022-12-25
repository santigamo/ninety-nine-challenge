package com.challenge.stock.application.update

import com.challenge.stock.domain.Stock
import com.challenge.stock.domain.StockRepository
import java.time.LocalDateTime

class StockUpdater(private val repository: StockRepository) {
    fun execute(name: String, price: Double) {
        Stock.from(name, price, LocalDateTime.now()).let {
            repository.update(it)
        }
    }
}