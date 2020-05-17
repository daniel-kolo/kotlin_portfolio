package com.kotlin_portfolio.controller

import com.kotlin_portfolio.domain.User
import com.kotlin_portfolio.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class MainController{

    @Autowired
    private val repo: UserRepository? = null

    /*
    @GetMapping("/callback")
    fun callback1(@AuthenticationPrincipal principal: OAuth2User): String {
        println("CALLBACK")
        return "github authenticated"
    }
    */

    @GetMapping("/getAuthenticatedUser")
    fun user(@AuthenticationPrincipal principal: OAuth2User): String {
        val login = principal.getAttributes().get("login").toString()
        return login
    }

    @GetMapping("/test")
    fun getTest(): String {
        val user = User("Firstnameasd","Lastname",
                "test@email.com", "test_pass")

        repo!!.save(user)

        return "test"
    }
}

