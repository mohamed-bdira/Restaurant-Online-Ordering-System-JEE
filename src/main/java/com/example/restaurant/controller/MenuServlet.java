package com.example.restaurant.controller;

import com.example.restaurant.dao.MenuDAO;
import com.example.restaurant.entity.MenuItem;
import jakarta.inject.Inject; // Use Injection
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    @Inject // <--- Server creates this for us now
    private MenuDAO menuDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MenuItem> allItems = menuDAO.getAllMenuItems();
        req.setAttribute("menuList", allItems);
        req.getRequestDispatcher("/menu.jsp").forward(req, resp);
    }
}