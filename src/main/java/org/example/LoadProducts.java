package org.example;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class LoadProducts {

    private HashMap<Integer, Products> stock;
    private boolean runProgram;
    private final FileService productsService;
    public LoadProducts(){
        stock = new HashMap<>();
        productsService = new FileService("src/main/java/org/example/data/products.txt");
    }

    public void populate(Scanner scan){
        String products = productsService.readFile();

        if (products == null){
            this.runProgram = false;
            System.out.println("Error: File can not be found");
        } else {
            scan = new Scanner(products).useDelimiter("\\A");
            String result = scan.hasNext() ? scan.next() : "File is Empty";
            this.runProgram = !result.equals("File is Empty");
            loadProducts(result);
        }
    }

    public boolean getRunProgram() {
        return this.runProgram;
    }

    private void loadProducts(String products){

        String[] productLineSplit = products.split("\\r?\\n");

        for (String s : productLineSplit) {
            String[] product = s.split(",");
            Products stockProduct = new Products(Integer.parseInt(product[0]),product[1],Double.parseDouble(product[2]),
                    product[3],Integer.parseInt(product[4]));
            stock.put(Integer.parseInt(product[0]),stockProduct);
//            System.out.println(Arrays.toString(product));
        }

    }

    public void productsList(){
        for (int numKey : stock.keySet()){
            System.out.println(stock.get(numKey));
            System.out.println();
        }
    }

    public void returnOneProduct(String productName){
        for (int numKey : stock.keySet()){
            if(stock.get(numKey).getProductName().equals(productName)){
                System.out.println(stock.get(numKey));
                System.out.println();
            }
        }
    }

    public void updateProducts(Scanner scan){

        int id = this.stock.size() + 1;
        String productName = stringInputCheck(scan,"Product Name");
        double price = Double.parseDouble(numberCheck(scan, "Price"));
        String description = stringInputCheck(scan, "Product Description");
        int stock = Integer.parseInt(numberCheck(scan, "Stock Amount"));

        productsService.writeFile("\n" + id + "," + productName + "," + price + "," + description
                + "," + stock);
        System.out.println("New Product successfully added to shop");
    }

    public String stringInputCheck(Scanner scan, String title){
        String stringInput;
        do {
            System.out.print("Enter the " + title + ": ");
            stringInput = scan.nextLine().trim();
        } while (!Pattern.matches("[a-zA-Z\\s]+", stringInput));

        return stringInput;
    }

    public String numberCheck(Scanner scan, String title){

        String numInput;
        do {
            System.out.print("Enter the " + title + ": ");
            numInput = scan.nextLine().trim();
        } while (!Pattern.matches("^[0-9]+(\\.[0-9]{1,2})?$", numInput));

        return numInput;
    }

}
