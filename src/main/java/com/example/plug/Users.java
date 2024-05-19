package com.example.plug;


import jakarta.validation.constraints.NotNull;

import java.beans.Transient;
import java.sql.Timestamp;

public class Users {

    String login;

    String password;

    String email;

    Timestamp date;

    public Users(String login, String password, String email, Timestamp date) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = date;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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


    @Override
    public String toString() {
        return "User{" +
                "login ='" + login + '\'' +
                ", password ='" + password + '\'' +
                ", email ='" + email + '\'' +
                ", date =" + date +
                '}';
    }


}


