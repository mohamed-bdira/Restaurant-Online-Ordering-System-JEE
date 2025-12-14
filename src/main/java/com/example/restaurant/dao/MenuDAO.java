package com.example.restaurant.dao;

import com.example.restaurant.entity.MenuItem;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;

@RequestScoped
public class MenuDAO {

    // The Server injects the working connection here automatically
    @PersistenceContext(unitName = "restaurantPU")
    private EntityManager em;

    public List<MenuItem> getAllMenuItems() {
        TypedQuery<MenuItem> query = em.createQuery("SELECT m FROM MenuItem m", MenuItem.class);
        return query.getResultList();
    }

    public MenuItem getMenuItemById(Long id) {
        return em.find(MenuItem.class, id);
    }
}