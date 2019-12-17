/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.sql.SQLException;
import java.util.List;
import mybudgetapp.domain.Balance;
import mybudgetapp.domain.Category;
import mybudgetapp.domain.Expense;
import mybudgetapp.domain.Income;
import mybudgetapp.domain.User;

/**
 *
 * @author anniinasainio
 * @param <Object> The objects of BudgetDao interface are instance of
 * the following classes: Category, Expense, Income, and Balance
 * 
 */
public interface BudgetDao<Object> {

    
    
    
    public void saveCategory(Category category) throws SQLException, Exception;
    public void saveExpense(Expense expense) throws SQLException, Exception;
    public void saveIncome(Income income) throws SQLException, Exception;
    public void saveBalance(Balance balance) throws Exception;
    public Balance findOne(String username) throws SQLException; 
    public List<Balance> getBalanceList(User user) throws SQLException; 
    public List<Category> getAllCategories(User user) throws SQLException; 
    public Category createCategory(Category category) throws SQLException;
    public Income createIncome(Income income) throws SQLException;
    public Expense createExpense(Expense expense) throws SQLException;
    public boolean deleteExpense(User user) throws SQLException;
    public boolean deleteIncome(User user) throws SQLException;
    public boolean deleteCategory(User user) throws SQLException;
    public boolean deleteBalance(User user) throws SQLException;
    
    
}
