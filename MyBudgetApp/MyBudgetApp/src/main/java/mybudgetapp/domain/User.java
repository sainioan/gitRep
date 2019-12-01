/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import javafx.beans.property.*;

/**
 *
 * @author ralahtin
 */
public class User {

    private String username;
    private String password;

    public User() {

    }

    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String validateUsername() {
        String s = "";

        if (username.length() < 5) {

            s = "username too short";
            return s;
        } else {
            return s;

        }
    }
    public String validatePassword() {
        String s = "";

        if (password.length() < 5) {

            s = "password too short";
            return s;
        } else {
            return s;

        }
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        return username.equals(other.username);
    }
    @Override
    public String toString(){
        return this.username;
    }
}
