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
public class Balance {

    private double balance;
    private int id;
    private String username;
    private Expense e;
    private Income i;
    private LocalDate date;

    /**
     * the constructor that takes two parameters:
     *
     * @param username of the logged in user
     * @param date time when a balance is checked
     */
    public Balance(String username, LocalDate date) {
        this.username = username;
        this.date = date;
    }

    public Balance(String username, double amount, LocalDate date) {
        this.username = username;
        this.balance = amount;
        this.date = date;
    }

    /**
     *
     * @return returns the username of the user
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return returns the expense object
     */
    public Expense getExp() {
        return e;
    }

    /**
     * setter the expense object
     *
     * @param e expense object
     */
    public void setExp(Expense e) {
        this.e = e;
    }

    public Income getInc() {
        return i;
    }

    /**
     * setter for the income object
     *
     * @param i the income object
     */
    public void setInc(Income i) {
        this.i = i;
    }

    public void addIncome(double income) {

        if (income >= 0) {
            this.balance += income;
        } else {
            return;
        }
    }

    public void deductExpense(double expense) {
        if (this.balance - expense < 0) {
            this.balance = 0.0;
        } else {
            this.balance = this.balance - expense;
        }
    }

    public double getAmount() {
        return this.balance;
    }

    public void setAmount(double b) {
        this.balance = b;
        //this.balance = (this.balance + i.getIncomeTotal()) - e.getExpensesTotal();

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     *
     * @return returns id for the balance
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.username + ": (Balance): " + this.balance + " € (Date): " + this.date;
    }

}
