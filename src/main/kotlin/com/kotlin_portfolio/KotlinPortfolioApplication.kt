package com.kotlin_portfolio

import com.kotlin_portfolio.domain.User
import com.kotlin_portfolio.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinPortfolioApplication

@Autowired
private lateinit var repo: UserRepository

fun main(args: Array<String>) {
    runApplication<KotlinPortfolioApplication>(*args)
    val user = User("Firstname","Lastname",
                        "test@email.com", "test_pass")

    repo.save(user)
}
