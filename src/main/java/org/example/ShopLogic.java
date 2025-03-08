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

    public void balance(){
        customer.printBalance();
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

        if(range > 0){
            return Integer.parseInt(s) <= range && Integer.parseInt(s) > 0;
        }

        return Integer.parseInt(s) > 0;
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
        this.customer.setCustomer(first_name, last_name, isAdmin);
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

        int productKey = load.returnOneProduct(productName);

        if (this.customer.isAdmin()) {
            adminSubOptions(scan,productKey);
        } else {
            customerSubOptions(scan,productKey);
        }

    }

    public void customerSubOptions(Scanner scan, int key){

        while(true){
            System.out.println("""
                    Choose from the below options:
                    (-): Individual Product Details
                        (1): add product to basket
                        (2): Main Menu
                    """);

            System.out.print("Enter either 1 or 2 ");

            String adminSelection = scan.nextLine().trim();

            if(adminSelection.equals("2")){
                break;
            }

            if (adminSelection.equals("1")) {
                String amount;
                do {
                    System.out.print("Enter an amount to buy max(9): ");
                    amount = scan.nextLine().trim();
                } while (!inputCheck(amount, 9));
                cart.addItem(load.getStock().get(key), amount);
                break;
            }

        }
    }

    public void adminSubOptions(Scanner scan, int key){

        System.out.println(load.getStock().get(key));

        while(true){
            System.out.println("""
                    Choose from the below options:
                    (-): Individual Product Details
                        (1): Update stock
                        (2): Reduce stock
                        (3): Remove Product
                        (4): Update Price
                        (5): Updating Description
                        (6): Main Menu
                    """);

            System.out.print("Enter a number between 1 and 6: ");

            String adminSelection = scan.nextLine().trim();
            if(adminSelection.equals("6")){
                break;
            }

            switch (adminSelection) {
                case "1":
                    removeAddStock("add Stock", scan, key);
                    break;
                case "2":
                    removeAddStock("reduce Stock", scan, key);
                    break;
                case "3":
                    load.getStock().get(key).setOutOfStock(true);
                    System.out.println(("Item set as out of stock"));
                    break;
                case "4":
                    String newPrice = load.numberCheck(scan,"Price update amount");
                    load.updateProductOptions(key,"Price update amount",newPrice);
                    break;
                case "5":
                    this.descriptionCheck(scan, key,"update description");
                    break;
            }
        }
    }

    public void removeAddStock(String command, Scanner scan, int key){
        while (true){
            System.out.print("Enter the amount to " + command + ": ");
            String amount = scan.nextLine();
            if (this.inputCheck(amount, 0)){
                load.updateProductOptions(key,command,amount);
                break;
            }

        }
    }

    public void descriptionCheck(Scanner scan, int key, String command){
        String description;
        do {
            System.out.print("Enter the product name: ");
            description = scan.nextLine().trim();
        } while (!Pattern.matches("[a-zA-Z\\s]+", description));

        load.updateProductOptions(key,command,description);
    }

    public void checkout(Scanner scan){
        cart.shoppingCart();

        while(true){
            System.out.println("""
                    Choose from the below options:
                    (-): Individual Product Details
                        (1): Pay
                        (2): remove item from basket
                        (3): empty basket
                        (4): Main Menu
                    """);

            System.out.print("Enter a number between 1 and 4: ");

            String customerSelection = scan.nextLine().trim();

            if(customerSelection.equals("4")){
                break;
            }

            boolean orderComplete = false;

            switch (customerSelection) {
                case "1":
                    if(cart.payOrder(customer)){
                        orderComplete = true;
                        updateProductStock();
                    }

                    break;
                case "2":
                    getKeyRemoveProduct(scan);
                    break;
                case "3":
                    cart.removeItems(0,0);
                    break;
            }

            if(orderComplete){
                break;
            }

        }

    }

    public void updateProductStock(){
        for (int key : load.getStock().keySet()){
            if(cart.getCartQuantity().containsKey(key)){
                load.getStock().get(key).setReduceStock(cart.getCartQuantity().get(key));
            }
        }
//        System.out.println(load.getStock());
//        System.out.println(cart.getCartQuantity());
    }

    public void getKeyRemoveProduct(Scanner scan){
        cart.shoppingCart();

        while (true) {
            System.out.print("Enter the ID to remove the item: ");
            String userSelection = scan.nextLine().trim();
            if (inputCheck(userSelection, 0)) {
                cart.removeItems(1,Integer.parseInt(userSelection));
                break;
            }
        }
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
