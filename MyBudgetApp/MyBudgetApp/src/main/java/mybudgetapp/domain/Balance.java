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
    private double amount;
    private double incomeTotal;
    private double expenseTotal;
    private String username;
    private Expense e;
    private Income i;
    private LocalDate date;

    public Balance(double incomeTotal, double expenseTotal) {

        this.incomeTotal = incomeTotal;
        this.expenseTotal = expenseTotal;
    }

    public Balance(String username, LocalDate date) {
        this.username = username;
        this.date = date;
    }

    public Balance(String username, double amount, LocalDate date) {
        this.username = username;
        this.amount = getBalance();
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Expense getExp() {
        return e;
    }

    public void setExp(Expense e) {
        this.e = e;
    }

    public Income getInc() {
        return i;
    }

    public void setInc(Income i) {
        this.i = i;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double b) {
        this.balance = b;
        //this.balance = (this.balance + i.getIncomeTotal()) - e.getExpensesTotal();

    }

    public LocalDate getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(double incomeTotal) {
        this.incomeTotal = incomeTotal;
    }

    public double getExpenseTotal() {
        return expenseTotal;
    }

    public void setExpenseTotal(double expenseTotal) {
        this.expenseTotal = expenseTotal;
    }

    @Override
    public String toString() {
        return this.username + ": (Balance): " + this.balance + " on " + this.date;
    }

}
