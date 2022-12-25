package com.challenge.stock.domain

sealed class StockExceptions(override val message: String, override val cause: Throwable? = null) : Exception(message, cause)

data class InvalidStockIdException(val id: String,override val cause: Throwable?) : StockExceptions("Invalid stock id: $id", cause)
data class InvalidStockNameException(val name: String) : StockExceptions("The name $name is not valid stock name")
data class InvalidStockPriceException(val price: Double) : StockExceptions("The price $price is not valid stock price")