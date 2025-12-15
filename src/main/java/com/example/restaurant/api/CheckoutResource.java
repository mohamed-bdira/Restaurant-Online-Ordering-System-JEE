package com.example.restaurant.api;

import com.example.restaurant.dao.OrderDAO;
import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.OrderItem;
import com.example.restaurant.entity.User;
import com.example.restaurant.model.CartItem;
import com.example.restaurant.model.ShoppingCart;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.ArrayList;

@Path("/checkout")
public class CheckoutResource {

    @Inject
    private OrderDAO orderDAO;

    @Context
    private HttpServletRequest request;

    // POST /api/checkout
    // Consumes JSON: { "name": "John", "email": "john@a.com", "phone": "123", "address": "123 St" }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkout(CheckoutRequest userData) {

        // 1. Get Cart from Session
        HttpSession session = request.getSession(false);
        ShoppingCart cart = (session != null) ? (ShoppingCart) session.getAttribute("cart") : null;

        if (cart == null || cart.getItems().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Cart is empty. Cannot checkout.\"}")
                    .build();
        }

        try {
            // 2. Create User from JSON Data
            User user = new User();
            user.setName(userData.getName());
            user.setEmail(userData.getEmail());
            user.setPhone(userData.getPhone());
            user.setAddress(userData.getAddress());

            // 3. Create Order
            Order order = new Order();
            order.setDeliveryAddress(userData.getAddress());
            order.setStatus("Pending");
            order.setTotalAmount(BigDecimal.valueOf(cart.getTotal()));
            order.setOrderItems(new ArrayList<>());

            // 4. Convert Cart Items to Order Entities
            for (CartItem ci : cart.getItems()) {
                OrderItem oi = new OrderItem();
                oi.setMenuItem(ci.getMenuItem());
                oi.setQuantity(ci.getQuantity());
                oi.setPrice(ci.getMenuItem().getPrice());
                oi.setOrder(order); // Link back to parent
                order.getOrderItems().add(oi);
            }

            // 5. Save to Database
            orderDAO.placeOrder(user, order);

            // 6. Clear Cart
            session.removeAttribute("cart");

            // 7. Return Success JSON
            return Response.ok()
                    .entity("{\"message\": \"Order placed successfully\", \"orderId\": " + order.getId() + "}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Order failed: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    // ==========================================
    // Helper DTO Class for JSON Input
    // ==========================================
    public static class CheckoutRequest {
        private String name;
        private String email;
        private String phone;
        private String address;

        // Getters and Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
    }
}