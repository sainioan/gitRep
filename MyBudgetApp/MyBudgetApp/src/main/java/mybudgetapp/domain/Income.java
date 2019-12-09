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
    private List<Income> totalIncome;
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

    public List<Income> getTotalIncome() {
        return totalIncome;
    }

    /**
     *
     * @param totalIncome list of users totalIncome
     */
    public void setTotalIncome(List<Income> totalIncome) {
        this.totalIncome = totalIncome;
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

    public List<Income> getIncome() {
        Income i = new Income(getUserName(), getAmount(), getDate());
        totalIncome.add(i);
        return totalIncome;
        //The following method is to get the totalAmountOfIncome   
    }

    public double getIncomeTotal() {
        double total = 0.0;
        for (Income i : totalIncome) {
            total = total + i.getAmount();
        }
        return total;
    }

    @Override
    public String toString() {
        return this.username + ": (Income amount): " + this.amount + ", (Date): " + this.date;
    }
}
