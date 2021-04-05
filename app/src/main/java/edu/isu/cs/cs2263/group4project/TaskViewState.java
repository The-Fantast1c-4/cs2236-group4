package edu.isu.cs.cs2263.group4project;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TaskViewState implements UIState{
    private Stage stage;
    private List list;
    private Task task;

    public TaskViewState(Stage stage, String list,String subList, String section, String task){
        this.stage = stage;
        this.task = App.getUser().getLists().getList(list).getSublist(subList).getSection(section).getTask(task);
    }

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
        //buttons
        Button logOut = new Button("LogOut");
        Button back = new Button("Back");

        Button editTask = new Button("Edit Task");
        Button addSubtask = new Button("Add Subtask");
        //task value labels
        Label name = new Label(task.getName());
        Label dueDate = new Label("Due: " + task.getDueDate());
        Label overdue = new Label("Overdue");
        Label priority = new Label("Priority: " + task.getPriority());
        TextArea description = new TextArea(task.getDescription());
        //subtask and label containers
        ListView<SubTask> subtasks = new ListView<SubTask>();
        ComboBox labels = new ComboBox();
        Button addLabel = new Button("+");
        //containers
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
        //build layout
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
        //styling
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
        //set scene
        Scene scene = new Scene(main,1000,600);
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
                if(event.getSource() == addSubtask){
                    Stage newSubTask = new Stage();
                    newSubTask.setTitle("New Subtask");
                    //create nodes
                    Label titleLabel = new Label("Title");
                    Label descriptionLabel = new Label("Description");
                    Label priorityLabel = new Label("Priority");
                    Label dueDateLabel = new Label("Due Date");
                    TextField titleText = new TextField();
                    TextField descriptionText = new TextField();
                    ComboBox<Integer> priorityList = new ComboBox<>();
                    priorityList.getItems().addAll(1,2,3,4,5);
                    priorityList.setValue(1);
                    DatePicker dueDatePicker = new DatePicker();
                    dueDatePicker.setValue(LocalDate.now());
                    Button createTask = new Button("Create Task");
                    Button cancel = new Button("Cancel");
                    //create containers
                    GridPane main = new GridPane();

                    //fill containers
                    main.add(titleLabel,0,0);
                    main.add(descriptionLabel,0,1);
                    main.add(priorityLabel,0,2);
                    main.add(dueDateLabel,0,3);
                    main.add(createTask,0,4);

                    main.add(titleText,1,0);
                    main.add(descriptionText,1,1);
                    main.add(priorityList,1,2);
                    main.add(dueDatePicker,1,3);
                    main.add(cancel,1,4);


                    newSubTask.setX(stage.getX() + (stage.getWidth() / 2) - 200);
                    newSubTask.setY(stage.getY() + (stage.getHeight() / 2) - 150);
                    newSubTask.setWidth(400);
                    newSubTask.setHeight(300);

                    newSubTask.setScene(new Scene(main));
                    newSubTask.show();

                    //style
                    main.setStyle("-fx-background-color: #f2edd7");
                    cancel.setStyle("-fx-background-color: #e48257");
                    createTask.setStyle("-fx-background-color: #e48257");

                    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(event.getSource()==cancel){
                                newSubTask.close();
                            }
                            if(event.getSource()==createTask){
                                String title = titleText.getText();
                                String description = descriptionText.getText();
                                int priority = priorityList.getValue();
                                LocalDate dueDate = dueDatePicker.getValue();


                                ZoneId defaultZoneID = ZoneId.systemDefault();
                                Date date = Date.from(dueDate.atStartOfDay(defaultZoneID).toInstant());

                                SubTask tempTask = new SubTask(title,priority,description, date);
                                task.addSubTask(tempTask);
                                IOManager.saveUser(App.getUser());
                                subtasks.getItems().add(tempTask);
                                newSubTask.close();

                            }
                        }
                    };
                    cancel.setOnMouseClicked(handler1);
                    createTask.setOnMouseClicked(handler1);

                }
                if(event.getSource() == editTask){
                    Stage editTask = new Stage();
                    editTask.setTitle("Edit Task");
                    //create nodes
                    Label titleLabel = new Label("Title");
                    Label descriptionLabel = new Label("Description");
                    Label priorityLabel = new Label("Priority");
                    Label dueDateLabel = new Label("Due Date");
                    TextField titleText = new TextField();
                    titleText.setText(task.getName());
                    TextField descriptionText = new TextField();
                    descriptionText.setText(task.getDescription());
                    ComboBox<Integer> priorityList = new ComboBox<>();
                    priorityList.getItems().addAll(1,2,3,4,5);
                    priorityList.setValue(task.getPriority());
                    DatePicker dueDatePicker = new DatePicker();
                    Button createTask = new Button("Edit Task");
                    Button cancel = new Button("Cancel");
                    //create containers
                    GridPane main = new GridPane();

                    //fill containers
                    main.add(titleLabel,0,0);
                    main.add(descriptionLabel,0,1);
                    main.add(priorityLabel,0,2);
                    main.add(dueDateLabel,0,3);
                    main.add(createTask,0,4);

                    main.add(titleText,1,0);
                    main.add(descriptionText,1,1);
                    main.add(priorityList,1,2);
                    main.add(dueDatePicker,1,3);
                    main.add(cancel,1,4);


                    editTask.setX(stage.getX() + (stage.getWidth() / 2) - 200);
                    editTask.setY(stage.getY() + (stage.getHeight() / 2) - 150);
                    editTask.setWidth(400);
                    editTask.setHeight(300);

                    editTask.setScene(new Scene(main));
                    editTask.show();

                    //style
                    main.setStyle("-fx-background-color: #f2edd7");
                    cancel.setStyle("-fx-background-color: #e48257");
                    createTask.setStyle("-fx-background-color: #e48257");

                    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(event.getSource()==cancel){
                                editTask.close();
                            }
                            if(event.getSource()==createTask){
                                String title = titleText.getText();
                                String description = descriptionText.getText();
                                int temppriority = priorityList.getValue();
                                LocalDate dueDate = dueDatePicker.getValue();


                                ZoneId defaultZoneID = ZoneId.systemDefault();
                                Date date = Date.from(dueDate.atStartOfDay(defaultZoneID).toInstant());

                                task.setName(title);
                                task.setDescription(description);
                                task.setDueDate(date);
                                task.setPriority(temppriority);

                                IOManager.saveUser(App.getUser());
                                editTask.close();

                            }
                        }
                    };
                    cancel.setOnMouseClicked(handler1);
                    createTask.setOnMouseClicked(handler1);

                }
                if(event.getSource() == addLabel){
                    
                }
            }
        };

        back.setOnMouseClicked(handler);
        logOut.setOnMouseClicked(handler);
        addSubtask.setOnMouseClicked(handler);
        editTask.setOnMouseClicked(handler);
        addLabel.setOnMouseClicked(handler);
    }
}
