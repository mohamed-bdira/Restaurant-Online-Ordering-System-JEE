package com.example.restaurant.dao;

import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@RequestScoped
public class OrderDAO {

    @PersistenceContext(unitName = "restaurantPU")
    private EntityManager em;

    // --- CREATE (Existing) ---
    @Transactional
    public void placeOrder(User user, Order order) {
        try {
            User existingUser = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", user.getEmail())
                    .getSingleResult();
            order.setUser(existingUser);
        } catch (Exception e) {
            em.persist(user);
            order.setUser(user);
        }
        em.persist(order);
    }

    // --- READ (New) ---
    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    // --- UPDATE ---
    @Transactional
    public void update(Order order) {
        em.merge(order);
    }

    // --- DELETE ---
    @Transactional
    public void delete(Long id) {
        Order order = em.find(Order.class, id);
        if (order != null) {
            em.remove(order);
        }
    }
}