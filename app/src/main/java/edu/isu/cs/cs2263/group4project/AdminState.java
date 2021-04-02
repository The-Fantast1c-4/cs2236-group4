package edu.isu.cs.cs2263.group4project;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AdminState{
    private Stage stage;
    public AdminState(Stage stage) {
        this.stage=stage;
    }

    public void handle(EventHandler event) {
    }

    public void run() {
        testState(stage);
    }

    public void testState(Stage stage) {
        // Creating nodes
        stage.setTitle("Admin");
        Button changeSettingsBtn = new Button("Change Settings");
        Button logOut = new Button("Log Out");
        Label name=new Label("Users");
        name.setStyle("-fx-font-weight: bold");
        name.setFont(new Font("Arial", 30));

        //Listview for users
        ListView users = new ListView();
        users.blendModeProperty();
        users.setPrefWidth(800);
        users.setPrefHeight(900);
        users.setStyle("-fx-control-inner-background: #3a635156;");


        //creating a grid 
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
        gridPane.setVgap(30.0D);
        gridPane.setHgap(20.0D);
        gridPane.setAlignment(Pos.CENTER);


        gridPane.add(name,0,0);
        gridPane.add(users,0,1);
        gridPane.add(changeSettingsBtn, 0, 4);
        gridPane.add(logOut, 2, 4);


        changeSettingsBtn.setStyle("-fx-background-color: #e48257;");
        logOut.setStyle("-fx-background-color: #e48257;");
        gridPane.setStyle("-fx-background-color: #f2edd7;");



        Scene scene = new Scene(gridPane,1000,600);
        stage.setScene(scene);
        stage.show();
    }
}
