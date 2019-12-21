/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudget.ui;

//import mybudgetapp.dao.BudgetDao;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import mybudgetapp.dao.MyBudgetDatabase;
import mybudgetapp.domain.User;
import mybudgetapp.domain.MyBudgetService;

/**
 *
 * @author ralahtin
 */
public class MyBudgetAppUI extends Application {

    private Scene myBudgetScene;
    private Scene newUserScene;
    private Scene loginScene;
    private Scene pieScene;
    private MyBudgetService mybudgetService;
    private Label menuLabel = new Label();
    private String username;
    private String password;
    private String usernameSU;
    private String passwordSU;
    private String category;
    private User user;
    private Label currentBalance;
    private DatePicker dateFieldExpense = new DatePicker();
    private DatePicker dateFieldIncome = new DatePicker();
    private ComboBox chooseCategory = new ComboBox();
    private String amountString;
    private Button createTableView;
    private Button back2 = new Button("Back");
    private Label balanceLabel = new Label("YOUR BALANCE TODAY:");
    private ObservableList<ObservableList> data;
    private ObservableList<ObservableList> data2;
    private ObservableList<ObservableList> data3;
    private TableView tableviewBalance;
    private TableView tableviewIncome;
    private TableView tableviewExpense;
    private PieChart pieChart;
    private ObservableList<PieChart.Data> pieChartData;
    private MyBudgetDatabase database;
    private Connection c = null;
    private ResultSet rs = null;
    private PreparedStatement stmt = null;
    private ObservableList dataPie;

    @Override
    public void init() throws SQLException, Exception {
        database = new MyBudgetDatabase("mybudgetapp.db");
        mybudgetService = new MyBudgetService(database, username);

    }

    @Override
    public void start(Stage primaryStage) throws SQLException, Exception {
        init();

        //table scene
        tableviewBalance = new TableView();
        tableviewIncome = new TableView();
        tableviewExpense = new TableView();

        HBox tablePane = new HBox();
        GridPane tableGP = new GridPane();
        Button back = new Button("Back");
        Label label = new Label("Your Balance History");
        Label label2 = new Label("Your Income History");
        Label label3 = new Label("Your Expense History");
        label.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
        label2.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
        label3.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
        tableGP.setPadding(new Insets(20, 20, 20, 20));
        tableGP.setHgap(5);
        tableGP.setVgap(5);
        tableGP.add(back, 0, 0);
        tableGP.add(label, 0, 1);
        tableGP.add(tableviewBalance, 0, 5);
        tableGP.add(tableviewIncome, 0, 10);
        tableGP.add(tableviewExpense, 0, 15);
        tableGP.add(label2, 0, 9);
        tableGP.add(label3, 0, 14);

        tablePane.setPadding(new Insets(20, 20, 20, 30));
        tablePane.getChildren().addAll(tableGP);
        Scene tableScene = new Scene(tablePane);
        back.setOnAction(e -> {
            primaryStage.setScene(myBudgetScene);
        });
        //login Scene
        primaryStage.setTitle("MyBudgetApp");

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));
        HBox inputPane = new HBox();

        inputPane.setPadding(new Insets(20, 20, 20, 30));

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(20, 20, 20, 20));
        gridpane.setHgap(5);
        gridpane.setVgap(5);

        Label usernameLabel = new Label("Username");
        TextField usernameInput = new TextField();
        Label passwordLabel = new Label("Password");
        PasswordField passwordInput = new PasswordField();
        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign up");
        Button backButton = new Button("Back");
        Button confirmButton = new Button("Confirm");
        Button deleteUser = new Button("Delete user account");
        Label loginMessage = new Label();

        Label userCreationMessage = new Label();
        bp.setPrefSize(550, 250);
        gridpane.add(usernameLabel, 0, 0);
        gridpane.add(usernameInput, 1, 0);
        gridpane.add(passwordLabel, 0, 1);
        gridpane.add(passwordInput, 1, 1);
        gridpane.add(loginButton, 2, 1);
        gridpane.add(loginMessage, 1, 2);
        gridpane.add(signUpButton, 2, 0);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        Text text = new Text("MyBudgetApp Login");
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        text.setEffect(dropShadow);
        inputPane.getChildren().addAll(gridpane, text);
        bp.setId("bp");
        gridpane.setId("root");
        loginButton.setId("loginButton");
        signUpButton.setId("signUpButton");
        text.setId("text");

        bp.setTop(inputPane);
        bp.setCenter(gridpane);

        //Adding BorderPane to the loginscene 
        loginScene = new Scene(bp);
        //loginscene set on action  
        loginButton.setOnAction(e -> {

            username = usernameInput.getText();
            password = passwordInput.getText();
            try {
                if (mybudgetService.login(username, password)) {
                    user = mybudgetService.getLoggedUser();
                    currentBalance.setText(mybudgetService.updateBalanceLabel());
                    primaryStage.setScene(myBudgetScene);

                    usernameInput.setText("");
                    passwordInput.setText("");
                    loginMessage.setText("");

                } else {

                    loginMessage.setText("Incorrect username or password.");
                    loginMessage.setTextFill(Color.RED);
                }
            } catch (Throwable t) {
                System.out.println("not printing balanceL" + t.getMessage());
            }

        });

        // creating the main scene
        BorderPane bpMain = new BorderPane();
        bpMain.setPadding(new Insets(10, 50, 50, 50));
        VBox mybudgetPane = new VBox(10);

        Label welcome = new Label("Welcome to MyBudgetApp!");
        welcome.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
        balanceLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
        Label createErrorMsg = new Label();
        Label createConfirmationMsg = new Label();
        Label incomeMsg = new Label();
        Label expenseMsg = new Label();
        Label categoryLabel = new Label("New category");
        Label expenseLabel = new Label("New expense: Add amount.");
        Label incomeLabel = new Label("New income: Add amount.");
        Label expenseDate = new Label("Pick expense date");
        Label incomeDate = new Label("Pick income date");
        Label deleteMessage = new Label("");
        currentBalance = new Label();
        Button signoutButton = new Button("Sign out");
        Button createCategoryButton = new Button("Save new category");
        Button createExpenseButton = new Button("Save expense");
        Button createIncomeButton = new Button("Save income");
        Button tableView = new Button("Budget history tableview");
        Button pieSceneButton = new Button("Expenses by category piechart");
        TextField newCategoryInput = new TextField();
        TextField newExpenseInput = new TextField();
        TextField newIncomeInput = new TextField();
        GridPane mybudgetLayout = new GridPane();
        chooseCategory.setPromptText("Choose category");
        chooseCategory.setItems(mybudgetService.createChoices(user));
        chooseCategory.setEditable(true);
        mybudgetLayout.setPadding(new Insets(10, 10, 10, 10));
        mybudgetLayout.setHgap(5);
        mybudgetLayout.setVgap(5);;

        mybudgetLayout.add(welcome, 0, 0);
        mybudgetLayout.add(chooseCategory, 0, 1);
        mybudgetLayout.add(categoryLabel, 0, 2);
        mybudgetLayout.add(newCategoryInput, 0, 3);
        mybudgetLayout.add(createCategoryButton, 0, 4);
        mybudgetLayout.add(createConfirmationMsg, 0, 5);
        mybudgetLayout.add(signoutButton, 15, 0);
        mybudgetLayout.add(expenseLabel, 4, 1);
        mybudgetLayout.add(newExpenseInput, 4, 2);
        mybudgetLayout.add(expenseMsg, 4, 27);
        mybudgetLayout.add(expenseDate, 4, 3);
        mybudgetLayout.add(dateFieldExpense, 4, 4);
        mybudgetLayout.add(createExpenseButton, 4, 5);
        mybudgetLayout.add(balanceLabel, 4, 10);
        mybudgetLayout.add(currentBalance, 4, 11);
        mybudgetLayout.add(tableView, 4, 20);
        mybudgetLayout.add(pieSceneButton, 4, 25);
        mybudgetLayout.add(incomeLabel, 0, 21);
        mybudgetLayout.add(newIncomeInput, 0, 22);
        mybudgetLayout.add(incomeMsg, 4, 27);
        mybudgetLayout.add(incomeDate, 0, 23);
        mybudgetLayout.add(dateFieldIncome, 0, 24);
        mybudgetLayout.add(createIncomeButton, 0, 25);
        mybudgetLayout.add(createErrorMsg, 4, 27);
        mybudgetLayout.add(deleteUser, 15, 1);
        mybudgetLayout.add(deleteMessage, 15, 2);
        mybudgetPane.getChildren().addAll(mybudgetLayout);
        mybudgetLayout.setId("root");
        myBudgetScene = new Scene(mybudgetPane, 1000, 1500);

        // signout buttons returns login view
        signoutButton.setOnAction(e -> {
            mybudgetService.logout();
            primaryStage.setScene(loginScene);

        });
        // table view 
        tableView.setOnAction(e -> {
            try {
                buildDataBalance(user);
                buildDataIncome(user);
                buildDataExpense(user);
            } catch (Exception exc) {
                System.out.println("tableView setOnAction error message" + exc.getMessage());
            }
            primaryStage.setScene(tableScene);
        });

        // pieScene 
        pieSceneButton.setOnAction(e -> {
            try {
                pieScene = new Scene(new Group());

                pieChartData = mybudgetService.expenseByCategory(user);
                pieChart = new PieChart(pieChartData);
                pieChart.setTitle("Expenses by category");
                GridPane piePane = new GridPane();
                piePane.add(back2, 5, 0);
                piePane.add(pieChart, 1, 1);
                ((Group) pieScene.getRoot()).getChildren().add(piePane);
            } catch (Throwable t) {
                System.out.println("pieSceneB set on action failure.." + t.getMessage());
            }
            primaryStage.setScene(pieScene);
        });

        back2.setOnAction(e -> {

            primaryStage.setScene(myBudgetScene);
        });
//        // delete useraccount
        deleteUser.setOnAction(e -> {
            try {
                mybudgetService.deleteUser(user);
                mybudgetService.deleteCategory(user);
                mybudgetService.deleteBalance(user);
                mybudgetService.deleteIncome(user);
                mybudgetService.deleteExpense(user);
                deleteMessage.setText(username + "'s user account successfully deleted.");
                primaryStage.setScene(loginScene);

            } catch (Throwable t) {
                System.out.println("delete user account error ..." + t.getMessage());
            }
        });
// create a new category
        createCategoryButton.setOnAction(e -> {
            if (newCategoryInput.getText().isEmpty()) {

                createErrorMsg.setTextFill(Color.RED);
                createErrorMsg.setText("Type the name of the category");

            } else {

                try {
                    category = newCategoryInput.getText();
                    mybudgetService.createCategory(user.getUsername(), category);
                    createConfirmationMsg.setText("Category '" + category + "' created successfully");
                    createConfirmationMsg.setTextFill(Color.GREEN);
                    newCategoryInput.setText("");
                    chooseCategory.setItems(mybudgetService.createChoices(user));
                } catch (SQLException ex) {
                    System.out.println("createCategoryButton setOnAction error message..." + ex.getMessage());
                }
            }
        });
        //create a new expense
        createExpenseButton.setOnAction(e -> {

            createErrorMsg.setText("");
            expenseMsg.setText("");
            if (newExpenseInput.getText().isEmpty()) {

                createErrorMsg.setTextFill(Color.RED);
                createErrorMsg.setText("Enter the all the required fields: category, amount, and date");

            } else {

                String categoryString = (String) chooseCategory.getValue();
                amountString = newExpenseInput.getText();
                double d = Double.parseDouble(amountString);
                LocalDate expensedate = dateFieldExpense.getValue();
                System.out.println(d);
                try {
                    if (mybudgetService.createExpense(user.getUsername(), categoryString, d, expensedate) == true) {
                        mybudgetService.updateBalanceNewExpense(user.getUsername(), d, expensedate);
                        currentBalance.setText(mybudgetService.updateBalanceLabel());
                        expenseMsg.setText("expense data entered successfully");
                        expenseMsg.setTextFill(Color.GREEN);
                        newExpenseInput.setText("");
                        chooseCategory.getSelectionModel().clearSelection();
                        dateFieldExpense.getEditor().clear();
                    } else {

                        createErrorMsg.setTextFill(Color.RED);
                        createErrorMsg.setText("Enter all the required fields: category, amount, and date");
                    }
                } catch (Throwable t) {
                    System.out.println("MybudgetService.createExpense error message ..." + t.getMessage());
                }
            }
        });

        //create income
        createIncomeButton.setOnAction(e -> {

            createErrorMsg.setText("");
            incomeMsg.setText("");
            if (newIncomeInput.getText().isEmpty()) {

                createErrorMsg.setTextFill(Color.RED);
                createErrorMsg.setText("Enter all the required fields: amount and date");

            } else {

                String amount = newIncomeInput.getText();
                double d2 = Double.parseDouble(amount);
                LocalDate incomedate = dateFieldIncome.getValue();
                System.out.println(d2);
                try {
                    if (mybudgetService.createIncome(user.getUsername(), d2, incomedate) == true) {
                        mybudgetService.updateBalanceNewIncome(user.getUsername(), d2, incomedate);
                        currentBalance.setText(mybudgetService.updateBalanceLabel());
                        incomeMsg.setText("input data entered successfully");
                        incomeMsg.setTextFill(Color.GREEN);
                        newIncomeInput.setText("");
                        dateFieldIncome.getEditor().clear();
                    } else {

                        createErrorMsg.setTextFill(Color.RED);
                        createErrorMsg.setText("Enter all the required fields: amount and date");
                    }
                } catch (Exception ex) {
                    System.out.println("mybudgetService.createIncome error..." + ex.getMessage());
                }
            }
        });
        //create new user scene
        VBox newUserPane = new VBox(10);
        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        Text textNewUser = new Text("MyBudgetApp Sign Up");
        textNewUser.setFont(Font.font("Courier New", FontWeight.BOLD, 28));

        BorderPane bpNewUser = new BorderPane();
        bpNewUser.setPadding(new Insets(10, 50, 50, 50));

        TextField newUsernameInput = new TextField();
        PasswordField newPasswordInput = new PasswordField();
        Label errorMessage = new Label();
        Label errorMessage2 = new Label();
        Label newPassWordLabel = new Label("password");
        newPassWordLabel.setPrefWidth(100);
        Label newUsernameLabel = new Label("username");
        newUsernameLabel.setPrefWidth(100);
        HBox newPasswordPane = new HBox(10);
        newPasswordPane.setPadding(new Insets(10));
        GridPane newUserGridPane = new GridPane();
        newUserGridPane.setPadding(new Insets(20, 20, 20, 20));
        newUserGridPane.setHgap(5);
        newUserGridPane.setVgap(5);

        newUserGridPane.add(newUsernameLabel, 0, 0);
        newUserGridPane.add(newUsernameInput, 1, 0);
        newUserGridPane.add(newPassWordLabel, 0, 1);
        newUserGridPane.add(newPasswordInput, 1, 1);
        newUserGridPane.add(backButton, 1, 2);
        newUserGridPane.add(confirmButton, 0, 2);
        newUserGridPane.add(errorMessage, 0, 3);
        newUserGridPane.add(errorMessage2, 0, 4);
        bpNewUser.setTop(newUsernamePane);
        bpNewUser.setCenter(newUserGridPane);
        textNewUser.setId("text");
        newUsernamePane.getChildren().addAll(newUserGridPane);
        newUserPane.getChildren().addAll(textNewUser, newUserGridPane, userCreationMessage, newPasswordPane, newUsernamePane);

        signUpButton.setOnAction(e -> {

            primaryStage.setScene(newUserScene);

        });

        backButton.setOnAction(e -> {
            mybudgetService.logout();
            primaryStage.setScene(loginScene);

        });

        confirmButton.setOnAction(e -> {

            usernameSU = newUsernameInput.getText();
            passwordSU = newPasswordInput.getText();

            User user = new User(usernameSU, passwordSU);
            String usernameerror = user.validateUsername();
            errorMessage.setText(usernameerror);
            errorMessage.setTextFill(Color.RED);
            String passworderror = user.validatePassword();
            errorMessage2.setText(passworderror);
            errorMessage2.setTextFill(Color.RED);
            try {

                if (usernameerror.equals("") && (passworderror.equals("")) && mybudgetService.createUser(usernameSU, passwordSU)) {
                    mybudgetService.createUser(usernameSU, passwordSU);
                    primaryStage.setScene(loginScene);
                    errorMessage.setText("");
                    usernameInput.setText("");
                    passwordInput.setText("");
                    loginMessage.setText("");
                } else if (!mybudgetService.createUser(usernameSU, passwordSU)) {
                    errorMessage.setText(usernameerror);
                    errorMessage.setTextFill(Color.RED);
                    errorMessage2.setText(passworderror);
                    errorMessage2.setTextFill(Color.RED);
                    errorMessage.setText("invalid username");
                    errorMessage.setTextFill(Color.RED);
                } else {

                    errorMessage.setText("invalid username");
                    errorMessage.setTextFill(Color.RED);
                }
            } catch (Throwable t) {
                System.out.println("confirmButton error message is..." + t.getMessage());
            }

        });

        newUserScene = new Scene(newUserPane, 500, 250);

        primaryStage.setScene(loginScene);

        primaryStage.show();
    }

    @Override
    public void stop() {
    }

    /**
     *
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void buildDataBalance(User useR) throws SQLException, Exception {

        data = FXCollections.observableArrayList();
        try {
            c = database.connect();
            String sql = "SELECT amount, time FROM balance WHERE user_username = ? ORDER BY time desc limit 30";
            stmt = c.prepareStatement(sql);
            //ResultSet
            stmt.setString(1, user.getUsername());
            rs = stmt.executeQuery();

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableviewBalance.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
            }
            //FINALLY ADDED TO TableView
            tableviewBalance.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        } finally {

            c.close();
            rs.close();
            stmt.close();

        }
    }

    public void buildDataIncome(User user) throws SQLException, Exception {
        tableviewIncome.getItems().clear();
        tableviewIncome.getColumns().clear();
        data2 = FXCollections.observableArrayList();
        try {
            c = database.connect();
            String sql = "SELECT amount, time FROM income WHERE user_username = ?  ORDER BY time desc limit 30";
            stmt = c.prepareStatement(sql);
            //ResultSet
            stmt.setString(1, user.getUsername());
            rs = stmt.executeQuery();

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableviewIncome.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data2.add(row);
            }
            //FINALLY ADDED TO TableView
            tableviewIncome.setItems(data2);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        } finally {

            c.close();
            rs.close();
            stmt.close();

        }
    }

    public void buildDataExpense(User user) throws SQLException, Exception {
        tableviewExpense.getItems().clear();
        tableviewExpense.getColumns().clear();
        data3 = FXCollections.observableArrayList();
        try {
            c = database.connect();
            String sql = "SELECT amount, time, category_name from expense WHERE user_username = ?  ORDER BY time desc limit 30";
            stmt = c.prepareStatement(sql);
            //ResultSet
            stmt.setString(1, user.getUsername());
            rs = stmt.executeQuery();

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableviewExpense.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data3.add(row);
            }
            //FINALLY ADDED TO TableView
            tableviewExpense.setItems(data3);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        } finally {
            c.close();
            rs.close();
        }

    }
}
