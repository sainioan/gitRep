/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import java.time.LocalDate;
import mybudgetapp.domain.User;

/**
 *
 * @author anniinasainio
 */
public class MyBudget {

    private int id;
    private String description;
    private User user;
    private double amount;
    private LocalDate date;

    public MyBudget(int id, String content, User user) {
        this.id = id;
        this.description = content;
        this.user = user;
    }

    public MyBudget(String description, double amount) {
        this.description = description;
        this.amount = amount;

    }

    public MyBudget(String description, double amount, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyBudget)) {
            return false;
        }
        MyBudget other = (MyBudget) obj;
        return id == other.id;
    }
}
