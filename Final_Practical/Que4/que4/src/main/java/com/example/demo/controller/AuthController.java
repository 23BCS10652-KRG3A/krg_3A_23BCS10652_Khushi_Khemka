package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final String HARDCODED_USERNAME = "admin";
    private static final String HARDCODED_PASSWORD = "admin123";
    private static final String HARDCODED_JWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (HARDCODED_USERNAME.equals(loginRequest.getUsername()) &&
            HARDCODED_PASSWORD.equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(new LoginResponse(HARDCODED_JWT));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    public static class LoginRequest {
        private String username;
        private String password;

        // getters and setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class LoginResponse {
        private String token;

        public LoginResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}