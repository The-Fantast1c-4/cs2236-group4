package edu.isu.cs.cs2263.group4project;

import com.google.common.collect.ForwardingTable;
import com.google.common.collect.Table;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskPageState implements UIState{
    private Stage stage;

    public TaskPageState(Stage stage) {
        this.stage = stage;
    }

    public void handle(Object event) {

    }

    public void run() {
        testState(stage);
    }
    public void testState(Stage stage){
        stage.setTitle("List Name Here");
        //create nodes
        Label searchLabel = new Label("Search");
        TextField searchBar = new TextField();
        Button logOut = new Button("LogOut");
        Button back = new Button("Back");
            //buttons for button bar
        Button delete = new Button("Delete");
        Button moveList = new Button("Move List");
        Button moveSection = new Button("Move Section");
        Button duplicate = new Button("Duplicate");
        Button viewTask = new Button("View Task");

        Label section = new Label("Default");
        //table
        TableView<Task> tasks = new TableView();
        TableColumn<Task, String> taskColumn = new TableColumn<>("Task");
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tasks.getColumns().add(taskColumn);
        tasks.getItems().add(new Task("task",2,""));

        ComboBox comments = new ComboBox();
        comments.getItems().addAll("Comments");
        Button addComment = new Button("+");
        Label sorting = new Label("Sort List By ...");
        CheckBox labelSort = new CheckBox("Label");
        CheckBox prioritySort = new CheckBox("Priority");
        CheckBox dueDateSort = new CheckBox("Due Date");
        CheckBox completedSort = new CheckBox("Completed");

            //button for bottom button bar
        Button makeTask = new Button("Add Task");
        Button addSection = new Button("Add Section");
        Button archiveList  = new Button("Archive List");
        Button addSublist = new Button("Add Sublist");

        Label subListLabel = new Label("Sublists");
        ListView<SubList> subLists = new ListView<SubList>();

        //create Layout
        VBox main = new VBox();
        HBox topBar = new HBox();
        HBox body = new HBox();
        VBox leftSide = new VBox();
        VBox rightSide = new VBox();
        HBox commentBox = new HBox();
        HBox topButtonBar = new HBox();
        HBox taskBox = new HBox();
        VBox sectionLabels = new VBox();
        HBox bottomButtonBar = new HBox();

        //fill layout
        bottomButtonBar.getChildren().addAll(makeTask,addSection,archiveList,addSublist);
        sectionLabels.getChildren().addAll(section);
        taskBox.getChildren().addAll(sectionLabels,tasks);
        topButtonBar.getChildren().addAll(delete,moveList,moveSection,duplicate,viewTask);
        commentBox.getChildren().addAll(comments,addComment);
        rightSide.getChildren().addAll(commentBox,sorting,labelSort,prioritySort,dueDateSort,completedSort,subListLabel,subLists);
        leftSide.getChildren().addAll(topButtonBar,taskBox,bottomButtonBar);
        body.getChildren().addAll(leftSide,rightSide);
        topBar.getChildren().addAll(searchLabel,searchBar,back,logOut);
        main.getChildren().addAll(topBar,body);
        //style layout
        main.setAlignment(Pos.CENTER);
        topBar.setSpacing(20);
        topBar.setPadding(new Insets(10,0,0,150));
        topBar.setAlignment(Pos.TOP_LEFT);
        body.setSpacing(50);
        body.setPadding(new Insets(10,0,0,0));
        body.setAlignment(Pos.CENTER);
        rightSide.setSpacing(5);
        leftSide.setSpacing(10);
        taskBox.setSpacing(15);
        subLists.setMaxSize(100000,200);
        topButtonBar.setSpacing(2);
        bottomButtonBar.setSpacing(10);
        //style nodes
        back.setStyle("-fx-background-color: #e48257");
        logOut.setStyle("-fx-background-color: #e48257");

        delete.setStyle("-fx-text-fill: #f2edd7;-fx-background-color: #393232");
        moveList.setStyle("-fx-text-fill: #f2edd7;-fx-background-color: #393232");
        moveSection.setStyle("-fx-text-fill: #f2edd7;-fx-background-color: #393232");
        duplicate.setStyle("-fx-text-fill: #f2edd7;-fx-background-color: #393232");
        viewTask.setStyle("-fx-text-fill: #f2edd7;-fx-background-color: #393232");

        addComment.setStyle("-fx-background-color: #e48257");

        makeTask.setStyle("-fx-background-color: #e48257");
        addSection.setStyle("-fx-background-color: #e48257");
        addSublist.setStyle("-fx-background-color: #e48257");
        archiveList.setStyle("-fx-background-color: #e48257");


        main.setStyle("-fx-background-color: #f2edd7");


        //set scene
        Scene scene = new Scene(main,1000,600);
        stage.setScene(scene);
        stage.show();


    }


}
