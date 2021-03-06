package edu.isu.cs.cs2263.group4project;


import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AdminState implements UIState{
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
        Admin admin = App.getAdmin();
        ArrayList<UserInfo> users = admin.getAllUsers();
        ListView userlist= new ListView();
        for (int i = 0; i < users.size(); i++) {
            userlist.getItems().add(users.get(i).getUsername());

        }
        userlist.blendModeProperty();
        userlist.setPrefWidth(800);
        userlist.setPrefHeight(900);
        userlist.setStyle("-fx-control-inner-background: #3a635156;");


        //creating a grid
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15.0D, 15.0D, 15.0D, 15.0D));
        gridPane.setVgap(20.0D);
        gridPane.setHgap(10.0D);
        gridPane.setAlignment(Pos.CENTER);


        gridPane.add(name,0,0);
        gridPane.add(userlist,0,1);
        gridPane.add(changeSettingsBtn, 0, 4);
        gridPane.add(logOut, 2, 4);


        changeSettingsBtn.setStyle("-fx-background-color: #e48257;");
        logOut.setStyle("-fx-background-color: #e48257;");
        gridPane.setStyle("-fx-background-color: #f2edd7;");



        Scene scene = new Scene(gridPane,1000,600);
        stage.setScene(scene);
        stage.show();
        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getSource()==logOut) {
                    App.setAdmin(null);
                    App.setState(new LoginState(stage));
                }
                if (event.getSource()==userlist){
                    App.setState(new PasswordChangeState(stage));
                }else { if (event.getSource() == changeSettingsBtn){
                    App.setState(new ChangeSettingsState(stage));

                }
                }
            }
        };
        logOut.setOnMouseClicked(handler);
        changeSettingsBtn.setOnMouseClicked(handler);
        userlist.setOnMouseClicked(handler);

    }

}

