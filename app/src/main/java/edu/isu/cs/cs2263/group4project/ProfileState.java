package edu.isu.cs.cs2263.group4project;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.Collection;

public class ProfileState implements UIState{
    private Stage stage;

    public ProfileState(Stage stage){
        this.stage = stage;
    }

    public void handle(EventHandler event){

    }

    public void run(){testStage(stage);}

    public void testStage(Stage stage){
        stage.setTitle(App.getUser().getUserInfo().getUsername() + "'s Profile");
        //buttons
        Button back = new Button("Back");
        Button logOut = new Button("LogOut");
        //labels
        Label username = new Label("Username: " + App.getUser().getUserInfo().getUsername());
        Label name = new Label("Name: " + App.getUser().getUserInfo().getFirstName() + " " + App.getUser().getUserInfo().getLastName());
        Label bio = new Label("Bio: " + App.getUser().getUserInfo().getBiography());
        Label email = new Label("Email: " + App.getUser().getUserInfo().getEmail());
        //picture
        ImageView profilePic = new ImageView();
        try{
            Image pic = new Image(new FileInputStream(App.getUser().getUserInfo().getPathToPicture()));
            profilePic.setImage(pic);
        }catch (Exception ex){
            IOManager.logSysInfo("The profile picture for that user could not be found.");
        }
        //containers
        VBox main = new VBox();
        HBox buttons = new HBox();
        HBox userInfo = new HBox();
        VBox labels = new VBox();
        VBox picture = new VBox();
        //build layout
        labels.getChildren().addAll(username, name, email, bio);
        picture.getChildren().addAll(profilePic);
        userInfo.getChildren().addAll(labels, picture);
        buttons.getChildren().addAll(back, logOut);
        main.getChildren().addAll(buttons, userInfo);

        //styling
        main.setAlignment(Pos.TOP_CENTER);
        buttons.setSpacing(20);
        buttons.setPadding(new Insets(10,30,0,0));
        buttons.setAlignment(Pos.TOP_RIGHT);
        userInfo.setSpacing(50);
        userInfo.setPadding(new Insets(10,0,0,30));
        userInfo.setAlignment(Pos.TOP_LEFT);
        labels.setSpacing(10);
        main.setStyle("-fx-background-color: #f2edd7;");

        Scene scene = new Scene(main,500, 300);
        stage.setScene(scene);
        stage.show();

        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getSource() == back){
                    App.setState(new HomePageState(stage));
                }
                if(event.getSource() == logOut){
                    App.setUser(null);
                    App.setState(new LoginState(stage));
                }
            }
        };
        back.setOnMouseClicked(handler);
        logOut.setOnMouseClicked(handler);
    }
}
