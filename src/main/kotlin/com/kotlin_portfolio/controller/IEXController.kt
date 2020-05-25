package com.kotlin_portfolio.controller

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kotlin_portfolio.domain.IEX.IEXWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IEXController {

    @Autowired
    private val iex : IEXWrapper? = null

    @GetMapping("/getTableTestList")
    fun getStockSymbolList(): String {
        val gson = Gson()
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()

        val tut = listOf("Tut #1", "bezkoder", "asd", "asd", "asd", "asd")

        val jsonTut: String = gson.toJson(tut)
        //println(jsonTut)
        //val jsonTutPretty: String = gsonPretty.toJson(tut)
        //println(jsonTutPretty)
        //return iex.stockSymbolList.map()
        return jsonTut
    }

    @GetMapping("/getStockList")
    fun testJs(): String {
        val gson = Gson()
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()

        if (iex == null){
            return gson.toJson(mutableListOf<String>("APPLE INC", "TESLA INC"))
        }
        val stocks = iex!!.stockSymbolList
        val jsonStocks: String = gson.toJson(stocks)
        return jsonStocks
    }

}