package com.kotlin_portfolio.repo

import com.kotlin_portfolio.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    override fun findById(Id: Long): Optional<User?>
    fun findByUserName(userName: String): User?
    //fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}