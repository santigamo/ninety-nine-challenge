package com.challenge.stock.domain

import java.time.LocalDateTime

data class StockName(val value: String) {
    init {
        validate()
    }

    private fun validate() {
        if (value.isEmpty() || value.isBlank()) {
            throw InvalidStockNameException(value)
        }
    }
}

data class StockPrice(val value: Double) {
    init {
        validate()
    }

    private fun validate() {
        if (value <= 0) {
            throw InvalidStockPriceException(value)
        }
    }
}

data class Stock private constructor(
    val name: StockName,
    val price: StockPrice,
    val updateTime: LocalDateTime
) {
    companion object {
        fun from(name: String, price: Double, updateTime: LocalDateTime) =
            Stock(StockName(name), StockPrice(price), updateTime)
    }
}


