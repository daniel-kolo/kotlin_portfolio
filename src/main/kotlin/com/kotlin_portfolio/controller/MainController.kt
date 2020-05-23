package com.kotlin_portfolio.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.kotlin_portfolio.domain.IEX.IEXWrapper
import com.kotlin_portfolio.domain.User
import com.kotlin_portfolio.repo.UserRepository
import com.kotlin_portfolio.service.UserService
import org.json.JSONObject
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

    @Autowired
    private val iex : IEXWrapper? = null

    @GetMapping("/getAuthenticatedUser")
    fun user(@AuthenticationPrincipal principal: OAuth2User): String {
        val login = principal.getAttributes().get("login").toString()
        val userService = UserService(repo!!)
        userService.processUserLogin(principal)
        //iex!!.getStockPrices()
        return login
    }
}

