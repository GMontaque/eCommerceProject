package org.example;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ShopLogic {

    private Customer customer;
    private LoadProducts load;
    private ShoppingCart cart;

    /**
     * Instantiates a new Shop front.
     */
    public ShopLogic(){
        this.customer = new Customer();
        this.load = new LoadProducts();
        this.cart = new ShoppingCart(this.customer.getId());
    }

    public Customer getCustomer() {
        return customer;
    }

    public LoadProducts getLoad() {
        return load;
    }

    public boolean inputCheck(String s, int range){
        if (s.isEmpty()){
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }

        return Integer.parseInt(s) <= range && Integer.parseInt(s) > 0;
    }
    
    public boolean userCheck(Scanner scan){

        int count = 3;

        while (count > 0){
            System.out.print("Enter Admin password: ");
            String password = scan.nextLine().trim();
            if(password.equals("admin")){
                customer.setCustomer("admin", "admin", true);
                System.out.println("Admin Logged In");
                return true;
            } else {
                System.out.println("Error: Password incorrect " + (count - 1) + " Attempts left.");
            }
            count--;
        }
        System.out.println("Admin logged in failed, please try again later");

        return false;

    }

    public void createCustomer(String first_name, String last_name, boolean isAdmin){
        this.customer.setCustomer(first_name, last_name, false);
    }

    public void loadStock(){
        load.productsList();
    }

    public void findProduct(Scanner scan){
        String productName;
        do {
            System.out.print("Enter the product name: ");
            productName = scan.nextLine().trim();
        } while (!Pattern.matches("[a-zA-Z\\s]+", productName));

        load.returnOneProduct(productName);
    }

    public void completeOrder(){
        cart.buy();
    }

    public void addFunds(Scanner scan){
        while(true){
            System.out.println("Enter an amount of funds to add to your online wallet");
            String amount = scan.nextLine();
            if(customer.setBalance(amount)){
                System.out.println("funds updated");
                break;
            } else {
                System.out.println("Error: please enter a valid number");
            }
        }

    }
}
