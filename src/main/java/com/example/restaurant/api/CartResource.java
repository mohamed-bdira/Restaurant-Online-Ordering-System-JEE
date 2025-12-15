package com.example.restaurant.api;

import com.example.restaurant.dao.MenuDAO;
import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.model.CartItem;
import com.example.restaurant.model.ShoppingCart;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cart")
public class CartResource {

    @Inject
    private MenuDAO menuDAO;

    // We inject the HttpServletRequest to access the Session
    @Context
    private HttpServletRequest request;

    // 1. GET /api/cart - View the cart
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCart() {
        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        return Response.ok(cart).build();
    }

    // 2. POST /api/cart - Add item
    // Body: { "itemId": 1, "quantity": 2 }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addToCart(CartRequestData itemData) {
        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        MenuItem menuItem = menuDAO.getMenuItemById(itemData.getItemId());

        if (menuItem != null) {
            cart.addItem(new CartItem(menuItem, itemData.getQuantity()));
            return Response.ok("{\"message\": \"Item added to cart\"}").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Menu item not found\"}").build();
        }
    }

    // 3. DELETE /api/cart/{itemId} - Remove item
    @DELETE
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeFromCart(@PathParam("itemId") Long itemId) {
        HttpSession session = request.getSession(false); // Don't create new session if missing

        if (session != null) {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart != null) {
                cart.removeItem(itemId);
                return Response.ok("{\"message\": \"Item removed\"}").build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"error\": \"Cart is empty\"}").build();
    }

    // ==========================================
    // Helper Class for JSON Input
    // ==========================================
    public static class CartRequestData {
        private Long itemId;
        private int quantity;

        // Getters and Setters
        public Long getItemId() { return itemId; }
        public void setItemId(Long itemId) { this.itemId = itemId; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}