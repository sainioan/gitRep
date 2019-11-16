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

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    
    @Override 
    public void start(Stage stage){
    VBox loginPane = new VBox(10);
    HBox inputPane = new HBox(10);
    loginPane.setPadding(new Insets(10));
    Label loginLabel = new Label("username");
    TextField usernameInput = new TextField();
    inputPane.getChildren().addAll(loginLabel,usernameInput);
    Label loginMessage = new Label();
    Button loginButton = new Button("Login");
    Button signupButton = new Button("Sign Up");
             stage.setTitle("MyBudgetApp");
        stage.setScene(loginScene);
       stage.show();
        
        
    }public static void main(String args){
    launch(args);
}
}
