package org.example;

public class Products {
    private int id;
    private String productName;
    private double price;
    private String description;
    private int stock;
    private boolean outOfStock;

    public Products(int id, String productName, double price, String description, int stock){
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.outOfStock = this.stock <= 0;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public void setOutOfStock(boolean outOfStock) {
        this.outOfStock = outOfStock;
        this.stock = 0;
    }

    public void setStock(int stock) {
        if(this.stock + stock <= 1000){
            this.stock += stock;
        } else{
            System.out.println("Error: stock set at maximum of 1000");
            this.stock = 1000;
        }
    }

    public void setReduceStock(int stock) {
        if (this.stock - stock == 0){
            this.stock = 0;
            this.outOfStock = true;
        } else if(this.stock - stock > 0){
            this.stock -= stock;
        } else {
            System.out.println("Error: Stock level can not be negative");
            System.out.println("Current Stock level: " + this.stock);
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product ID: " + this.id +
                "\n" + "Product Name: " + this.productName +
                "\n" + "Price: " + this.price +
                "\n" + "Description: " + this.description +
                "\n" + "Stock: " + this.stock;
    }
}
