/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyBudget.ui;

import mybudgetapp.domain.*;
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
import mybudgetapp.domain.MyBudgetService;

/**
 *
 * @author ralahtin
 */
public class MyBudgetAppUI extends Application {

    private Scene MyBudgetScene;
    private BudgetDao budgetDao;
    private MyBudgetService mybudgetservice;
    private Scene newUserScene;
    private Scene loginscene;
    private VBox myBudgetNodes;
    private Label menuLabel;
    private MyBudgetService mbs;
//   private User user;
    // get username and password from user.
    // String username = user.getUsername();
    String username = "testUser";
    //   String password = user.getPassword();
    String password = "TU123";
    String checkUser, checkPw;

    @Override
    public void init() throws Exception {
        mybudgetservice = new MyBudgetService(budgetDao);
    }

    @Override
    public void start(Stage primarystage) throws Exception {
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
        Label loginMessage = new Label();
        VBox newUserPane = new VBox(10);
        menuLabel = new Label(username + " ...logged in.");
        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("username");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);
        HBox newPasswordPane = new HBox(10);
        newPasswordPane.setPadding(new Insets(10));
        TextField newPasswordInput = new TextField();
        Label newNameLabel = new Label("name");
        newNameLabel.setPrefWidth(100);
        newPasswordPane.getChildren().addAll(newUsernameLabel, newPasswordInput);
        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));

        //  HBox MyBudgetPane = new HBox(10);
        gridpane.add(usernameLabel, 0, 0);
        gridpane.add(usernameInput, 1, 0);
        gridpane.add(passwordLabel, 0, 1);
        gridpane.add(passwordInput, 1, 1);
        gridpane.add(loginButton, 2, 1);
        gridpane.add(loginMessage, 1, 2);

        Reflection reflection = new Reflection();
// Not sure I want the reflection:   reflection.setFraction(0.7f);

//    gridpane.setEffect(reflection);
//    
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        Text text = new Text("MyBudgetApp Login");
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        text.setEffect(dropShadow);
        inputPane.getChildren().add(text);
        bp.setId("bp");
        gridpane.setId("root");
        loginButton.setId("loginButton");
        text.setId("text");

        bp.setTop(inputPane);
        bp.setCenter(gridpane);
//         
        //Adding BorderPane to the scene and loading CSS
        loginscene = new Scene(bp);
        loginButton.setOnAction(e -> {
            checkUser = usernameInput.getText().toString();
            checkPw = passwordInput.getText().toString();

            if (checkUser.equals(username) && checkPw.equals(password)) {
             
//                loginMessage.setText("Login successful!");
//                loginMessage.setTextFill(Color.GREEN);

                // code to be added: open the app
                primarystage.setScene(MyBudgetScene);

            } else {
                loginMessage.setText("Incorrect username or password.");
                loginMessage.setTextFill(Color.RED);
            }
            usernameInput.setText("");
            passwordInput.setText("");

        });
//
//        createNewUserButton.setOnAction(e->{
//            String username = newUsernameInput.getText();
//            String name = newNameInput.getText();
//   
//            if ( username.length()==2 || name.length()<2 ) {
//                userCreationMessage.setText("username or name too short");
//                userCreationMessage.setTextFill(Color.RED);              
//            } else if ( todoService.createUser(username, name) ){
//                userCreationMessage.setText("");                
//                loginMessage.setText("new user created");
//                loginMessage.setTextFill(Color.GREEN);
//                primarystage.setScene(loginScene);      
//            } else {
//                userCreationMessage.setText("username has to be unique");
//                userCreationMessage.setTextFill(Color.RED);        
//            }
// 
//        });  

        // main scene
        try {

           //  ScrollPane mybudgetScrollbar = new ScrollPane();
            //  BorderPane mainPane = new BorderPane(mybudgetScrollbar);
            Button logoutButton = new Button("logout");
            GridPane mybudgetLayout = new GridPane();

            mybudgetLayout.add(new Label("Welcome to MyBudgetApp!"), 0, 0);
            mybudgetLayout.add(logoutButton, 2, 2);
            VBox mybudgetappPane = new VBox(10);
            Region menuSpacer = new Region();
            HBox.setHgrow(menuSpacer, Priority.ALWAYS);

            MyBudgetScene = new Scene(mybudgetLayout, 500, 350);
            //      mybudgetappPane.getChildren().addAll(logoutButton, menuLabel, menuSpacer, logoutButton);
            //mybudgetappPane.getChildren().addAll(logoutButton,  menuSpacer);

            logoutButton.setOnAction(e -> {
                //mybudgetservice.logout();
                primarystage.setScene(loginscene);

            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        newUserPane.getChildren().addAll(userCreationMessage, newPasswordPane, newUsernamePane, createNewUserButton);

        // This code doesn't work:... scene.getStylesheets().add(getClass().getClassLoader().getResource("login.CSS.css").toExternalForm());
        newUserScene = new Scene(newUserPane, 300, 250);

        primarystage.setScene(loginscene);

        primarystage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
