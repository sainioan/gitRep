/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import javafx.beans.property.*;

/**
 *
 * @author anniinasainio
 */
public class Category {

    private int id;
    private String name;
    private User user;
    private String username;

    public Category() {

    }

    public Category(String name) {

        this.name = name;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/**
 *
 * @return category object to String
 */
    @Override
    public String toString() {
        return this.name;
    }
}
