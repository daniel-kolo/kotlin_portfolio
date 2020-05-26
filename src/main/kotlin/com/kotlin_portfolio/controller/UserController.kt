package com.kotlin_portfolio.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kotlin_portfolio.DTO.StockTableRow
import com.kotlin_portfolio.DTO.stockQuantityChangeDTO
import com.kotlin_portfolio.domain.IEX.IEXWrapper
import com.kotlin_portfolio.domain.User
import com.kotlin_portfolio.repo.PortfolioRepository
import com.kotlin_portfolio.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import kotlin.system.exitProcess

@RestController
class UserController {

    @Autowired
    private val repo: UserRepository? = null

    @Autowired
    private val pRepo: PortfolioRepository? = null

    @Autowired
    private val iex: IEXWrapper? = null

    fun checkUser(userName : String): User{
        var user = repo!!.findByUserName(userName)
        if (user == null){
            user = User(userName, "")
            repo!!.save(user)
        }
        return user
    }

    @PostMapping("/addStock")
    fun addStock(@RequestBody stockAdded : String): String {
        val userName = "daniel-kolo"
        var user = checkUser(userName)
        var gson = Gson()
        var response = gson.fromJson(stockAdded, stockQuantityChangeDTO::class.java)
        user!!.addStock(response)
        repo!!.save(user)
        pRepo!!.save(user.portfolio)
        print("asd")
       return "OK"
    }

    @PostMapping("/removeStock")
    fun removeStock(@RequestBody stockRemoved : String): String {
        val userName = "daniel-kolo"
        var user = checkUser(userName)
        var gson = Gson()
        var response = gson.fromJson(stockRemoved, stockQuantityChangeDTO::class.java)

        user.removeStock(response)
        repo!!.save(user)
        pRepo!!.save(user.portfolio)

        return "OK"
    }

    @PostMapping("/addNewStock")
    fun addNewStock(@RequestBody stockAdded : String): String {
        val userName = "daniel-kolo"
        var user = checkUser(userName)
        var gson = Gson()
        var response = gson.fromJson(stockAdded, stockQuantityChangeDTO::class.java)
        user!!.addStock(response)
        repo!!.save(user)
        pRepo!!.save(user.portfolio)
        println("new stock added")
        return gson.toJson(StockTableRow(response.ticker, 1,iex!!.getStockPrice(response.ticker),1,iex!!.getStockPrice(response.ticker)))
    }



    @GetMapping("/getUserStockList")
    fun getUserStockList(): String {
        val gson = Gson()
        val userName = "daniel-kolo"
        var user = checkUser(userName)
        val stockMap = user.portfolio.stockMap
        val stockPriceMap = iex!!.getStockPriceList(stockMap.keys.toList())

        var rows = mutableListOf<StockTableRow>()

        for (stockName in stockPriceMap.keys){
            val name = stockName
            val priceBought = 1
            val currentPrice = stockPriceMap.get(stockName)
            val numberOfStocks = stockMap.get(stockName)
            val totalCurrentValue =  currentPrice!!*numberOfStocks!!
            rows.add(StockTableRow(name,
                                priceBought,
                                currentPrice,
                                numberOfStocks,
                                totalCurrentValue))
        }
        return gson.toJson(rows)
    }


    @GetMapping("/getStockList")
    fun getStockList(): String {
        val gson = Gson()
        val userName = "daniel-kolo"
        var user = checkUser(userName)
        val stockMap = user.portfolio.stockMap

        var symbolList = iex!!.stockSymbolList

        for (stockName in stockMap.keys){
            symbolList.remove(stockName)
        }

        return gson.toJson(symbolList)
    }





}