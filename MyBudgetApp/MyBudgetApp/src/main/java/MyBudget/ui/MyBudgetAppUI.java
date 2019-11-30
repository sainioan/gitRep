/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudget.ui;

import mybudgetapp.domain.*;
import java.io.*;
import java.util.*;
import java.sql.SQLException;
import com.sun.javafx.charts.Legend;
import java.lang.Object;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Node;

import javafx.scene.input.MouseEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.text.*;
import mybudgetapp.dao.BudgetDao;
import mybudgetapp.dao.MyBudgetDatabase;
import mybudgetapp.dao.DBUserDao;
import mybudgetapp.dao.DBUserDao;
import mybudgetapp.dao.DBBudgetDao;    
import mybudgetapp.domain.Category;
import mybudgetapp.domain.Expense;
import mybudgetapp.domain.Income;
import mybudgetapp.domain.User;
import mybudgetapp.domain.MyBudgetService;


/**
 *
 * @author ralahtin
 */
public class MyBudgetAppUI extends Application {

    private Scene MyBudgetScene;
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

     String checkUser, checkPw;
    @Override
    public void init() throws Exception {
        MyBudgetDatabase database = new MyBudgetDatabase("mybudgetapp.db");
        mybudgetService = new MyBudgetService(database);
 
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
        Button confirmButton = new Button("confirm");
        Label loginMessage = new Label();

        Label userCreationMessage = new Label();
        bp.setPrefSize(500,250);
        gridpane.add(usernameLabel, 0, 0);
        gridpane.add(usernameInput, 1, 0);
        gridpane.add(passwordLabel, 0, 1);
        gridpane.add(passwordInput, 1, 1);
        gridpane.add(loginButton, 2, 1);
        gridpane.add(loginMessage, 1, 2);
        gridpane.add(signUpButton, 2, 0);

        Reflection reflection = new Reflection();

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
            
        checkUser = usernameInput.getText();
         checkPw = passwordInput.getText();
             this.username = usernameInput.getText();
             this.password = passwordInput.getText();
               if (mybudgetService.login(username, password)) {
                primarystage.setScene(MyBudgetScene);
            } else {
               
              loginMessage.setText("Incorrect username or password.");
                loginMessage.setTextFill(Color.RED);
            }

        });   

       

        // main scene
        try {

            Button logoutButton = new Button("logout");
            GridPane mybudgetLayout = new GridPane();

            mybudgetLayout.add(new Label("Welcome to MyBudgetApp!"), 0, 0);
            mybudgetLayout.add(logoutButton, 2, 2);
            VBox mybudgetappPane = new VBox(10);
            Region menuSpacer = new Region();
            HBox.setHgrow(menuSpacer, Priority.ALWAYS);

            MyBudgetScene = new Scene(mybudgetLayout, 500, 350);

            logoutButton.setOnAction(e -> {
                mybudgetService.logout();
                primarystage.setScene(loginscene);

            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // new user scene  
        VBox newUserPane = new VBox(10);
        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        Text textNewUser = new Text("MyBudgetApp Sign Upp");
        textNewUser.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        //  textNewUser.setEffect(dropShadow);

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
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);
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
        newUserGridPane.add(confirmButton, 0,2);
        newUserGridPane.add(errorMessage, 0,3);
        newUserGridPane.add(errorMessage2, 0,4);
        bpNewUser.setTop(newUsernamePane);
        bpNewUser.setCenter(newUserGridPane);

        newUsernamePane.getChildren().addAll(newUserGridPane);
        newUserPane.getChildren().addAll(newUserGridPane, userCreationMessage, newPasswordPane, newUsernamePane);
      
         signUpButton.setOnAction(e->{
            
            primarystage.setScene(newUserScene);
         
        });
  
        // This code doesn't work:... scene.getStylesheets().add(getClass().getClassLoader().getResource("login.CSS.css").toExternalForm());
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
         if (usernameerror.equals("")&& (passworderror.equals(""))){
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
