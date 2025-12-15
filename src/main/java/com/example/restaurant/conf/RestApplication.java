package com.example.restaurant.conf;


import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

// This annotation defines the base URL for all your API endpoints.
// Example: http://localhost:8080/YourApp/api/menu
@ApplicationPath("/api")
public class RestApplication extends Application {
    // No code needed inside!
    // Just extending 'Application' turns on the JAX-RS engine.
}
