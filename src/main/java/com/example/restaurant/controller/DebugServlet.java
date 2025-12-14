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

@WebServlet("/debug")
public class DebugServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>🚨 System Diagnosis 🚨</h1>");

        try {
            out.println("<h3>1. Checking Database Connection...</h3>");

            // Attempt to build the factory manually to catch errors
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("restaurantPU");
            out.println("<p style='color:green'>✅ EntityManagerFactory Created.</p>");

            EntityManager em = emf.createEntityManager();
            out.println("<p style='color:green'>✅ Database Connected Successfully.</p>");

            out.println("<h3>2. Checking Database Content...</h3>");
            // Simple query to check if tables exist
            Long count = em.createQuery("SELECT count(u) FROM User u", Long.class).getSingleResult();
            out.println("<p style='color:green'>✅ User Table exists. Count: " + count + "</p>");

            out.println("<h3>3. Checking Menu Items...</h3>");
            Long menuCount = em.createQuery("SELECT count(m) FROM MenuItem m", Long.class).getSingleResult();
            out.println("<p style='color:green'>✅ MenuItem Table exists. Count: " + menuCount + "</p>");

            em.close();

            out.println("<h2>🎉 SYSTEM IS WORKING!</h2>");
            out.println("<p>If you see this, the Database is fine. The issue is likely in your <b>menu.jsp</b> file.</p>");

        } catch (Throwable e) {
            out.println("<h2 style='color:red'>❌ FATAL ERROR DETECTED</h2>");
            out.println("<h3>Here is the exact reason your app is crashing:</h3>");
            out.println("<pre style='background:#f8d7da; padding:15px; border:1px solid #f5c6cb; color:#721c24;'>");

            // Print the full error to the browser
            e.printStackTrace(out);

            out.println("</pre>");
            out.println("<p><b>Please copy the text in the red box above and paste it into the chat.</b></p>");
        }

        out.println("</body></html>");
    }
}