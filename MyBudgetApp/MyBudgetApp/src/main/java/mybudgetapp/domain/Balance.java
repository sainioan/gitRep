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
    private double incomeTotal;
    private double expenseTotal;
    private User user;
    private Expense e;
    private Income i;
    private LocalDate date;

    public Balance(double incomeTotal, double expenseTotal) {

        this.incomeTotal = incomeTotal;
        this.expenseTotal = expenseTotal;
    }

    public Balance(User user, double incomeTotal, double expenseTotal) {
        this.user = user;
        this.incomeTotal = incomeTotal;
        this.expenseTotal = expenseTotal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setBalance() {

        this.balance = (this.balance + i.getIncomeTotal()) - e.getExpensesTotal();

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

    public Balance(int id) {
        this.id = id;
    }

    public Balance(int id, double incomeTotal, double expenseTotal) {
        this.id = id;
        this.incomeTotal = incomeTotal;
        this.expenseTotal = expenseTotal;
    }

    public Balance(double balance) {
        this.balance = balance;
    }

}
