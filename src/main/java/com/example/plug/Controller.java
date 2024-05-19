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
    String url = "jdbc:postgresql://192.168.1.124:5432/postgres";
    String username = "postgres";
    String password = "postgres";
    Connection connection;

    @GetMapping("/date")
    public Users getUser(@RequestParam String login) throws SQLException {

        try {
            connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            String query = "SELECT us.login, password, email, date FROM users as us " +
                    "JOIN user_emails as up ON us.login = up.login WHERE us.login = '" + login + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                Timestamp date = resultSet.getTimestamp("date");
                return new Users(login, password, email, date);
            }else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }

    // Map<String, String> response = new HashMap<>();
    //  response.put("message", "Static JSON");

    //return ResponseEntity.ok(response);


    @PostMapping("/login")
    public int postLogin(@Valid @RequestBody Map<String, String> userNew ) throws SQLException {

        if (userNew.size() == 3 && userNew.containsKey("login") && userNew.containsKey("password") && userNew.containsKey("email")) {

        try (Connection connection2 = DriverManager.getConnection(url, username, password)) {

                PreparedStatement psUsers = connection2.prepareStatement("INSERT INTO users(login, password, date) VALUES (?,?,?);");
                PreparedStatement psEmail = connection2.prepareStatement("INSERT INTO user_emails(login, email) VALUES (?,?)");

                psUsers.setString(1, userNew.get("login"));
                psUsers.setString(2, userNew.get("password"));
                psUsers.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

                psEmail.setString(1, userNew.get("login"));
                psEmail.setString(2, userNew.get("email"));

                return psUsers.executeUpdate() + psEmail.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
//
//        if (userNew.size() == 2 && userNew.containsKey("login") && userNew.containsKey("password")) {
//            User user = new User();
//            user.setLogin(userNew.get("login"));
//            user.setPassword(userNew.get("password"));
//            user.setDate(new Timestamp(System.currentTimeMillis()));
//
//            return ResponseEntity.ok(user);
//
//        } else {
//
//            return ResponseEntity.badRequest().body("Введите корректный запрос");
//        }
//    }



