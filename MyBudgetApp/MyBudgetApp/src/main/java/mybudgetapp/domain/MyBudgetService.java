/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;
import mybudgetapp.dao.BudgetDao;
/**
 *
 * @author anniinasainio
 */
public class MyBudgetService {
    BudgetDao budgetDao;
    
    public MyBudgetService(BudgetDao bd){
        this.budgetDao = bd;
    }
}
