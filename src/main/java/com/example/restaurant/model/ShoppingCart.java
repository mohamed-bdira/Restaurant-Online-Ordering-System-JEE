package com.example.restaurant.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem item) {
        // Check if item already exists, if so, just update quantity
        for (CartItem existing : items) {
            if (existing.getMenuItem().getId().equals(item.getMenuItem().getId())) {
                existing.setQuantity(existing.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(Long menuItemId) {
        items.removeIf(item -> item.getMenuItem().getId().equals(menuItemId));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}