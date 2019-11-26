/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import java.util.*;

/**
 *
 * @author anniinasainio
 */
public class Expense {

    private int id;
    private int categoryId;
    private double amount;
    private Date date;
    private List<Expense> expenses;

    public Expense() {

    }

    public Expense(int categoryId, double amount, Date date) {

        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
    }

    public Expense(int id, int categoryId, double amount, Date date) {
        this.id = id;
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Expense> getExpenses() {
        Expense e = new Expense(getCategoryId(), getAmount(), getDate());
        expenses.add(e);
        return expenses;
    } public double getExpensesTotal(){
        double total = 0.0;
        for(Expense e : expenses){
            total = total + e.getAmount();
        }
        return total;
    }
}
