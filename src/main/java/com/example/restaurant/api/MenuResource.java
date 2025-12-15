package com.example.restaurant.api;

import com.example.restaurant.dao.MenuDAO;
import com.example.restaurant.entity.MenuItem;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/menu")
public class MenuResource {

    @Inject
    private MenuDAO menuDAO;

    // GET /api/menu
    // This replaces the Servlet's doGet() method
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMenuItems() {
        List<MenuItem> allItems = menuDAO.getAllMenuItems();

        // Return 200 OK with the list of items
        return Response.ok(allItems).build();
    }
}