package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class LoadProducts {

    private HashMap<Integer, Products> stock;
    private boolean runProgram;

    public LoadProducts(){
        stock = new HashMap<>();
    }

    public void populate(){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream products = classloader.getResourceAsStream("products.txt");
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
        String stringToWrite = "\nJava files are easy";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt", true));
            writer.write(stringToWrite);

            writer.close();
            System.out.println(writer);
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }


    }


}
