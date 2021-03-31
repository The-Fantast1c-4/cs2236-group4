package edu.isu.cs.cs2263.group4project;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignUpState implements UIState {
    private Stage stage;
    public SignUpState(Stage stage) {
        this.stage=stage;
    }

    public void handle(EventHandler event) {

    }

    public void run() {
        testState(stage);
    }



    public void testState(Stage stage) {
        //Creating nodes to put in Grid pane
        stage.setTitle("Sign Up");
        Button back = new Button("Back to Login Page");
        Button signUp = new Button("Sign up");
        TextField userName = new TextField();
        userName.setPromptText("User Name");
        TextField firstName = new TextField();
        firstName.setPromptText("First Name");
        TextField lastName = new TextField();
        lastName.setPromptText("Last Name");
        TextField email = new TextField();
        email.setPromptText("Email Address");
        FileChooser profilePic = new FileChooser();
        profilePic.setTitle("Choose");
        Button profileBtn =new Button("Browse Profile Picture");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        PasswordField passwordConfirm = new PasswordField();
        passwordConfirm.setPromptText("Confirm Password");
        TextArea biography=new TextArea();
        biography.setPromptText("Biography");
        biography.setPrefSize(50,100);
        Label bio = new Label("Tell us about yourself");


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(100.0D, 100.0D, 100.0D, 100.0D));
        gridPane.setVgap(30.0D);
        gridPane.setHgap(100.0D);

        gridPane.setAlignment(Pos.CENTER);


        //adding nodes to grid Pane
        gridPane.add(userName, 1, 0);
        gridPane.add(firstName, 1, 1);
        gridPane.add(lastName, 1, 2);
        gridPane.add(profileBtn, 1, 3);
        gridPane.add(email, 1, 4);
        gridPane.add(passwordField, 1, 5);
        gridPane.add(passwordConfirm, 1, 6);
        gridPane.add(signUp, 1, 7);
        gridPane.add(bio, 2, 0);
        gridPane.add(biography, 2, 1);
        gridPane.add(back, 2, 7);

        //adding color effects to buttons and background
        back.setStyle("-fx-background-color: #e48257;");
        signUp.setStyle("-fx-background-color: #e48257;");
        gridPane.setStyle("-fx-background-color: #f2edd7;");

        //setting scene
        Scene scene = new Scene(gridPane, 1000,600);
        stage.setScene(scene);
        stage.show();

        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getSource()==back){
                    App.setState(new LoginState(stage));
                }
            }
        };

        back.setOnMouseClicked(handler);
    }

}







