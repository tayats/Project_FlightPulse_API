package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // When someone visits http://localhost:8080/api/hello in their browser:
    @GetMapping("/api/hello")
    public String sayHello() {
        return "✈️ Welcome to FlightPulse REST API! The server is LIVE on port 8080!";
    }
}