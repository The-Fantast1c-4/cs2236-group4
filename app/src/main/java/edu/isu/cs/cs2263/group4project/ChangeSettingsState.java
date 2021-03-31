package edu.isu.cs.cs2263.group4project;

import javafx.application.Application;
import javafx.event.EventHandler;
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

public class ChangeSettingsState implements UIState {
    private Stage stage;
    public ChangeSettingsState(Stage stage) {
        this.stage=stage;
    }


    public void handle(EventHandler event) {
    }

    public void run() {
        this.testState(stage);
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
        //style nodes
        backB.setStyle("-fx-background-color: #e48257;");
        saveB.setStyle("-fx-background-color: #e48257;");
        main.setStyle("-fx-background-color: #f2edd7;");

        //set scene
        Scene scene = new Scene(main,1000,600);
        // load the stylesheet
        stage.setScene(scene);
        stage.show();
    }


}
