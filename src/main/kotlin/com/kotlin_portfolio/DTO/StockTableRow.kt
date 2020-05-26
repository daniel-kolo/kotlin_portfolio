package com.kotlin_portfolio.DTO

data class StockTableRow(
        val stockName : String,
        val priceBought : Int,
        val currentPrice: Int,
        val numberOfStocks : Int,
        val totalCurrentValue : Int
        ) {
}