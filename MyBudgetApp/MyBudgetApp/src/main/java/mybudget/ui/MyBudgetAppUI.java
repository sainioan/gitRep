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
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import mybudgetapp.dao.MyBudgetDatabase;
import mybudgetapp.dao.DBUserDao;
import mybudgetapp.dao.DBBudgetDao;
import mybudgetapp.domain.User;
import mybudgetapp.domain.MyBudgetService;

/**
 *
 * @author ralahtin
 */
public class MyBudgetAppUI extends Application {

    private Scene myBudgetScene;
    private Scene newUserScene;
    private Scene loginscene;
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
    private Label balanceLabel = new Label("BALANCE");
    private ObservableList<ObservableList> data;
    private TableView tableview;
    private MyBudgetDatabase database;

    @Override
    public void init() throws SQLException, Exception {
        database = new MyBudgetDatabase("mybudgetapp.db");
        mybudgetService = new MyBudgetService(database, username);

    }//CONNECTION DATABASE

    public void buildData(User user) throws SQLException, Exception {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = database.connect();
            String SQL = "SELECT*from BALANCE WHERE user_username = ?";
            PreparedStatement stmt = c.prepareStatement(SQL);
            //ResultSet
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();

            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY * ********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /**
             * ******************************
             * Data added to ObservableList * ******************************
             */
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
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @Override
    public void start(Stage primarystage) throws SQLException, Exception {
        init();
        //table scene
        tableview = new TableView();
        
        
        HBox tablePane = new HBox();
        GridPane tableGP = new GridPane();
        Button back = new Button("Back");
        tableGP.setPadding(new Insets(20, 20, 20, 20));
        tableGP.setHgap(5);
        tableGP.setVgap(5);
        tableGP.add(back, 0, 0);
        tableGP.add(tableview,0,5);
        
        tablePane.setPadding(new Insets(20, 20, 20, 30));
        tablePane.getChildren().addAll(tableGP);
        Scene tablescene = new Scene(tablePane);
        back.setOnAction(e ->{
        primarystage.setScene(myBudgetScene);    
        });
        //login Scene
        primarystage.setTitle("MyBudgetApp");

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
//         
        //Adding BorderPane to the scene 
        loginscene = new Scene(bp);
        loginButton.setOnAction(e -> {

            username = usernameInput.getText();
            password = passwordInput.getText();

            if (mybudgetService.login(username, password)) {
                user = mybudgetService.getLoggedUser();
                try {
                    System.out.println("GUI" + mybudgetService.updateBalanceLabel());
                    currentBalance.setText(mybudgetService.updateBalanceLabel());
                    buildData(user);
                    primarystage.setScene(myBudgetScene);
                    System.out.println(mybudgetService.getMostRecent(user));
                } catch (Throwable t) {
                    System.out.println("not printing balanceL" + t.getMessage());
                }
                usernameInput.setText("");
                passwordInput.setText("");
                loginMessage.setText("");

            } else {

                loginMessage.setText("Incorrect username or password.");
                loginMessage.setTextFill(Color.RED);
            }

        });

        // main scene
        BorderPane bpMain = new BorderPane();
        bpMain.setPadding(new Insets(10, 50, 50, 50));
        VBox mybudgetPane = new VBox(10);

        Label welcome = new Label("Welcome to MyBudgetApp!");
        welcome.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
        balanceLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
        Label createErrorMsg = new Label();
        Label createConfirmationMsg = new Label();
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
        Button tableView = new Button("Balance View");
        TextField newCategoryInput = new TextField();
        TextField newExpenseInput = new TextField();
        TextField newIncomeInput = new TextField();
        GridPane mybudgetLayout = new GridPane();
        chooseCategory.setPromptText("Choose category");
        chooseCategory.getItems().addAll(mybudgetService.createChoices(user));
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
        mybudgetLayout.add(expenseDate, 4, 3);
        mybudgetLayout.add(dateFieldExpense, 4, 4);
        mybudgetLayout.add(createExpenseButton, 4, 5);
        mybudgetLayout.add(balanceLabel, 4, 10);
        mybudgetLayout.add(currentBalance, 4, 11);
        mybudgetLayout.add(tableView, 4, 20);
        mybudgetLayout.add(incomeLabel, 0, 21);
        mybudgetLayout.add(newIncomeInput, 0, 22);
        mybudgetLayout.add(incomeDate, 0, 23);
        mybudgetLayout.add(dateFieldIncome, 0, 24);
        mybudgetLayout.add(createIncomeButton, 0, 25);
        mybudgetLayout.add(createErrorMsg, 3, 0);
        mybudgetLayout.add(deleteUser, 15, 1);
        mybudgetLayout.add(deleteMessage, 15, 2);
        mybudgetPane.getChildren().addAll(mybudgetLayout);
        mybudgetLayout.setId("root");
        myBudgetScene = new Scene(mybudgetPane, 1000, 1500);

        // signout buttons returns login view
        signoutButton.setOnAction(e -> {
            mybudgetService.logout();
            primarystage.setScene(loginscene);

        });
        // table view 
        tableView.setOnAction(e -> {
            primarystage.setScene(tablescene);
        });
        // delete useraccount
        deleteUser.setOnAction(e -> {
            try {
                mybudgetService.deleteUser(user);
                mybudgetService.deleteCategory(user);
                mybudgetService.deleteBalance(user);
                mybudgetService.deleteIncome(user);
                mybudgetService.deleteExpense(user);
                deleteMessage.setText(username + "'s user account successfully deleted.");
                primarystage.setScene(loginscene);

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
                    chooseCategory.getItems().addAll(mybudgetService.createChoices(user));
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        // new user scene  
        //create a new expense
        createExpenseButton.setOnAction(e -> {
            if (newExpenseInput.getText().isEmpty()) {

                createErrorMsg.setTextFill(Color.RED);
                createErrorMsg.setText("Enter the value of expense ");

            } else {

                String categoryString = (String) chooseCategory.getValue();
                amountString = newExpenseInput.getText();
                double d = Double.parseDouble(amountString);
                LocalDate expensedate = dateFieldExpense.getValue();
                System.out.println(d);
                try {
                    mybudgetService.createExpense(user.getUsername(), categoryString, d, expensedate);
                    mybudgetService.updateBalanceNewExpense(user.getUsername(), d, expensedate);
                    currentBalance.setText(mybudgetService.updateBalanceLabel());
                } catch (Throwable t) {
                    System.out.println("MybudgetService.createExpense error message ..." + t.getMessage());
                }
            }
        });

        //create income
        createIncomeButton.setOnAction(e -> {
            if (newIncomeInput.getText().isEmpty()) {

                createErrorMsg.setTextFill(Color.RED);
                createErrorMsg.setText("Enter the value of expense ");

            } else {

                String amount = newIncomeInput.getText();
                double d2 = Double.parseDouble(amount);
                LocalDate incomedate = dateFieldIncome.getValue();
                System.out.println(d2);
                try {
                    mybudgetService.createIncome(user.getUsername(), d2, incomedate);
                    mybudgetService.updateBalanceNewIncome(user.getUsername(), d2, incomedate);
                    currentBalance.setText(mybudgetService.updateBalanceLabel());
                } catch (Exception ex) {
                    System.out.println("mybudgetService.createIncome error..." + ex.getMessage());

                    Logger.getLogger(MyBudgetService.class.getName()).log(Level.SEVERE, null, ex);
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

            primarystage.setScene(newUserScene);

        });

        backButton.setOnAction(e -> {
            mybudgetService.logout();
            primarystage.setScene(loginscene);

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
            mybudgetService.createUser(usernameSU, passwordSU);
            if (usernameerror.equals("") && (passworderror.equals(""))) {
                primarystage.setScene(loginscene);
                errorMessage.setText("");
                usernameInput.setText("");
                passwordInput.setText("");
                loginMessage.setText("");
            }

        });

        newUserScene = new Scene(newUserPane, 500, 250);

        primarystage.setScene(loginscene);

        primarystage.show();
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
}
