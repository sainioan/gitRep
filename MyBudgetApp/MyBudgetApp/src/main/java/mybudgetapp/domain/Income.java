/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import java.util.*;
import java.time.LocalDate;

/**
 *
 * @author anniinasainio
 */
public class Income {

    private int id;
    private double amount;
    private LocalDate date;
    private String username;

    public Income() {

    }

    public Income(int id, double amount, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.date = date;

    }

    public Income(String username, double amount, LocalDate date) {
        this.username = username;
        this.amount = amount;
        this.date = date;
    }

    /**
     *
     * @param amount income amount as double
     * @param date time of income
     */
    public Income(double amount, LocalDate date) {

        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return returns the user's username
     */
    public String getUserName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return returns the amount of the income entry
     */
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
     * @param date time of the income
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return income object to String
     */
    @Override
    public String toString() {
        return this.username + ": (Income amount): " + this.amount + ", (Date): " + this.date;
    }
}
