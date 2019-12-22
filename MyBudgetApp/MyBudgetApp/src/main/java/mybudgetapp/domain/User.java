/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import javafx.beans.property.*;

/**
 *
 * The class representing the user
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

    /**
     *
     * @param password password inputted by the logged in user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username username of the logged in user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return returns an empty string if validateUsername succeeds
     */
    public String validateUsername() {
        String s = "";

        if (username.length() < 5) {

            s = "username too short";
            return s;
        } else {
            return s;

        }
    }

    /**
     *
     * @return returns an empty string if validatePassword succeeds
     */
    public String validatePassword() {
        String s = "";

        if (password.length() < 5) {

            s = "password too short";
            return s;
        } else {
            return s;

        }
    }

    /**
     * method checks if two objects are equal
     *
     * @param obj object given as a parameter
     * @return true if two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        return username.equals(other.username);
    }
 /**
     * 
     * @return user to String
     */
    @Override
    public String toString() {
        return this.username;
    }
}
