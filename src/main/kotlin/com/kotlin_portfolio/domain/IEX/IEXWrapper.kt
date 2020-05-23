package com.kotlin_portfolio.domain.IEX

import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject
import org.springframework.stereotype.Component

@Component
class IEXWrapper {

    var stockData = String()
    var cryptoData = String()
    //var currencyData = String()

    init {
        val stockProcess = ProcessBuilder("curl", "https://cloud.iexapis.com/beta/ref-data/symbols?token=pk_5fd464ae2e144027a6d71d451a84b488").start()
        stockProcess.inputStream.reader(Charsets.UTF_8).use {
            stockData = it.readText()
        }

        val cryptoProcess = ProcessBuilder("curl", "https://cloud.iexapis.com/beta/ref-data/crypto/symbols?token=pk_5fd464ae2e144027a6d71d451a84b488").start()
        cryptoProcess.inputStream.reader(Charsets.UTF_8).use {
            cryptoData = it.readText()
        }

        /*
        val currencyProcess = ProcessBuilder("curl", "https://cloud.iexapis.com/beta/ref-data/fx/symbols?token=pk_5fd464ae2e144027a6d71d451a84b488").start()
        currencyProcess.inputStream.reader(Charsets.UTF_8).use {
            currencyData = it.readText()
        }
        */



    }

    fun getStockSymbols(){

    }
}