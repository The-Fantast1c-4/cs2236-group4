package edu.isu.cs.cs2263.group4project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

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
        ObservableList<List> userLists = FXCollections.observableArrayList(App.getUser().getLists().getNonArchivedLists());

        ListView<List> lists = new ListView<>(userLists);
        lists.setPrefWidth(600);
        lists.setPrefHeight(800);
        lists.setStyle("-fx-control-inner-background: #3a635156;");




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
        gridPane.add(lists, 0, 1);



        viewArchived.setStyle("-fx-background-color: #e48257;");
        makeNewList.setStyle("-fx-background-color: #e48257;");
        logOut.setStyle("-fx-background-color: #e48257;");
        searchBtn.setStyle("-fx-background-color: #e48257;");
        gridPane.setStyle("-fx-background-color: #f2edd7;");



        Scene scene = new Scene(gridPane,1000,600);
        stage.setScene(scene);
        stage.show();

        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getSource()==makeNewList) {
                    Stage newList = new Stage();
                    newList.setTitle("New List");
                    //create nodes
                    TextField name = new TextField();
                    name.setPromptText("Name");
                    TextField description = new TextField();
                    description.setPromptText("Description");
                    Button save = new Button("Save");
                    Button cancel = new Button("Cancel");

                    //create containers
                    HBox buttonBox = new HBox();
                    VBox main = new VBox();

                    //fill containers
                    buttonBox.getChildren().addAll(save,cancel);
                    main.getChildren().addAll(name,description,buttonBox);


                    newList.setX(stage.getX() + (stage.getWidth() / 2) - 200);
                    newList.setY(stage.getY() + (stage.getHeight() / 2) - 150);
                    newList.setWidth(400);
                    newList.setHeight(300);

                    newList.setScene(new Scene(main));
                    newList.show();

                    main.setStyle("-fx-background-color: #f2edd7");
                    save.setStyle("-fx-background-color: #e48257");
                    cancel.setStyle("-fx-background-color: #e48257");

                    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(event.getSource()==cancel){
                                newList.close();
                            }
                            if(event.getSource()==save||(name.getText()!="")){
                                String sName = name.getText();
                                String sDescription = description.getText();
                                App.getUser().getLists().makeList(sName,sDescription);
                                IOManager.saveUser(App.getUser());
                                lists.getItems().add(App.getUser().getLists().getList(sName));
                                App.setState(new HomePageState(stage));
                                newList.close();
                            }

                        }
                    };
                    cancel.setOnMouseClicked(handler1);
                    save.setOnMouseClicked(handler1);
                }
                if(event.getSource()==logOut){
                    App.setUser(null);
                    App.setState(new LoginState(stage));
                }
                if(event.getSource()==lists){
                    App.setState(new TaskPageState(stage,lists.getFocusModel().getFocusedItem().getName()));
                }
                if(event.getSource()==searchBtn){
                    SearchingVisitor v = new SearchingVisitor(search.getText());
                    App.getUser().getLists().accept(v);
                    ObservableList<List> matches = FXCollections.observableArrayList(v.getListMatches());
                    lists.getItems().clear();
                    lists.getItems().addAll(matches);
                }
                if(event.getSource()==viewArchived){
                    if(viewArchived.getText().equals("View Archived Lists")) {
                        ObservableList<List> archivedLists = FXCollections.observableArrayList(App.getUser().getLists().getArchivedLists());
                        lists.getItems().clear();
                        lists.getItems().addAll(archivedLists);
                        viewArchived.setText("      View Lists      ");
                    }
                    else if(viewArchived.getText().equals("      View Lists      ")){
                        ObservableList<List> userLists = FXCollections.observableArrayList(App.getUser().getLists().getNonArchivedLists());
                        lists.getItems().clear();
                        lists.getItems().addAll(userLists);
                        viewArchived.setText("View Archived Lists");
                    }
                }
            }
        };
        makeNewList.setOnMouseClicked(handler);
        logOut.setOnMouseClicked(handler);
        lists.setOnMouseClicked(handler);
        searchBtn.setOnMouseClicked(handler);
        viewArchived.setOnMouseClicked(handler);
        }

    }

