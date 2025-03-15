package org.example;

import java.io.*;
import java.util.HashMap;

public class FileService {

    private final String path;

    public FileService(String path) {
        this.path = path;
    }

    public String readFile() {
        String content = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line = br.readLine();
            while (line != null) {
                content += line + "\n";
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error: file could not be read");
        }
        return content;
    }


    public void writeFile(String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
            writer.write(content);

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }
    }

    public void updateTxt(HashMap<Integer,Products> products) {
//        System.out.println(products);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
            for(int id : products.keySet()){
                String productName = products.get(id).getProductName();
                double price = products.get(id).getPrice();
                String description = products.get(id).getDescription();
                int stockLevel = products.get(id).getStock();
                if(id == 1){
                    writer.write(id + "," + productName + "," + price + "," + description
                            + "," + stockLevel);
                }
                writer.write("\n" + id + "," + productName + "," + price + "," + description
                        + "," + stockLevel);
            }

            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }
    }
}
