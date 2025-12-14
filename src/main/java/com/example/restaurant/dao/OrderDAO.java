package com.example.restaurant.dao;

import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.OrderItem;
import com.example.restaurant.entity.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RequestScoped
public class OrderDAO {

    @PersistenceContext(unitName = "restaurantPU")
    private EntityManager em;

    @Transactional
    public void placeOrder(User user, Order order) {
        // 1. Check if user exists by email, else create new
        try {
            User existingUser = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", user.getEmail())
                    .getSingleResult();
            order.setUser(existingUser);
        } catch (Exception e) {
            // User not found, save the new one
            em.persist(user);
            order.setUser(user);
        }

        // 2. Save the Order (Cascade will save OrderItems automatically)
        em.persist(order);
    }
}