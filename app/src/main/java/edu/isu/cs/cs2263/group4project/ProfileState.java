package edu.isu.cs.cs2263.group4project;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        Label username = new Label(App.getUser().getUserInfo().getUsername());
        Label name = new Label(App.getUser().getUserInfo().getFirstName() + " " + App.getUser().getUserInfo().getLastName());
        Label bio = new Label(App.getUser().getUserInfo().getBiography());
        Label email = new Label(App.getUser().getUserInfo().getEmail());
        //picture
        ImageView profilePic = new ImageView();
        try{
            Image pic = new Image(new FileInputStream(App.getUser().getUserInfo().getPathToPicture()));
            profilePic.setImage(pic);
        }catch (Exception ex){
            IOManager.logSysInfo("The profile picture for that user could not be found.");
        }
        //containers
        HBox main = new HBox();
        HBox buttons = new HBox();
        VBox userInfo = new VBox();
        HBox labels = new HBox();
        HBox picture = new HBox();
        //build layout
        labels.getChildren().addAll(username, name, email, bio);
        picture.getChildren().addAll(profilePic);
        userInfo.getChildren().addAll(labels, picture);
        buttons.getChildren().addAll(back, logOut);
        main.getChildren().addAll(buttons, userInfo);
    }
}
