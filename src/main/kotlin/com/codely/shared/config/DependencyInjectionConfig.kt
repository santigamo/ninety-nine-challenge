package com.codely.shared.config

import com.codely.api.application.StockCreator
import com.codely.api.domain.StockRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectionConfig {
    @Bean
    fun stockCreator(stockRepository: StockRepository) = StockCreator(stockRepository)
}