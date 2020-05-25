package com.kotlin_portfolio.domain;

import com.kotlin_portfolio.DTO.stockAddedDTO;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Portfolio {
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Id
    @Column(name = "id")
    private long Id;

    public Portfolio(){
        System.out.println("Portfolio constructed: " + Id);
    }

    //@ElementCollection()
    //private Map<String, Integer> stockMap;

   public void addStock(stockAddedDTO stockAdded  ){
        System.out.print(stockAdded);
   }



}
