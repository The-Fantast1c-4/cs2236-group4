package edu.isu.cs.cs2263.group4project;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskViewState implements UIState{
    private Stage stage;
    private List list;
    private Task task;

    public TaskViewState(Stage stage, String list, String section, String task){
        this.stage = stage;
        this.task = App.getUser().getLists().getList(list).getSection(section).getTask(task);
    }

    public void handle(EventHandler event){

    }

    public void run(){ testState(stage);}

    public void testState(Stage stage){
        stage.setTitle("Task View");
        CheckBox completed = new CheckBox("Completed");

        Button logOut = new Button("LogOut");
        Button back = new Button("Back");

        Button editTask = new Button("Edit Task");
        Button addSubtask = new Button("Add Subtask");

        Label name = new Label(task.getName());
        Label dueDate = new Label("Due: " + task.getDueDate());
        Label overdue = new Label("Overdue");
        Label priority = new Label("Priority: " + task.getPriority());

        TextArea description = new TextArea(task.getDescription());

        ListView<SubTask> subtasks = new ListView<SubTask>();
        ComboBox labels = new ComboBox();
        Button addLabel = new Button("+");

        VBox main = new VBox();
        HBox topBar = new HBox();
        HBox body = new HBox();
        HBox bottomBar = new HBox();
        VBox leftSide = new VBox();
        VBox rightSide = new VBox();
        HBox descBox = new HBox();
        HBox propBox = new HBox();
        HBox labelBox = new HBox();
        HBox subtaskBox = new HBox();

        topBar.getChildren().addAll(name, completed, back, logOut);
        descBox.getChildren().addAll(description);
        propBox.getChildren().addAll(dueDate, overdue, priority);
        leftSide.getChildren().addAll(descBox, propBox);
        labelBox.getChildren().addAll(labels, addLabel);
        subtaskBox.getChildren().addAll(subtasks);
        rightSide.getChildren().addAll(labelBox, subtaskBox);
        body.getChildren().addAll(leftSide, rightSide);
        bottomBar.getChildren().addAll(editTask, addSubtask);
        main.getChildren().addAll(topBar, body, bottomBar);

        main.setAlignment(Pos.CENTER);
        topBar.setSpacing(20);
        topBar.setPadding(new Insets(10,0,0,150));
        topBar.setAlignment(Pos.TOP_LEFT);
        body.setSpacing(50);
        body.setPadding(new Insets(10,0,0,0));
        body.setAlignment(Pos.CENTER);
        bottomBar.setSpacing(20);
        bottomBar.setPadding(new Insets(10,0,0,150));
        bottomBar.setAlignment(Pos.BOTTOM_LEFT);
        rightSide.setSpacing(5);
        leftSide.setSpacing(10);
        subtasks.setMaxSize(100000,200);

        back.setStyle("-fx-background-color: #e48257");
        logOut.setStyle("-fx-background-color: #e48257");

        editTask.setStyle("-fx-background-color: #e48257");
        addSubtask.setStyle("-fx-background-color: #e48257");
        addLabel.setStyle("-fx-background-color: #e48257");

        main.setStyle("-fx-background-color: #f2edd7");

        Scene scene = new Scene(main,1000,600);
        stage.setScene(scene);
        stage.show();
    }
}
