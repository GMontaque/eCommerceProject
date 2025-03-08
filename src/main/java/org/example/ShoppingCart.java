package org.example;

import java.util.HashMap;

public class ShoppingCart {
    private HashMap<Integer,Products> checkout;
    private HashMap<Integer,Integer> productChange;
    private HashMap<Integer,Integer> cartQuantity;
    private double totalCost;

    public ShoppingCart(int customerId){
        this.checkout = new HashMap<>();
        this.totalCost = 0;
        this.productChange = new HashMap<>();
        this.cartQuantity = new HashMap<>();
    }

    public HashMap<Integer, Products> getCheckout() {
        return checkout;
    }

    public HashMap<Integer, Integer> getCartQuantity() {
        return cartQuantity;
    }

    public void addItem(Products item, String amount){
        int stockAmount = Integer.parseInt(amount);
        int key = item.getId();
        if (cartQuantity.containsKey(item.getId())){
            this.cartQuantity.put(key, this.cartQuantity.get(key) + stockAmount);
        } else {
            this.checkout.put(item.getId(),item);
            this.cartQuantity.put(key, stockAmount);
        }

        totalCost += item.getPrice();
        System.out.println(item.getProductName() + " was added to your basket");
    }

    public boolean payOrder(Customer customer){
        System.out.println("Wallet Fund: £" + customer.getBalance());
        System.out.println("Basket Total: £" + totalCost);

        if(customer.getBalance() > totalCost){
            customer.setNewBalance(totalCost);
            setProductChange(this.checkout);
            checkout.clear();
            System.out.println("Payment has been completed and order processed");
            return true;
        }
        System.out.println("Error: Cost of items is more than your balance");
        return false;
    }

    public void setProductChange(HashMap<Integer, Products> productChange) {
        for(int id : productChange.keySet()){
            this.productChange.put(id, this.productChange.getOrDefault(id, 1) + 1);
        }
    }

    public void shoppingCart(){
        for(int key : checkout.keySet()){

            System.out.println("Product ID: " + this.checkout.get(key).getId());
            System.out.println("Product Name: " + this.checkout.get(key).getProductName());
            System.out.println("Price: " + this.checkout.get(key).getPrice());
            System.out.println("Amount: " + this.cartQuantity.get(key));

        }
    }

    public void removeItems(int amount, int key){

        if(amount == 0){
            checkout.clear();
            System.out.println("Basket has been cleared");
        }

        if(amount == 1){
            System.out.println(checkout.get(key).getProductName() + " has been removed");
            checkout.remove(key);
        }

    }

}
