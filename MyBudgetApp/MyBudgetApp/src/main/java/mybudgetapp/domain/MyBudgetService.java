/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import java.util.List;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;
import mybudgetapp.dao.DBBudgetDao;
import mybudgetapp.dao.DBUserDao;
import mybudgetapp.dao.MyBudgetDatabase;

/**
 *
 * @author anniinasainio
 */
public class MyBudgetService {

    private MyBudgetDatabase mybDatabase;
    private DBBudgetDao dbbudgetDao;
    private DBUserDao dbuserDao;
    private User loggedIn;
    private String username;
    private String password;
    private LocalDate date;
    private Balance currentBalance;
    private ObservableList<ObservableList> data;
    private TableView tableview;

    /**
     * the contructor for MyBudgetService
     *
     * @param db the database given as a parameter
     * @param username the loggedin user's username is given as a parameter
     * @throws SQLException when connection to the database fails
     */
    public MyBudgetService(MyBudgetDatabase db, String username) throws SQLException {
        this.username = username;
        this.mybDatabase = db;
        this.mybDatabase.initializeDatabase();
        dbuserDao = new DBUserDao(mybDatabase);
        dbbudgetDao = new DBBudgetDao(mybDatabase);
        this.date = LocalDate.now();
    }

    /**
     * method creates a new expense category
     *
     * @param username the name of the logged in user
     * @param description the category name inputted by the user
     * @return true when the creation of a new category succeeds
     */
    public boolean createCategory(String username, String description) {
        Category category = new Category(username, description);
        try {
            dbbudgetDao.createCategory(category);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * the method creates a new expense entry
     *
     * @param username the username of the logged in user
     * @param category the category inputted by the user
     * @param amount the expense amount as double inputted by the user
     * @param date the date of the expense entry
     * @return returns true if the entry succeeds
     * @throws SQLException if the entry fails
     */
    public boolean createExpense(String username, String category, double amount, LocalDate date) throws SQLException {
        Expense expense = new Expense(username, category, amount, date);
        try {
            if ((!username.equals("") && (date != null) && (amount > 0))) {
                dbbudgetDao.createExpense(expense);
                return true;

            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("createExpense error message is..." + ex.getMessage());
            return false;
        }
    }

    /**
     * the method creates a new income entry
     *
     * @param username the username of the logged in user
     * @param amount the income amount as double inputted by the user
     * @param date the date of the income entry
     * @return returns true if the entry succeeds
     * @throws SQLException if the entry fails
     */
    public boolean createIncome(String username, double amount, LocalDate date) throws SQLException, Exception {
        Income income = new Income(username, amount, date);

        try {
            dbbudgetDao.createIncome(income);
        } catch (SQLException ex) {
            System.out.println("createIncome error message is..." + ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * updates the balance when a new expense entry is added
     *
     * @param username the logged in user
     * @param income the amount deposited
     * @param date (always the present day)
     * @return true if there is the execution is successful, otherwise false
     * @throws SQLException if the database operations fail
     * @throws Exception if the the code fails to be executed
     */
    public boolean updateBalanceNewIncome(String username, double income, LocalDate date) throws SQLException, Exception {

        try {
            currentBalance = dbbudgetDao.findOne(username);
            if (currentBalance == null) {
                Balance balance = new Balance(username, income, date);
                dbbudgetDao.saveBalance(balance);
            } else {
                currentBalance.addIncome(income);
                double newBalance = currentBalance.getAmount();
                currentBalance.setAmount(newBalance);
                currentBalance.setDate(date);
                dbbudgetDao.saveBalance(currentBalance);
            }
        } catch (SQLException ex) {
            System.out.println("updateBalance error message is..." + ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * updates the string that the balance label displays on MyBudgetApp scene
     *
     * @return the user's current balance in string format.
     * @throws SQLException if the database operations fail
     * @throws Exception if the the code fails to be executed
     */
    public String updateBalanceLabel() throws SQLException, Exception {
        if (loggedIn != null) {
            if (dbbudgetDao.findOne(loggedIn.getUsername()) != null) {
                currentBalance = dbbudgetDao.findOne(loggedIn.getUsername());

                System.out.println(currentBalance.toString());
            } else {
                currentBalance = new Balance(loggedIn.getUsername().toUpperCase(), 0.0, LocalDate.now());
            }

            return currentBalance.toString();

        }
        return "";

    }

    /**
     * updates the balance when a new expense entry is added
     *
     * @param username the logged in user
     * @param expense the amount deducted from the balance
     * @param date (always the present day)
     * @return true if there is money on the balance, and false if the balance
     * is null.
     * @throws SQLException if the database operations fail
     * @throws Exception if the the code fails to be executed
     */
    public boolean updateBalanceNewExpense(String username, double expense, LocalDate date) throws SQLException, Exception {

        currentBalance = dbbudgetDao.findOne(username);
        if (currentBalance == null) {
            return false;
        }
        try {
            currentBalance.deductExpense(expense);
            double newBalance = currentBalance.getAmount();
            currentBalance.setAmount(newBalance);
            dbbudgetDao.saveBalance(currentBalance);
            return true;

        } catch (SQLException ex) {
            System.out.println("updateBalance error message is..." + ex.getMessage());
            return false;
        }

    }

    /**
     * logging in
     *
     * @param username the username inputted by the user at login
     * @param password the password inputted by the user at login
     *
     * @return true if username and password are correct, otherwise false
     * @throws java.sql.SQLException if the database operations fail
     */
        public boolean login(String username, String password) throws SQLException {
        User user = dbuserDao.findByUsername(username);
        if (user == null) {
            return false;
        }

        loggedIn = user;
        return true;
    }

    /**
     * get logged user
     *
     * @return the logged user
     */
    public User getLoggedUser() {
        System.out.println(loggedIn);
        return loggedIn;
    }

    /**
     * logging out
     *
     */
    public void logout() {
        loggedIn = null;
    }

    /**
     * signing up as a new new user
     *
     * @param username given by the user
     * @param password given by the user
     *
     * @return true if the signing up is successful, otherwise false
     * @throws SQLException if the database operations fail
     */
    public boolean createUser(String username, String password) throws SQLException {

        User user = new User(username, password);
        if ((dbuserDao.findOne(username) != null) || (!user.validateUsername().isEmpty()) || (!user.validatePassword().isEmpty())) {
            return false;
        }
        try {
            dbuserDao.create(user);
        } catch (Exception e) {
            System.out.println("createUser error message is ..." + e.getMessage());
            return false;
        }

        return true;

    }

    /**
     * The method creates an observable list of names for expense categories
     *
     * @param user gives as a parameter is the logged in user
     *
     * @return an observable list
     * @throws SQLException if the database operations fail
     */
    public ObservableList<String> createChoices(User user) throws SQLException {
        ObservableList<String> items = FXCollections.observableArrayList();
        List<Category> categories = dbbudgetDao.getAllCategories(loggedIn);
        ArrayList<String> categorynames = new ArrayList<>();
        for (Category c : categories) {
            String name = c.getName();
            categorynames.add(name);
        }
        for (String s : categorynames) {
            items.add(s.trim());
        }
        return items;
    }

    /**
     * The method creates an observable list the user's expenses grouped by
     * category
     *
     * @param user gives as a parameter is the logged in user
     *
     * @return an observable list
     * @throws SQLException if the database operations fail
     */     

    public ObservableList<PieChart.Data> expenseByCategory(User user) throws SQLException {
        ObservableList<PieChart.Data> itemsE = FXCollections.observableArrayList();

        List<Expense> expenses = dbbudgetDao.getExpensesByCategory(loggedIn);

        for (Expense e : expenses) {
            itemsE.add(new PieChart.Data(e.getCategoryName(), e.getAmount()));
        }
        System.out.println(itemsE);
        return itemsE;

    }

    /**
     * The method checks if the username inputted by the user is valid
     *
     * @param username inputted by the user who is attempting a login or a
     * sign-up
     *
     * @return true if the username input meets the requirements
     */
    public Boolean validateUsernameInput(String username) {
        return ((username != null) && username.matches("[A-Za-z0-9_]+") && username.length() >= 5);
    }

    /**
     * The method deletes a category by invoking a method by the same name in
     * DBBudgetDao class.
     *
     * @param user given as parameter is the logged in user
     *
     * @return true if the username input meets the requirements
     */
    public boolean deleteCategory(User user) {
        try {
            dbbudgetDao.deleteCategory(user);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * The method deletes the user's expense entries by calling a method by the
     * same name in DBBudgetDao class.
     *
     * @param user given as parameter is the logged in user
     *
     * @return true if the username input meets the requirements
     */
    public boolean deleteExpense(User user) {
        try {
            dbbudgetDao.deleteExpense(user);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * The method deletes the user's income entries by calling a method by the
     * same name in DBBudgetDao class.
     *
     * @param user given as a parameter is the logged in user
     *
     * @return true if the username input meets the requirements
     */
    public boolean deleteIncome(User user) {
        try {
            dbbudgetDao.deleteIncome(user);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * The method deletes the user's balance entries by calling a method by the
     * same name in DBBudgetDao class.
     *
     * @param user given as a parameter is the logged in user
     *
     * @return true if the username input meets the requirements
     */
    public boolean deleteBalance(User user) {
        try {
            dbbudgetDao.deleteBalance(user);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * The method deletes the user by calling a method by the same name in
     * DBUserDao class.
     *
     * @param user given as a parameter is the logged in user
     *
     * @return true if the username input meets the requirements
     * @throws java.sql.SQLException when the database connection fails
     */
    public boolean deleteUser(User user) throws SQLException {
        if (user == null) {
            return false;
        } else {
            dbuserDao.delete(user);
        }
        return true;
    }
}
