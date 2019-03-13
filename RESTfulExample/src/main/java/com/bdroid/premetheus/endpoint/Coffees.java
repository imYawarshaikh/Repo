package com.bdroid.premetheus.endpoint;

import io.prometheus.client.Counter;
import io.prometheus.client.Summary;

import javax.annotation.PostConstruct;

public class Coffees {

    private Counter consumedCoffees;
    private Summary s ;

     public Coffees() {
        consumedCoffees = Counter.build("total_coffees_consumed", "Total number of consumed coffees").register();
        s = Summary.build().name("summary").help("meh").register();
    }

    public String retrieveCoffee() {
        consumedCoffees.inc();
        return "Coffee!";
    }

    
    
    
    
    
}
