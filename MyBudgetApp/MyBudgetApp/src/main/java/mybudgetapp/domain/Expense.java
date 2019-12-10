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
    private ArrayList<Expense> expenses = new ArrayList<>();
    private String userName;

    public Expense() {

    }

    /**
     *
     * @param user username of the logged in user
     * @param categoryName name of the category for the expense object
     * @param amount amount of the expense as double
     * @param date time of the expense
     */
    public Expense(String user, String categoryName, double amount, LocalDate date) {

        this.userName = user;
        this.categoryName = categoryName;
        this.amount = amount;
        this.date = date;
    }

    /**
     *
     * @return returns the username of the logged in user
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    /**
     *
     * @param date time of the expense
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void listExpenses(ArrayList<Expense> list, Expense e) {
        this.expenses = list;
        e = new Expense(getUserName(), getCategoryName(), getAmount(), getDate());
        expenses.add(e);
    }

    public double getExpensesTotal() {
        double total = 0.0;
        for (Expense e : expenses) {
            total = total + e.getAmount();
        }
        return total;
    }

    @Override
    public String toString() {
        return this.userName + ": (Expense catetegory): " + this.categoryName + ", (Amount): " + this.amount + ", (Date): " + this.date;
    }
}
