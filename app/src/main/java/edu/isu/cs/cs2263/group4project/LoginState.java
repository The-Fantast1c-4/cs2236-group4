

package edu.isu.cs.cs2263.group4project;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LoginState implements UIState {
    public static Stage stage;

    public LoginState(Stage stage) {
        this.stage=stage;
    }


    public void run() {
        testState(stage);
    }


    public void handle(EventHandler event) {

    }

    public static void testState(Stage stage) {
        ArrayList<UserInfo> users = IOManager.loadUserMacro();

        //create nodes
        stage.setTitle("Login");
        Button logIN = new Button("Log in");
        Button signUp = new Button("Sign up");
        TextField userName = new TextField("admin");
        PasswordField passwordField = new PasswordField();
        Label unLabel = new Label("User Name:");
        Label passLabel = new Label("Password:");
        Label instructions = new Label("Don't have an account?");
        Label wrong = new Label("Incorrect Username or Password");

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
        wrong.setStyle("-fx-text-fill: red;");

        //set scene
        Scene scene = new Scene(main,1000,600);
        stage.setScene(scene);
        stage.show();

        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getSource()==signUp){
                    App.setState(new SignUpState(stage));
                }
                if (event.getSource()==logIN){
                    String un = userName.getText();
                    String ps = passwordField.getText();
                    for(UserInfo user : users){
                        StandardUser tempUser = new StandardUser(user);
                        System.out.println(un);
                        System.out.println(tempUser.getUserInfo().getUsername());
                        if (tempUser.getUserInfo().getUsername() == un){
                            System.out.println("yes "+"\n"+tempUser.attemptLogin(ps));
                        }else{System.out.println(tempUser.getUserInfo().getUsername()+" != "+un);}
                    }
                    main.add(wrong ,1,3);
                }
            }
        };
        signUp.setOnMouseClicked(handler);
        logIN.setOnMouseClicked(handler);
    }



}

