package com.kotlin_portfolio.repo

import com.kotlin_portfolio.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface UserRepository : JpaRepository<User, Long> {
    override fun findById(Id: Long): Optional<User?>
    fun findOneByUserName(userName: String): User?
    //fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}