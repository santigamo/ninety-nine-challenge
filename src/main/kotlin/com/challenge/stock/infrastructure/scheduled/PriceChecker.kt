package com.challenge.stock.infrastructure.scheduled

import com.challenge.stock.application.update.StockUpdater
import java.util.*
import javax.annotation.PostConstruct
import kotlin.random.Random

private const val PERIOD = 20000L

class PriceChecker (private val stockUpdater: StockUpdater){

    @PostConstruct
    fun start() {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                ThirdPartyApi.fetchCompanies().forEach {
                    stockUpdater.execute(it.name, it.price)
                }
//                companyList.forEach { company ->
//                    stockUpdater.execute(company, Random.nextDouble(0.1, 500.1))
//                }
            }
        }, 0, PERIOD)
    }
}

class ThirdPartyApi {
    companion object {
        fun fetchCompanies(): List<Company> {
            return listOf(
                Company("AAPL", Random.nextDouble(0.1, 500.1)),
                Company("MSFT", Random.nextDouble(0.1, 500.1)),
                Company("GOOG", Random.nextDouble(0.1, 500.1))
            )
        }
    }
}

data class Company constructor(
    val name: String,
    val price: Double
) {
    companion object {
        fun from(name: String, price: Double) = Company(name, price)
    }
}
)