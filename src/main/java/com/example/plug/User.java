package com.example.plug;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;

public class User {
//    @Size(min = 3, message = "Логин не может быть меньше 3 символов")
//    @NotBlank(message = "Логин не может быть пустым")
    String login;

//    @Size(min = 3, max = 10, message = "Пароль не может быть меньше 3 или больше 10 символов ")
//    @NotBlank(message = "Пароль не может быть пустым")
    String password;

    LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


