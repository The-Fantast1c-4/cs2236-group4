package edu.isu.cs.cs2263.group4project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ChangeSettingsState extends Application implements UiInterface {
    public ChangeSettingsState() {
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void handle(Object event) {
    }

    public void run() {
    }

    public void start(Stage primaryStage) throws Exception {
        this.testState(primaryStage);
    }

    public void testState(Stage stage) {
        //create nodes
        Label logLocation = new Label("Log Location");
        Button defaultLL = new Button("Default");
        FileChooser browse = new FileChooser();
        Label sULabel = new Label("Store User");
        Label itemLabel = new Label("Items per page");
        ChoiceBox itemCount = new ChoiceBox();
            for (int i = 1; i<31; i++){
                itemCount.getItems().add(i);
            }
        ChoiceBox storeUSer = new ChoiceBox();
            storeUSer.getItems().addAll("A/c Base Program", "A/c Home Directory");
        Button backB = new Button("Back");
        Button saveB = new Button("Save");

        //make main scene
        VBox log = new VBox();
            log.setAlignment(Pos.TOP_LEFT);
            log.setPadding(new Insets(0,50,0,10));
        VBox store = new VBox();
            store.setAlignment(Pos.TOP_LEFT);
        HBox top = new HBox();
            top.setPadding(new Insets(10,10,50,10));
            top.setAlignment(Pos.CENTER_LEFT);
        HBox bottom = new HBox();
            bottom.setPadding(new Insets(0,30,0,10));
            bottom.setSpacing(30);
            bottom.setAlignment(Pos.BOTTOM_LEFT);

        VBox main = new VBox();
            main.setAlignment(Pos.CENTER);
            main.setPadding(new Insets(10,10,10,10));
        //fill main scene

        main.getChildren().addAll(top,bottom);
        bottom.getChildren().addAll(itemLabel,itemCount,backB,saveB);
        top.getChildren().addAll(log, store);
        store.getChildren().addAll(sULabel,storeUSer);
        log.getChildren().addAll(logLocation,defaultLL);

        //set scene
        Scene scene = new Scene(main);
        // load the stylesheet
        scene.getStylesheets().add("stylesheet.css");
        stage.setScene(scene);
        stage.show();
    }
}
