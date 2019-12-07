/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author anniinasainio
 */
public class Expense {

    private int id;
    private String categoryName;
    private double amount;
    private LocalDate date;
    private List<Expense> expenses;
    private String userName;

    public Expense() {

    }

    public Expense(double amount, LocalDate date) {

        this.amount = amount;
        this.date = date;
    }

    public Expense(String user, String categoryName, double amount, LocalDate date) {

        this.userName = user;
        this.categoryName = categoryName;
        this.amount = amount;
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public List<Expense> getExpenses() {
        Expense e = new Expense(getUserName(), getCategoryName(), getAmount(), getDate());
        expenses.add(e);
        return expenses;
    }

    public double getExpensesTotal() {
        double total = 0.0;
        for (Expense e : expenses) {
            total = total + e.getAmount();
        }
        return total;
    }
}
