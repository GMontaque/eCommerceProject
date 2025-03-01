package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class LoadProducts {

    private HashMap<Integer, Products> stock;
    private boolean runProgram;
    private final FileService productsService;
    public LoadProducts(){
        stock = new HashMap<>();
        productsService = new FileService("src/main/java/org/example/data/products.txt");
    }

    public void populate(){
//        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//        InputStream products = classloader.getResourceAsStream("./data/products.txt");
        String products = productsService.readFile();
//        System.out.println(products);
        if (products == null){
            this.runProgram = false;
            System.out.println("Error: File can not be found");
        } else {
            Scanner scan = new Scanner(products).useDelimiter("\\A");
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
            Products stockProduct = new Products(Integer.parseInt(product[0]),product[1],Integer.parseInt(product[2]),
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

    public void updateProducts(){
        productsService.writeFile("\n13,Ninja spacasde,169,7-qasduart air fryer with mega zone basket,17");
    }


}
