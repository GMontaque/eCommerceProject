package org.example;

import java.util.HashMap;

public class ShoppingCart {
    private HashMap<Integer,Products> checkout;
    private int customerId;

    public ShoppingCart(int customerId){
        this.checkout = new HashMap<>();
        this.customerId = customerId;
    }

    public void buy(){
        System.out.println("buy");
    }
}
