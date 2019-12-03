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

    public Income() {

    }

    public Income(int id, double amount, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.date = date;

    }

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

    public List<Income> getIncome() {
        Income i = new Income(getAmount(), getDate());
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
}
