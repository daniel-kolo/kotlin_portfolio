package com.kotlin_portfolio.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController{
    @GetMapping("/test")
    fun getTest(): String{
        return "test"
    }


}

