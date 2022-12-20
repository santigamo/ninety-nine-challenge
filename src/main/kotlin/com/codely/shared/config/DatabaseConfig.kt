package com.codely.shared.config

import com.codely.api.infrastructure.persistence.DatabaseConnectionProperties
import com.codely.api.infrastructure.persistence.InMemoryStockRepository
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "database.connection")
    fun databaseConnectionProperties() = DatabaseConnectionProperties()


    @Bean
    fun stockRepository(databaseConnectionProperties: DatabaseConnectionProperties) = InMemoryStockRepository(databaseConnectionProperties)
}