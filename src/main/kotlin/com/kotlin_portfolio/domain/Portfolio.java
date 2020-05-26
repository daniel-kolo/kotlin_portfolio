package com.kotlin_portfolio.domain;

import com.kotlin_portfolio.DTO.stockQuantityChangeDTO;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Entity
public class Portfolio {


    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Id
    @Column(name = "id")
    private long Id;

    public Portfolio(){}

    @ElementCollection()
    private Map<String, Integer> stockMap = new HashMap<String, Integer>();

   public void addStock(stockQuantityChangeDTO stockAdded){
       String ticker = stockAdded.getTicker();
       int q = stockAdded.getQuantity();
        if (stockMap.containsKey(ticker)){
            stockMap.put(ticker,stockMap.get(ticker)+q);
        }
        else{
            stockMap.put(stockAdded.getTicker(), stockAdded.getQuantity());
        }

        System.out.print(stockMap);
   }

    public void removeStock(stockQuantityChangeDTO stockRemoved){
        String ticker = stockRemoved.getTicker();
        int q = stockRemoved.getQuantity();
        if (stockMap.containsKey(ticker)){
            int currentQ = stockMap.get(ticker);
            if (currentQ > q){
                stockMap.put(ticker,stockMap.get(ticker)-q);
            }
            else{
                stockMap.remove(ticker);
            }
        }
    }

    public Set<String> getStockList(){
       return stockMap.keySet();
    }

    public Map<String,Integer> getStockMap(){
       return stockMap;
    }



}
