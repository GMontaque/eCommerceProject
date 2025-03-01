package org.example;

public class Products {
    private int id;
    private String productName;
    private int price;
    private String description;
    private int stock;
    private boolean outOfStock;

    public Products(int id, String productName, int price, String description, int stock){
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.outOfStock = false;
    }

    public String getProductName() {
        return productName;
    }

    public void setOutOfStock(boolean outOfStock) {
        this.outOfStock = outOfStock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(int price) {
        this.price = price;
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
