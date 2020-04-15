package com.kotlin_portfolio.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

enum class AssetType {
    crypto_currency, stock, bond, currency, commodity
}

@Entity
class Asset(assetType: AssetType, priceBought: Double, currentPrice: Double){
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Id
    val Id : Long = 0

    // comment

    val assetType = assetType
    val priceBought = priceBought
    val currentPrice = currentPrice
}

