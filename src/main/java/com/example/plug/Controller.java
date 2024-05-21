package com.example.plug;

import jakarta.validation.*;

import org.springframework.http.HttpStatus;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.*;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class Controller {
    UserServices userService = new UserServices();

    @GetMapping("/date")
    public Users getUser(@RequestParam String login) throws SQLException {
        return userService.getUser(login);
    }

    @PostMapping("/login")
    public int postLogin(@Valid @RequestBody Map<String, String> userNew) {
        if (userNew.size() == 3 && userNew.containsKey("login") && userNew.containsKey("password") && userNew.containsKey("email")) {
            return userService.postLogin(userNew);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}




