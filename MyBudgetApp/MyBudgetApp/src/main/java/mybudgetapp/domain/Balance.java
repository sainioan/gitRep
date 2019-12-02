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

    public Balance() {

    }

    public Balance(double incomeTotal, double expenseTotal) {

        this.incomeTotal = incomeTotal;
        this.expenseTotal = expenseTotal;
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
