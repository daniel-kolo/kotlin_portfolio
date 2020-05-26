package com.kotlin_portfolio

import com.kotlin_portfolio.domain.User
import com.kotlin_portfolio.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
open class KotlinPortfolioApplication


fun main(args: Array<String>) {
    runApplication<KotlinPortfolioApplication>(*args)


}
