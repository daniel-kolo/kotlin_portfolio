package com.kotlin_portfolio.repo

import com.kotlin_portfolio.security.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByRolename(rolename: String): Role
}