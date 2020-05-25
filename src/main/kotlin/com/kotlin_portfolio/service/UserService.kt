package com.kotlin_portfolio.service

import com.kotlin_portfolio.domain.User
import com.kotlin_portfolio.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

class UserService(val repo : UserRepository) {

   fun processUserLogin(principal : OAuth2User){
        val username = principal.getAttributes().get("login").toString()
        val email = principal.getAttributes().get("email").toString()

        if (repo!!.findByUserName(username)!=null){

        }
        else{
            print("user not found while authenticating")
            repo.save(User(username, email))
        }
    }

}