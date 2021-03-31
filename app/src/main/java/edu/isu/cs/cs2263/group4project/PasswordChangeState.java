package edu.isu.cs.cs2263.group4project;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PasswordChangeState implements UIState  {
    private Stage stage;
    public PasswordChangeState(Stage stage) {
        this.stage = stage;
    }

    public void handle(Object event) {
    }

    public void run() {
        testState(stage);
    }


    public void testState(Stage stage) {
        //creating child nodes
        stage.setTitle("Password Change");
        Button changePassword = new Button("Change Password");
        Button exit = new Button("Exit   ");
        TextField userFirstLast = new TextField();
        userFirstLast.setPromptText("FirstName LastName");
        PasswordField password = new PasswordField();
        PasswordField verification = new PasswordField();
        PasswordField adminPassword = new PasswordField();
        Label newPassword= new Label("New Password");
        Label verifyPassword = new Label("Verify Password");
        Label adminPass = new Label("Admin Password");

        //creating Gridpane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
        gridPane.setVgap(30.0D);
        gridPane.setHgap(20.0D);
        gridPane.setAlignment(Pos.CENTER);

        //adding nodes to gridPane
        gridPane.add(userFirstLast, 0, 0);
        gridPane.add(newPassword, 0, 1);
        gridPane.add(password, 1, 1);
        gridPane.add(verifyPassword, 0, 2);
        gridPane.add(verification, 1, 2);
        gridPane.add(adminPass, 0, 3);
        gridPane.add(adminPassword, 1, 3);
        gridPane.add(changePassword,0,4);
        gridPane.add(exit,1,4);

        //color of bittons and background
        changePassword.setStyle("-fx-background-color: #e48257;");
        exit.setStyle("-fx-background-color: #e48257;");
        gridPane.setStyle("-fx-background-color: #f2edd7;");

        //adding layout to the scene
        Scene scene = new Scene(gridPane,1000,600);
        stage.setScene(scene);
        stage.show();
    }



}

