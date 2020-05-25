package com.kotlin_portfolio.domain

import com.kotlin_portfolio.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

//@Component
class UserInitializer {


    @Autowired
    private val repo: UserRepository? = null

    init {
        val user = User("daniel-kolo", "kolodani95@gmail.com")
        println(repo!!)
        if (repo!!.findByUserName(user.userName) == null){
            repo.save(user)
        }
    }

}