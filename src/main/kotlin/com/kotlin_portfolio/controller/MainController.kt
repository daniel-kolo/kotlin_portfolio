package com.kotlin_portfolio.controller

import com.kotlin_portfolio.domain.User
import com.kotlin_portfolio.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController{

    @Autowired
    private val repo: UserRepository? = null

    @GetMapping("/test")
    fun getTest(): String {
        val user = User("Firstnameasd","Lastname",
                "test@email.com", "test_pass")

        repo!!.save(user)

        return "test"

    }


}

