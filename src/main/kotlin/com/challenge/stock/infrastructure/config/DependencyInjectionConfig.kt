package com.challenge.stock.infrastructure.config

import com.challenge.stock.application.StockService
import com.challenge.stock.domain.StockRepository
import com.challenge.stock.infrastructure.persistence.InMemoryStockRepository
import com.challenge.stock.infrastructure.scheduled.PriceChecker
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectionConfig {
    @Bean
    fun stockRepository() = InMemoryStockRepository()
    @Bean
    fun stockService(stockRepository: StockRepository) = StockService(stockRepository)
    @Bean
    fun priceChecker(stockService: StockService) = PriceChecker(stockService)


}