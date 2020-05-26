package com.kotlin_portfolio.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kotlin_portfolio.DTO.StockTableRow
import com.kotlin_portfolio.DTO.stockQuantityChangeDTO
import com.kotlin_portfolio.domain.User
import com.kotlin_portfolio.repo.PortfolioRepository
import com.kotlin_portfolio.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping

@RestController
class UserController {

    @Autowired
    private val repo: UserRepository? = null

    @Autowired
    private val pRepo: PortfolioRepository? = null

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

    @GetMapping("/getUserStockList")
    fun getUserStockList(): String {
        val gson = Gson()

        val list = listOf(StockTableRow("APPL",1,1,1,1),
                StockTableRow("GOOG",2,2,2,2))

        //return gson.toJson(StockTableRow("APPL",1,1,1,1))
        return gson.toJson(list)

    }




}