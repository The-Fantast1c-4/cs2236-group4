

package edu.isu.cs.cs2263.group4project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginState implements UIState {
    public static Stage stage;
    public LoginState(Stage stage) {
        this.stage=stage;
    }


    public void handle(Object event) {
    }

    public void run() {
        testState(stage);
    }


    public static void testState(Stage stage) {
        //create nodes
        //LoginState.stage = primaryStage;
        stage.setTitle("Login");
        Button logIN = new Button("Log in");
        logIN.setOnMouseClicked(value->{
            });
        Button signUp = new Button("Sign up");
        signUp.setOnMouseClicked(value->{
            App.setState(new SignUpState(stage));
            App.getState().run();
        });
        TextField userName = new TextField();
        PasswordField passwordField = new PasswordField();
        Label unLabel = new Label("User Name:");
        Label passLabel = new Label("Password:");
        Label instructions = new Label("Dont have an accout?");

        //create grid
        GridPane main = new GridPane();
        main.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
        main.setVgap(30.0D);
        main.setHgap(20.0D);
        main.setAlignment(Pos.CENTER);

        //add nodes to grid
        main.add(unLabel, 0, 0);
        main.add(passLabel, 0, 1);
        main.add(userName, 1, 0);
        main.add(passwordField, 1, 1);
        main.add(logIN, 1, 2);
        main.add(instructions, 2, 0);
        main.add(signUp, 2, 1);
        //style nodes

        logIN.setStyle("-fx-background-color: #e48257;");
        signUp.setStyle("-fx-background-color: #e48257;");
        main.setStyle("-fx-background-color: #f2edd7;");

        //set scene
        Scene scene = new Scene(main,1000,600);
        stage.setScene(scene);
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

