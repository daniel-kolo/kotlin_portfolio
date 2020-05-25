package com.kotlin_portfolio.controller

import com.kotlin_portfolio.service.UserService
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.google.gson.Gson
import com.kotlin_portfolio.DTO.stockAddedDTO
import com.kotlin_portfolio.domain.User
import com.kotlin_portfolio.repo.RoleRepository
import com.kotlin_portfolio.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired

@RestController
class UserController {

    @Autowired
    private val repo: UserRepository? = null

    @Autowired
    private val repo2: RoleRepository? = null



    @PostMapping("/addStock")
    fun addStock(@RequestBody stockAdded : String): String {
        val userName = "daniel-kolo"
        var gson = Gson()
        var response = gson.fromJson(stockAdded, stockAddedDTO::class.java)

        var user = repo!!.findByUserName(userName)
        if (user == null){
            user = User(userName, "")
            repo!!.save(user)
        }
        println(user)

        user!!.addStock(response)

       return "OK"
    }

}