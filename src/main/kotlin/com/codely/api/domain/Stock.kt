package com.codely.api.domain

import java.time.LocalDateTime
import java.util.UUID

data class StockId(val value: UUID) {
    companion object {
        fun fromString(id: String) = try {
            StockId(UUID.fromString(id))
        } catch (exception: Exception) {
            throw InvalidStockIdException(id, exception)
        }
    }
}

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
    val id: StockId,
    val name: StockName,
    val price: StockPrice,
    val updateTime: LocalDateTime
) {
    companion object {
        fun from(id: String, name: String, price: Double, updateTime: LocalDateTime) =
            Stock(StockId.fromString(id), StockName(name), StockPrice(price), updateTime)
    }
}


