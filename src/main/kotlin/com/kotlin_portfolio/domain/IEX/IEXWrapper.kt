package com.kotlin_portfolio.domain.IEX

import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject
import org.springframework.stereotype.Component

@Component
class IEXWrapper {

    var stockData = String()
    var stockSymbolList = mutableListOf<String>()
    var cryptoData = String()
    var cryptoSymbolList = mutableListOf<String>()
    var stockNameMap = HashMap<String,String>()
    var cryptoNameMap = HashMap<String,String>()

    init {
        println("IEWX wrapper initialized")
        val stockProcess = ProcessBuilder("curl", "https://cloud.iexapis.com/beta/ref-data/symbols?token=pk_5fd464ae2e144027a6d71d451a84b488").start()
        stockProcess.inputStream.reader(Charsets.UTF_8).use {
            stockData = it.readText()
        }

        val cryptoProcess = ProcessBuilder("curl", "https://cloud.iexapis.com/beta/ref-data/crypto/symbols?token=pk_5fd464ae2e144027a6d71d451a84b488").start()
        cryptoProcess.inputStream.reader(Charsets.UTF_8).use {
            cryptoData = it.readText()
        }

        stockSymbolList = stockData.split("},{").toMutableList()
        stockSymbolList[0] = stockSymbolList[0].split("{")[1]
        stockSymbolList[stockSymbolList.size-1].split("}")[0]

        for (i in 0..stockSymbolList.size-1){
            stockSymbolList[i] = stockSymbolList[i].split(",")[2].split(":")[1]
                    .split("\"")[1]
                    .split("\"")[0]
        }

        cryptoSymbolList = cryptoData.split("},{").toMutableList()
        cryptoSymbolList[0] = cryptoSymbolList[0].split("{")[1]
        cryptoSymbolList[cryptoSymbolList.size-1].split("}")[0]
        /*
        for (i in 0..cryptoSymbolList.size-1){
            cryptoSymbolList[i] = cryptoSymbolList[i].split(",")[2].split(":")[1]
                    .split("\"")[1]
                    .split("\"")[0]
        }*/
    }

    fun getStockPriceList(tickerList : List<String>): HashMap<String, Int> {
        //val Process = ProcessBuilder("curl", "https://cloud.iexapis.com/beta/stock/AAPL/book?token=pk_5fd464ae2e144027a6d71d451a84b488").start()
        var tickers = ""
        for (ticker in tickerList){
            tickers = tickers.plus(ticker).plus(",")
        }

        var stockMap = HashMap<String, Int>()

        val Process = ProcessBuilder("curl", "https://cloud.iexapis.com/beta/stock/market/batch?symbols=$tickers,&types=quote&token=pk_5fd464ae2e144027a6d71d451a84b488").start()
        Process.inputStream.reader(Charsets.UTF_8).use {
            var data = it.readText()
            for (ticker in tickerList){
                var price = data.split(ticker.toUpperCase())[2].split("latestPrice\":")[1].split(",")[0].split(".")[0].toInt()
                stockMap.put(ticker,price)
            }
        }
        return stockMap
    }

}