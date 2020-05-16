package com.kotlin_portfolio

import com.kotlin_portfolio.domain.User
import com.kotlin_portfolio.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Repository
import org.springframework.boot.autoconfigure.security.oauth2.*;

@SpringBootApplication
open class KotlinPortfolioApplication

fun main(args: Array<String>) {
    runApplication<KotlinPortfolioApplication>(*args)

    /*
    val (request, response, result) = tokenEndpoint.httpPost(listOf(
            "grant_type" to "client_credentials",
            "client_id" to clientId,
            "client_secret" to clientSecret,
            "scope" to scopes.joinToString(" ")))
            .responseString()

    when (result) {
        is Result.Success -> {
            // handle response
        }
        is Result.Failure -> {
            // handle error
        }
    }*/

}
