package com.challenge.stock.infrastructure.config

import com.challenge.stock.application.StockCreator
import com.challenge.stock.application.find.StockFinder
import com.challenge.stock.application.update.StockUpdater
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
    fun stockCreator(stockRepository: StockRepository) = StockCreator(stockRepository)
    @Bean
    fun stockUpdater(stockRepository: StockRepository) = StockUpdater(stockRepository)

    @Bean
    fun stockFinder(stockRepository: StockRepository) = StockFinder(stockRepository)
    @Bean
    fun priceChecker(stockUpdater: StockUpdater) = PriceChecker(stockUpdater)


}