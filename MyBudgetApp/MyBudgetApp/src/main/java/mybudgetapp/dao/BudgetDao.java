/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;
import java.util.List;
import mybudgetapp.domain.MyBudget;
import mybudgetapp.domain.User;
/**
 *
 * @author anniinasainio
 */
public interface BudgetDao {
     MyBudget create() throws Exception;

    List<MyBudget> getAll();

}
