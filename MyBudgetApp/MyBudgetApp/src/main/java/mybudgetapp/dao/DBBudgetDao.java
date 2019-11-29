/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.util.ArrayList;
import java.util.List;
import mybudgetapp.domain.MyBudget;
import mybudgetapp.domain.User;

/**
 *
 * @author anniinasainio
 */
public class DBBudgetDao implements BudgetDao {
      private int id;
    private String content;
    private boolean done;
    private double amount;
    //private User user;
    public DBBudgetDao(){
        
    }
      @Override
//    public MyBudget create() throws Exception(){
//     return new MyBudget(content, amount);     
//      }

    public List<MyBudget> getAll(){
        return new ArrayList<MyBudget>();
    }

 

    @Override
    public MyBudget create() throws Exception {
 //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return new MyBudget(content, amount);    
    }

  
}
