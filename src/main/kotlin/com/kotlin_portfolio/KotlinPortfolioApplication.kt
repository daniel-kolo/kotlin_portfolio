package com.kotlin_portfolio

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
open class KotlinPortfolioApplication

fun main(args: Array<String>) {
    runApplication<KotlinPortfolioApplication>(*args)
}
