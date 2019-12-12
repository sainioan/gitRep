/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mybudgetapp.domain.Balance;
import mybudgetapp.domain.Category;
import mybudgetapp.domain.Expense;
import mybudgetapp.domain.Income;
import mybudgetapp.domain.User;

/**
 *
 * @author anniinasainio
 */
public class DBBudgetDao implements BudgetDao {

    private int id;
    private String description;
    private MyBudgetDatabase db;
    private String database;
    private List<Category> categories = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();
    private List<Income> incomeList = new ArrayList<>();
    private String sql2 = "SELECT*FROM income where user_username = ?";
    private String sql3 = "SELECT*FROM expense where user_username = ?";
    private String sql4 = "SELECT*FROM category";
    private List<Income> incomeByUser = new ArrayList<>();
    private List<Expense> expensesByUser = new ArrayList<>();
    private List<Balance> listB = new ArrayList<>();

    /**
     *
     * @param db database given as a parameter
     */
    public DBBudgetDao(MyBudgetDatabase db) {
        this.db = db;
        categories = new ArrayList<>();
        db.initializeDatabase();
        Statement stmt = null;
        try {
            Connection conn = db.connect();
            db = new MyBudgetDatabase(database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select*FROM category");
            while (rs.next()) {
                Category category = new Category();
                category.setUserName(rs.getString("categoryUser"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Saves given category into the database
     *
     * @param category category name
     * @throws SQLException if saving the category name fails
     */
    public void saveCategory(Category category) throws SQLException, Exception {
        Connection connection = db.connect();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT OR REPLACE INTO category (categoryUser, name) VALUES (?,?);"
            );
            statement.setString(1, category.getUserName());
            statement.setString(2, category.getName());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param expense the inserted expected
     * @throws SQLException throws an SQL if saving into database fails
     * @throws Exception throws an Exception if execution fails
     */
    public void saveExpense(Expense expense) throws SQLException, Exception {

        Connection connection = db.connect();
        Float amountF = (float) expense.getAmount();
        Date sqlDate = Date.valueOf(expense.getDate());
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT OR REPLACE INTO expense (user_username, category_name, amount, time) VALUES (?,?,?,?);"
            );
            statement.setString(1, expense.getUserName());
            statement.setString(2, expense.getCategoryName());
            statement.setFloat(3, amountF);
            statement.setDate(4, sqlDate);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("saveExpense message is... " + e.getMessage());
        }
    }

    /**
     * Saves an income entry into the database
     *
     * @param income income object
     * @throws SQLException if the saving into the database fails
     *
     * @throws Exception if execution fails
     */
    public void saveIncome(Income income) throws SQLException, Exception {
        Connection connection = db.connect();
        Float amountF2 = (float) income.getAmount();
        Date sqlDate = Date.valueOf(income.getDate());
        try {
            PreparedStatement statementIn = connection.prepareStatement(
                    "INSERT OR REPLACE INTO income (user_username, amount, time) VALUES (?,?,?);"
            );
            statementIn.setString(1, income.getUserName());
            statementIn.setFloat(2, amountF2);
            statementIn.setDate(3, sqlDate);
            statementIn.executeUpdate();
            statementIn.close();
        } catch (SQLException e) {
            System.out.println("saveIncome error message is... " + e.getMessage());
        }
    }

    public void saveBalance(Balance balance) throws Exception {
        Connection connection = db.connect();
        Float amount = (float) balance.getBalance();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT OR REPLACE INTO Balance (user_username, amount, time) VALUES (?, ?, ?);"
            );
            statement.setString(1, balance.getUsername());
            statement.setFloat(2, amount);
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println("saveBAlance error" + e.getMessage());
        }
    }

    public Balance findOne(String username) throws SQLException {
        Connection conn = db.connect();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM balance WHERE user_username = ?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        boolean findOne = rs.next();
        if (!findOne) {
            return null;
        }
        double balanceAmount = Double.parseDouble(rs.getString("amount"));
        Balance b = new Balance(rs.getString("user_username"), balanceAmount, LocalDate.now());
        stmt.close();
        rs.close();
        conn.close();

        return b;
    }

    public List<Balance> getBalanceList(User user) throws SQLException {
        try {
            Connection con = db.connect();
            PreparedStatement stmt = con.prepareStatement("SELECT*FROM balance WHERE user_username = ?");
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            listB = new ArrayList<>();
            while (rs.next()) {
                Balance b = new Balance(rs.getString("user_username").trim(), rs.getFloat("amount"), rs.getDate("time").toLocalDate());
                b.setId(rs.getInt("id"));
                listB.add(b);
                stmt.close();
                rs.close();
                con.close();
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        System.out.println(listB.toString());
        return listB;
    }

    /**
     * Adds a new expense entered by a user given as a parameter to a list
     *
     * @param user user object
     * @return an ArrayList with all of the users expenses
     * @throws SQLException if retrieving data from the database fails
     *
     */
    public List<Expense> getAllExpenses(User user) throws SQLException {
        try {
            Connection con = db.connect();
            String userUsername = user.getUsername();
            PreparedStatement stmt = con.prepareStatement(sql3);
            stmt.setString(1, userUsername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense(rs.getString("user_username").trim(), rs.getString("category_name"), rs.getFloat("amount"), rs.getDate("time").toLocalDate());
                expensesByUser.add(expense);
                stmt.close();
                rs.close();
                con.close();
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        System.out.println(expensesByUser);
        return expensesByUser;
    }

    /**
     *
     * @param user user given as a parameter
     * @return returns a List with all given user's income entries
     * @throws SQLException if data retrieval fails
     */
    public List<Income> getAllIncome(User user) throws SQLException {
        try {
            Connection con = db.connect();
            String userUsername = user.getUsername();
            PreparedStatement stmt = con.prepareStatement(sql2);
            stmt.setString(1, userUsername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Income income = new Income(rs.getString("user_username").trim(), rs.getFloat("amount"), rs.getDate("time").toLocalDate());
                income.setId(rs.getInt("id"));
                incomeList.add(income);
                stmt.close();
                rs.close();
                con.close();
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        return incomeByUser;

    }

    /**
     *
     * @param user user object
     * @return returns a list of categories created by the given user
     * @throws SQLException when retrieving data from the database fails
     */
    public List<Category> getAllCategories(User user) throws SQLException {
        try {
            Connection con = db.connect();
            PreparedStatement stmt = con.prepareStatement("SELECT*FROM category WHERE categoryUser = ?");
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category(rs.getString("categoryUser").trim(), rs.getString("name"));
                category.setId(rs.getInt("id"));
                categories.add(category);
                stmt.close();
                rs.close();
                con.close();
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        return categories;
    }

    /**
     * creates a new category object by calling saveCategory method
     *
     * @param category category object
     * @return returns category object
     * @throws SQLException if data retrieval fails
     */
    public Category create(Category category) throws SQLException {

        categories.add(category);

        try {
            saveCategory(category);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return category;
    }

    /**
     *
     * @param income income object
     * @return returns income object
     * @throws SQLException if the data retrieval fails
     */
    public Income create(Income income) throws SQLException {

        try {
            incomeList.add(income);
            saveIncome(income);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return income;
    }

    /**
     *
     * @param expense expense object inputted by the logged in user
     * @return returns the newly created expense object
     * @throws SQLException if data retrieval from the database fails
     */
    public Expense create(Expense expense) throws SQLException {

        try {
            expenses.add(expense);
            saveExpense(expense);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return expense;
    }

    @Override
    public Object create(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteExpense(String key) throws SQLException {

        Connection con = db.connect();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM expense WHERE id = ?");

        stmt.setString(1, key);

        stmt.executeUpdate();

        stmt.close();
        con.close();

        return true;

    }

    public boolean deleteIncome(String key) throws SQLException {

        Connection con = db.connect();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM income WHERE id = ?");

        stmt.setString(1, key);

        stmt.executeUpdate();

        stmt.close();
        con.close();

        return true;

    }

    public boolean deleteCategory(String key) throws SQLException {

        Connection con = db.connect();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM category WHERE id = ?");

        stmt.setString(1, key);

        stmt.executeUpdate();

        stmt.close();
        con.close();

        return true;

    }

    private int getLastId(Connection connection) {
        int id = -1;
        try {
            PreparedStatement getLastId = connection.prepareStatement("SELECT last_insert_rowid() AS id;");
            ResultSet resultSet = getLastId.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }

            return id;
        } catch (SQLException ex) {
            Logger.getLogger(DBBudgetDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return id;
    }
}
