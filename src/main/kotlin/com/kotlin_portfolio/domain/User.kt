package com.kotlin_portfolio.domain

import com.kotlin_portfolio.DTO.stockAddedDTO
import com.kotlin_portfolio.security.Role
import javax.persistence.*

@Entity
open class User(var userName: String = "",
                var email: String = ""){
    @Id
    @GeneratedValue
    var Id: Long = 0
    var version: Int = 0
    var accountNonExpired: Boolean = true
    var accountNonLocked: Boolean = true
    var credentialsNonExpired: Boolean = true
    var enabled: Boolean = true


    @OneToOne(cascade=arrayOf(CascadeType.ALL))
    @JoinColumn(name = "portfolio_id" , referencedColumnName = "id")
    var portfolio: Portfolio = Portfolio()

    @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
    var roles: MutableSet<Role> = HashSet()


    constructor(user: User) : this( user.userName, user.email) {
        Id = user.Id
        version = user.version
        userName = user.userName
        email = user.email
        accountNonExpired = user.accountNonExpired
        accountNonLocked = user.accountNonLocked
        credentialsNonExpired = user.credentialsNonExpired
        enabled = user.enabled
        roles = user.roles
        this.portfolio = user.portfolio
    }

    init{
        this.portfolio = Portfolio()
    }

    fun addStock(stockAdded : stockAddedDTO){
        //this.portfolio = Portfolio()
        println(stockAdded)
        println(this.portfolio)
        //portfolio.addStock(stockAdded)
    }

}