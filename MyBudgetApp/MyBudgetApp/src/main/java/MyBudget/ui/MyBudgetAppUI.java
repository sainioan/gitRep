/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyBudget.ui;
import mybudgetapp.domain.*;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 *
 * @author ralahtin
 */
public class MyBudgetAppUI extends Application {
    
    private Scene MyBudgetScene;
    private Scene newUserScene;
    private Scene loginScene;
    private VBox myBudgetNodes;
    private Label menuLabel;
    private MyBudgetService mbs;
    User user;
    // get username and password from user.
    String username = user.getUsername();
    String password = user.getPassword();
    String checkUser, checkPw;
        
    public static void main(String args){
    launch(args);
}
    @Override 
    public void start(Stage primarystage) throws Exception {
    primarystage.setTitle("MyBudgetApp");
    
    BorderPane bp = new BorderPane();
    bp.setPadding(new Insets(10,50,50,50));
    
    HBox inputPane = new HBox();    
    inputPane.setPadding(new Insets(20,20,20,30));
    
    GridPane gridpane = new GridPane();
    gridpane.setPadding(new Insets(20,20,20,20));
    gridpane.setHgap(5);
    gridpane.setVgap(5);
   
    Label usernameLabel = new Label("Username"); 
    TextField usernameInput = new TextField();
    Label passwordLabel = new Label("Password");
    PasswordField passwordInput = new PasswordField();
    Button loginButton = new Button("Login");
    Label loginMessage = new Label();
 
 //   Button signupButton = new Button("Sign Up");
    gridpane.add(usernameLabel, 0,0);
    gridpane.add(usernameInput, 1,0);
    gridpane.add(passwordLabel, 0,1);
    gridpane.add(passwordInput, 1,1);
    gridpane.add(loginButton,2,1);
    gridpane.add(loginMessage,1,2);
    
    Reflection reflection = new Reflection();
    reflection.setFraction(0.7f);
    gridpane.setEffect(reflection);
    
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
   loginButton.setOnAction((event)-> {
              checkUser = usernameInput.getText().toString();
          checkPw = passwordInput.getText().toString();
          if(checkUser.equals(username) && checkPw.equals(password)){
           loginMessage.setText("Login successful!");
           loginMessage.setTextFill(Color.GREEN);
          }
          else{
           loginMessage.setText("Incorrect username or password.");
           loginMessage.setTextFill(Color.RED);
          }
          usernameInput.setText("");
          passwordInput.setText("");
         
         });
   
//     loginButton.setOnAction(new EventHandler<ActionEvent>(){
//         @Override
//         public void handle(ActionEvent event) {
//          checkUser = usernameInput.getText().toString();
//          checkPw = passwordInput.getText().toString();
//          if(checkUser.equals(username) && checkPw.equals(password)){
//           loginMessage.setText("Login successful!");
//           loginMessage.setTextFill(Color.GREEN);
//          }
//          else{
//           loginMessage.setText("Incorrect username or password.");
//           loginMessage.setTextFill(Color.RED);
//          }
//          usernameInput.setText("");
//          passwordInput.setText("");
//         }
//         });

   bp.setTop(inputPane);
        bp.setCenter(gridpane);  
         
        //Adding BorderPane to the scene and loading CSS
     Scene scene = new Scene(bp);
    scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
     primarystage.setScene(scene);
       primarystage.titleProperty().bind(
                 scene.widthProperty().asString().
                 concat(" : ").
                 concat(scene.heightProperty().asString()));
     primarystage.setResizable(false);
     primarystage.show();
    }
}
