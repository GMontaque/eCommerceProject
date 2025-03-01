package org.example;

import java.util.Random;
import java.util.regex.Pattern;

public class Customer {
    private int id;
    private String first_name;
    private String last_name;
    private boolean admin;
    private double balance;

    public Customer(){
    }

    public void setCustomer(String first_name, String last_name, boolean admin){
        this.id = this.gen();
        this.first_name = first_name;
        this.last_name = last_name;
        this.admin = admin;
        this.balance = 0;
    }

    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return 10000 + r.nextInt(20000);
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public double getBalance() {
        return balance;
    }

    public boolean setBalance(String cost){
        if (Pattern.matches("^[0-9]+(\\.[0-9]{1,2})?$", cost)) {
            this.balance += Integer.parseInt(cost);
            return true;
        }
        return false;
    }

    public boolean setNewBalance(double cost) {
        if (this.balance >= cost && cost > 0) {
            this.balance -= cost;
            return true;
        }
        return  false;
    }
}

