package edu.isu.cs.cs2263.group4project;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HomePageState implements UIState {
    private Stage stage;
    public HomePageState(Stage stage) {
        this.stage=stage;
    }

    public void handle(EventHandler event) {
    }

    public void run() {
        testState(stage);
    }

    public void testState(Stage stage) {
        // Creating nodes
        stage.setTitle("Home");
        Button logOut = new Button("Log Out");
        Button makeNewList = new Button("Make new List");
        Button viewArchived = new Button("View Archived Lists");
        Button searchBtn = new Button("Search");

        TextField search = new TextField();
        search.setPromptText("Search here");

        //Listview for lists
        ListView<List> lists = new ListView<List>();
        listView.setPrefWidth(600);
        listView.setPrefHeight(800);
        listView.setStyle("-fx-control-inner-background: #3a635156;");




        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
        gridPane.setVgap(30.0D);
        gridPane.setHgap(20.0D);
        gridPane.setAlignment(Pos.CENTER);


        gridPane.add(search,0,0);
        gridPane.add(makeNewList, 0, 3);
        gridPane.add(searchBtn, 3/2, 0);
        gridPane.add(viewArchived, 2, 3);
        gridPane.add(logOut, 3, 0);
        gridPane.add(listView, 0, 1);


        viewArchived.setStyle("-fx-background-color: #e48257;");
        makeNewList.setStyle("-fx-background-color: #e48257;");
        logOut.setStyle("-fx-background-color: #e48257;");
        searchBtn.setStyle("-fx-background-color: #e48257;");
        gridPane.setStyle("-fx-background-color: #f2edd7;");



        Scene scene = new Scene(gridPane,1000,600);
        stage.setScene(scene);
        stage.show();
    }
}
