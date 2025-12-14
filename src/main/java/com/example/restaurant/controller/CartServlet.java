package com.example.restaurant.controller;

import com.example.restaurant.dao.MenuDAO;
import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.model.CartItem;
import com.example.restaurant.model.ShoppingCart;
import jakarta.inject.Inject;  // <--- Make sure you have this import!
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Inject
    private MenuDAO menuDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            addToCart(req);
        } else if ("remove".equals(action)) {
            removeFromCart(req);
        }
        resp.sendRedirect("cart");
    }

    private void addToCart(HttpServletRequest req) {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        // This is where it was crashing before!
        Long itemId = Long.parseLong(req.getParameter("itemId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        MenuItem item = menuDAO.getMenuItemById(itemId);
        if (item != null) {
            cart.addItem(new CartItem(item, quantity));
        }
    }

    private void removeFromCart(HttpServletRequest req) {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            Long itemId = Long.parseLong(req.getParameter("itemId"));
            cart.removeItem(itemId);
        }
    }
}