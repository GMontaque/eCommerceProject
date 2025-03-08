package org.example;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The type Shop front.
 */
public class ShopFront {

    /**
     * Start.
     */
    public void start() {
        Scanner scan = new Scanner(System.in);
        ShopLogic logic = new ShopLogic();

        System.out.println("Welcome to **Novo** the best supermarket around");
        System.out.println("""
                Are opening hours are: 8am to 8pm Monday to Saturday
                Below you will be greeted with the available options
                we have a wide range of stock from fruit to bread
                
                """);

        logic.getLoad().populate(scan);
        if (logic.getLoad().getRunProgram()) {
            if (command(scan, logic)) {
                shopPage(scan, logic);
            }
        } else {
            System.out.println("Error: products code not load and program has ended");
        }

        updateProductsTxt(logic);
    }

    /**
     * User inputs commands to access shop
     *
     * @param scan the scan
     */
    public boolean command(Scanner scan, ShopLogic logic) {

        boolean logIn;
        int selection;
        while (true) {
            System.out.print("please confirm if you are (1) Admin or (2) Customer: ");
            String userSelection = scan.nextLine().trim();
            if (logic.inputCheck(userSelection, 2)) {
                selection = Integer.parseInt(userSelection);
                break;
            }
        }

        if (selection == 1) {
            boolean adminCheck = logic.userCheck(scan);
            logIn = true;
            if (!adminCheck) {
                logIn = false;
                System.out.println("More than 3 attempts to log in as admin has been detected, Goodbye");
            }
        } else {
            String first_name;
            do {
                System.out.println("Enter your First Name");
                first_name = scan.nextLine().trim();
            } while (!Pattern.matches("[a-zA-Z]+", first_name));

            String last_name;
            do {
                System.out.println("Enter your Last Name");
                last_name = scan.nextLine().trim();
            } while (!Pattern.matches("[a-zA-Z]+", last_name));

            logic.createCustomer(first_name, last_name, false);

            logIn = true;
        }

        return logIn;

    }

    /**
     * Shop page - gives the user options on what they can do.
     *
     * @param scan the scan
     */
    public void shopPage(Scanner scan, ShopLogic logic) {
        System.out.println("Welcome " + logic.getCustomer().getFirst_name() + " How can we help you today");
        if (logic.getCustomer().isAdmin()) {
            this.shopPageAdmin(logic, scan);
        }
        else {
            this.shopPageCustomer(logic, scan);
        }
    }

    public void shopPageAdmin(ShopLogic logic, Scanner scan) {
        while (true) {
            System.out.println("""
                    Choose from the below options:
                    (1): Product List
                    (2): Individual Product Details
                        (-): Update stock
                        (-): Remove Product
                        (-): Update Price
                        (-): Updating Description
                    (3): Add New Product
                    (4): Quit
                    """);

            System.out.print("Enter a number between 1 and 4: ");
            String adminSelection = scan.nextLine().trim();
            if(adminSelection.equals("4")){
                break;
            }

            switch (adminSelection) {
                case "1":
                    logic.loadStock();
                    break;
                case "2":
                    logic.findProduct(scan);
                    break;
                case "3":
                    logic.getLoad().updateProducts(scan);
                    break;
            }
        }
    }

    public void shopPageCustomer(ShopLogic logic, Scanner scan) {
        while (true) {
            System.out.println("""
                    Choose from the below options:
                    (1): Product List
                    (2): Individual Product Details
                        (-): add product to basket
                    (3): checkout
                        (-): Pay
                        (-): remove item from basket
                        (-): empty basket
                    (4): Add Funds to Card
                    (5): Current Balance
                    (6): Quit
                    """);

            System.out.print("Enter a number between 1 and 6: ");
            String adminSelection = scan.nextLine().trim();
            if(adminSelection.equals("6")){
                break;
            }

            switch (adminSelection) {
                case "1":
                    logic.loadStock();
                    break;
                case "2":
                    logic.findProduct(scan);
                    break;
                case "3":
                    logic.checkout(scan);
                    break;
                case "4":
                    logic.addFunds(scan);
                    break;
                case "5":
                    logic.balance();
                    break;
            }
        }
    }

    public void updateProductsTxt(ShopLogic logic){
        System.out.println("TXT file updated");
    }
}
