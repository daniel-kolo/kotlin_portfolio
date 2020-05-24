package com.kotlin_portfolio.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
    fun getAuthenticatedUser(@AuthenticationPrincipal principal: OAuth2User): String {
        val login = principal.getAttributes().get("login").toString()
        val userService = UserService(repo!!)
        userService.processUserLogin(principal)
        return login
    }

    @GetMapping("/getStockSymbolList")
    fun getStockSymbolList(): String {
        val gson = Gson()
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()

        val tut = listOf("Tut #1", "bezkoder", "asd")

        val jsonTut: String = gson.toJson(tut)
        println(jsonTut)

        val jsonTutPretty: String = gsonPretty.toJson(tut)
        println(jsonTutPretty)
        //return iex.stockSymbolList.map()
        return jsonTut
    }

    @GetMapping("/getStockList")
    fun testJs(): String {
        val gson = Gson()
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()

        if (iex == null){
            return gson.toJson(mutableListOf<String>("placeholder1", "placeholder2"))
        }
        val stocks = iex!!.stockSymbolList
        val jsonStocks: String = gson.toJson(stocks)
        return jsonStocks
    }



}

