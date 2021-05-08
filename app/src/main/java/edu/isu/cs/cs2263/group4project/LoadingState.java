package edu.isu.cs.cs2263.group4project;

import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LoadingState implements UIState {
    public static Stage stage;

    public LoadingState(Stage stage) {
        this.stage=stage;
    }


    public void run() {
        testState(stage);
    }


    public void handle(EventHandler event) {

    }

    public static void testState(Stage stage){

        //create nodes
        stage.setTitle("Loading");
        ImageView imageView= new ImageView();
        try{
            Image logo = new Image(new FileInputStream("./Logo/logo.png"));
            imageView = new ImageView(logo);
        }catch(FileNotFoundException e){ }

        VBox main = new VBox();
        main.getChildren().add(imageView);
        main.setAlignment(Pos.CENTER);
        main.setStyle("-fx-background-color: #f2edd7; ");
        Scene scene = new Scene(main,1000,600);
        stage.setScene(scene);
        stage.show();
        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(event ->
                {App.setState(new LoginState(stage)); }
        );
        pause.play();




    }



}

