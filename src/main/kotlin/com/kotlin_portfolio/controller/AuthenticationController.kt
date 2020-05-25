package com.kotlin_portfolio.controller

import com.kotlin_portfolio.repo.UserRepository
import com.kotlin_portfolio.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController {

    @Autowired
    private val repo: UserRepository? = null

    @GetMapping("/getAuthenticatedUser")
    fun getAuthenticatedUser(@AuthenticationPrincipal principal: OAuth2User): String {
        println(principal)
        val login = principal.getAttributes().get("login").toString()
        val userService = UserService(repo!!)
        userService.processUserLogin(principal)
        return login
    }


    @PostMapping("/test")
    fun postTest(): String {
        return "test"
    }


}