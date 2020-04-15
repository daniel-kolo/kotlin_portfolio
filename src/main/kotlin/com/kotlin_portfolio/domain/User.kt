package com.kotlin_portfolio.domain

import javax.persistence.*

@Entity
class User(firstName: String, lastName: String, email: String, password: String){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val Id : Long = 0

    @Column
    val firstName = firstName
    @Column
    val lastName = lastName
    @Column
    val email = email
    @Column
    val password = password
    //@Column
    //val portfolio = portfolio

}