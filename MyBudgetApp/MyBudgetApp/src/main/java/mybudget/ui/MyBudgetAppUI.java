/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudget.ui;

//import mybudgetapp.dao.BudgetDao;
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
import javafx.scene.control.ComboBox;
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
    private DBBudgetDao dbbudgetDao;
    private DBUserDao dbuserDao;
    private MyBudgetService mybudgetService;
    private VBox myBudgetNodes;
    private Label menuLabel = new Label();
    private String username;
    private String password;
    private String usernameSU;
    private String passwordSU;
    private String category;
    private User user;
    private  DatePicker dateFieldExpense = new DatePicker();
    private  DatePicker dateFieldIncome = new DatePicker();
    private ComboBox chooseCategory = new ComboBox();

    @Override
    public void init() throws Exception {
        MyBudgetDatabase database = new MyBudgetDatabase("mybudgetapp.db");
        mybudgetService = new MyBudgetService(database, username);

    }
    public void createNewUserScene(){
        
    }

    @Override
    public void start(Stage primarystage) throws Exception {
        init();
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
        Label loginMessage = new Label();

        Label userCreationMessage = new Label();
        bp.setPrefSize(500, 250);
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
                primarystage.setScene(myBudgetScene);
                usernameInput.setText("");
                passwordInput.setText("");
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
        Label createErrorMsg = new Label();
        Label createConfirmationMsg = new Label();
        Label categoryLabel = new Label("New category");
        Label expenseLabel = new Label("New expense: Add amount.");
        Label expenseDate = new Label("Pick expense date");
        Button signoutButton = new Button("Sign out");
        Button createCategoryButton = new Button("Save category");
        Button createExpenseButton = new Button("Save expense");
        TextField newCategoryInput = new TextField();
        TextField newExpenseInput = new TextField();
        GridPane mybudgetLayout = new GridPane();
        chooseCategory.setPromptText("Choose category");
        mybudgetLayout.setPadding(new Insets(10, 10, 10, 10));
        mybudgetLayout.setHgap(5);
        mybudgetLayout.setVgap(5);;

        mybudgetLayout.add(welcome, 0, 0);
        mybudgetLayout.add(categoryLabel, 0, 1);
        mybudgetLayout.add(chooseCategory, 0, 2);
        mybudgetLayout.add(newCategoryInput, 0, 3);
        mybudgetLayout.add(createCategoryButton, 0, 4);
        mybudgetLayout.add(createConfirmationMsg, 0, 5);
        mybudgetLayout.add(signoutButton, 10, 0);
        mybudgetLayout.add(expenseLabel, 4,1);
        mybudgetLayout.add(expenseDate, 4, 3);
        mybudgetLayout.add(newExpenseInput, 4, 2);
        mybudgetLayout.add(dateFieldExpense, 4,4);
        mybudgetLayout.add(createExpenseButton, 4, 5);
       
        
        mybudgetLayout.add(createErrorMsg, 3, 0);
        mybudgetPane.getChildren().addAll(mybudgetLayout);
        mybudgetLayout.setId("root");
        myBudgetScene = new Scene(mybudgetPane, 1000, 1500);

        // signout buttons returns login view
        signoutButton.setOnAction(e -> {
            mybudgetService.logout();
            primarystage.setScene(loginscene);

        });
// create a new category
        createCategoryButton.setOnAction(e -> {
            if (newCategoryInput.getText().isEmpty()) {

                createErrorMsg.setTextFill(Color.RED);
                createErrorMsg.setText("Type the name of the category");

            } else {

                category = newCategoryInput.getText();
                mybudgetService.createCategory(user.getUsername(), category);
                createConfirmationMsg.setText("Category '" + category + "' created successfully");
                createConfirmationMsg.setTextFill(Color.GREEN);
                newCategoryInput.setText("");

            }
        });
        // new user scene  
  // create a new expense
  // createExpenseButton.setOnAction(e -> { 
  //String amountString = TextField.getString();
  //double d = Double.parseDouble(amountString); 
  // });
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
            }

        });

        newUserScene = new Scene(newUserPane, 500, 250);

        primarystage.setScene(loginscene);

        primarystage.show();
    }

    @Override
    public void stop() {
    }

    public static void main(String[] args) {
        launch(args);
    }
}
