package com.ndungutse.springboot.myfirstwebapptodo.welcome;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String username, String password) {
        return username.equalsIgnoreCase("eric") && password.equalsIgnoreCase("test");
    }
}
