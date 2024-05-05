package com.example.plug;

import jakarta.validation.*;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/date")
    public ResponseEntity<?> getStaticJson() {

        Map<String, String> response = new HashMap<>();
        response.put("message", "Static JSON");

        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<?> postLogin(@Valid @RequestBody Map<String, String> userNew) {

        if (userNew.size() == 2 && userNew.containsKey("login") && userNew.containsKey("password")) {
            User user = new User();
            user.setLogin(userNew.get("login"));
            user.setPassword(userNew.get("password"));
            user.setDate(LocalDateTime.now());

            return ResponseEntity.ok(user);

        } else {

            return ResponseEntity.badRequest().body("Введите корректный запрос");
        }
    }


}
