package com.example.restaurant.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/testdb")
public class TestDbServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        out.println("--- DIAGNOSTIC TEST ---");

        try {
            // 1. Test Persistence Unit Loading
            out.println("Attempting to create EntityManagerFactory...");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("restaurantPU");
            out.println("SUCCESS: EntityManagerFactory created.");

            // 2. Test Database Connection
            out.println("Attempting to create EntityManager...");
            EntityManager em = emf.createEntityManager();
            out.println("SUCCESS: Database connection established.");

            // 3. Test Data Retrieval
            out.println("Attempting to count MenuItems...");
            Long count = em.createQuery("SELECT count(m) FROM MenuItem m", Long.class).getSingleResult();
            out.println("SUCCESS: Found " + count + " items in the menu.");

            // 4. List Items (if any)
            if (count > 0) {
                List<Object[]> items = em.createQuery("SELECT m.id, m.name FROM MenuItem m", Object[].class).getResultList();
                for (Object[] item : items) {
                    out.println(" - Item ID: " + item[0] + ", Name: " + item[1]);
                }
            } else {
                out.println("WARNING: The MenuItem table is empty! The menu page will look blank.");
            }

            em.close();

        } catch (Exception e) {
            out.println("\n!!! ERROR DETECTED !!!");
            e.printStackTrace(out); // This will print the exact error to your browser
        }
    }
}