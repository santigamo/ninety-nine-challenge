package com.codely.api.application

import com.codely.api.domain.Stock
import com.codely.api.domain.StockRepository
import java.time.LocalDateTime

class StockCreator(private val repository: StockRepository) {
    fun create(id: String, name: String, price: Double) {
        Stock.from(id, name, price, LocalDateTime.now()).let {
            repository.save(it)
        }
    }
}
