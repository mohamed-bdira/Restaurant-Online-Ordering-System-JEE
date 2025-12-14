package com.example.restaurant.controller;

import com.example.restaurant.dao.OrderDAO;
import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.OrderItem;
import com.example.restaurant.entity.User;
import com.example.restaurant.model.CartItem;
import com.example.restaurant.model.ShoppingCart;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Inject
    private OrderDAO orderDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Show the checkout form
        req.getRequestDispatcher("/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null || cart.getItems().isEmpty()) {
            resp.sendRedirect("menu");
            return;
        }

        // 1. Create User from Form Data
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setPhone(req.getParameter("phone"));
        user.setAddress(req.getParameter("address"));

        // 2. Create Order
        Order order = new Order();
        order.setDeliveryAddress(req.getParameter("address"));
        order.setStatus("Pending");
        order.setTotalAmount(BigDecimal.valueOf(cart.getTotal()));
        order.setOrderItems(new ArrayList<>());

        // 3. Convert Cart Items to Order Entities
        for (CartItem ci : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setMenuItem(ci.getMenuItem());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getMenuItem().getPrice());
            oi.setOrder(order); // Link back to parent
            order.getOrderItems().add(oi);
        }

        // 4. Save to Database
        orderDAO.placeOrder(user, order);

        // 5. Clear Cart and Redirect
        session.removeAttribute("cart");
        req.setAttribute("orderId", order.getId());
        req.getRequestDispatcher("/confirmation.jsp").forward(req, resp);
    }
}