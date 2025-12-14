package com.example.restaurant.model;

import com.example.restaurant.entity.MenuItem;
import java.io.Serializable;

public class CartItem implements Serializable {
    private MenuItem menuItem;
    private int quantity;

    public CartItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    // Getters and Setters
    public MenuItem getMenuItem() { return menuItem; }
    public void setMenuItem(MenuItem menuItem) { this.menuItem = menuItem; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() {
        // Convert BigDecimal to double for simple calculation display
        return menuItem.getPrice().doubleValue() * quantity;
    }
}