package com.example.assignment_4;

public class ShoppingItem {
    private String itemName;
    private int quantity;
    private double price;

    public ShoppingItem() { }

    public ShoppingItem(String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public ShoppingItem(String itemId, String itemName, int i, double v) {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
