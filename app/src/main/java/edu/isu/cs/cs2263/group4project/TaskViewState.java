package edu.isu.cs.cs2263.group4project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TaskViewState implements UIState{

    private Stage stage;
    private List list;
    private Section section;
    private Task task;
    private String[] priorities = {"Low", "Medium", "High", "Highest"};

    //constructors
    public TaskViewState(Stage stage, String list, String subList, String section, String task){
        this.stage = stage;
        this.list = App.getUser().getLists().getList(list);
        this.section = App.getUser().getLists().getList(list).getSublist(subList).getSection(section);
        this.task = App.getUser().getLists().getList(list).getSublist(subList).getSection(section).getTask(task);
    }

    public TaskViewState(Stage stage, String list, String section, String task){
        this.stage = stage;
        this.list = App.getUser().getLists().getList(list);
        this.section = App.getUser().getLists().getList(list).getSection(section);
        this.task = App.getUser().getLists().getList(list).getSection(section).getTask(task);
    }


    public void handle(EventHandler event){

    }

    public void run(){ testState(stage);}

    public void testState(Stage stage){
        //set title of state
        stage.setTitle("Task View");
        //completed checkbox
        CheckBox completed = new CheckBox("Completed");
        completed.setSelected(task.isComplete());
        //buttons
        Button logOut = new Button("LogOut");
        Button back = new Button("Back");

        Button editTask = new Button("Edit Task");
        Button addSubtask = new Button("Add Subtask");
        //task value labels
        Label name = new Label(task.getName());
        Label dueDate = new Label("Due: " + task.getDueDate());
        Label overdue = new Label();
        //displays an OVERDUE label if the task is past its due date
        if (task.isOverDue()){
            overdue.setText("OVERDUE");
            overdue.setTextFill(Color.color(0.8, 0, 0));
        }
        Label priority = new Label("Priority: " + priorities[task.getPriority() - 1]);
        TextArea description = new TextArea(task.getDescription());
        //subtask and label containers
        ObservableList<SubTask> subtasksList = FXCollections.observableArrayList(task.getSubTasks());
        ListView<SubTask> subtasks = new ListView<>(subtasksList);
        ComboBox labels = new ComboBox();
        ObservableList labelList = FXCollections.observableArrayList(task.getLabels());
        labels.getItems().addAll(labelList);
        Button addLabel = new Button("+");
        //containers
        VBox main = new VBox();
        HBox topBar = new HBox();
        HBox topLeft = new HBox();
        HBox topRight = new HBox();
        HBox body = new HBox();
        VBox bottomLeft = new VBox();
        VBox bottomRight = new VBox();
        VBox leftSide = new VBox();
        VBox rightSide = new VBox();
        HBox descBox = new HBox();
        HBox propBox = new HBox();
        HBox labelBox = new HBox();
        HBox subtaskBox = new HBox();
        //build layout
        topLeft.getChildren().addAll(name, completed);
        topRight.getChildren().addAll(back, logOut);
        topBar.getChildren().addAll(topLeft, topRight);
        descBox.getChildren().addAll(description);
        propBox.getChildren().addAll(dueDate, overdue, priority);
        leftSide.getChildren().addAll(descBox, propBox, bottomLeft);
        labelBox.getChildren().addAll(labels, addLabel);
        subtaskBox.getChildren().addAll(subtasks);
        rightSide.getChildren().addAll(labelBox, subtaskBox, bottomRight);
        body.getChildren().addAll(leftSide, rightSide);
        bottomLeft.getChildren().addAll(editTask);
        bottomRight.getChildren().addAll(addSubtask);
        main.getChildren().addAll(topBar, body);
        //styling
        main.setAlignment(Pos.CENTER);
        topBar.setSpacing(500);
        topBar.setPadding(new Insets(10,0,0,120));
        topBar.setAlignment(Pos.TOP_LEFT);
        topLeft.setSpacing(20);
        topRight.setSpacing(20);
        topLeft.setAlignment(Pos.TOP_LEFT);
        topRight.setAlignment(Pos.TOP_RIGHT);
        body.setSpacing(50);
        body.setPadding(new Insets(10,0,0,0));
        body.setAlignment(Pos.CENTER);
        bottomLeft.setAlignment(Pos.BOTTOM_LEFT);
        bottomRight.setAlignment(Pos.BOTTOM_RIGHT);
        rightSide.setSpacing(5);
        leftSide.setSpacing(10);
        propBox.setAlignment(Pos.CENTER);
        propBox.setSpacing(20);
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
                    //returns to the list view state
                    if (section != null){
                        App.setState(new TaskPageState(stage, list.getName(), section.getName()));
                    }else{
                        App.setState(new TaskPageState(stage, list.getName()));
                    }

                }
                if(event.getSource() == logOut){
                    //logs out the user and returns to the login page
                    App.setUser(null);
                    App.setState(new LoginState(stage));
                }
                if(event.getSource() == addSubtask){
                    //creates a pop up to prompt the user to create a new subtask
                    Stage newSubTask = new Stage();
                    newSubTask.setTitle("New Subtask");
                    //create nodes
                    Label titleLabel = new Label("Title");
                    Label descriptionLabel = new Label("Description");
                    Label priorityLabel = new Label("Priority");
                    Label dueDateLabel = new Label("Due Date");
                    TextField titleText = new TextField();
                    TextField descriptionText = new TextField();
                    ComboBox<String> priorityList = new ComboBox<>();
                    priorityList.getItems().addAll("Low", "Medium", "High", "Highest");
                    priorityList.setValue("Low");
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
                                //closes the new subtask popup
                                newSubTask.close();
                            }
                            if(event.getSource()==createTask){
                                //creates a subtask with the given information and adds it to the current task
                                String title = titleText.getText();
                                String description = descriptionText.getText();
                                int priority = getPriorityInt(priorityList.getValue());
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
                    //displays a popup to prompt the user to change the info on the current task
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
                    ComboBox<String> priorityList = new ComboBox<>();
                    priorityList.getItems().addAll("Low", "Medium", "High", "Highest");
                    priorityList.setValue(priorities[task.getPriority() - 1]);
                    DatePicker dueDatePicker = new DatePicker();
                    Button saveEdit = new Button("Edit Task");
                    Button cancel = new Button("Cancel");
                    //create containers
                    GridPane main = new GridPane();

                    //fill containers
                    main.add(titleLabel,0,0);
                    main.add(descriptionLabel,0,1);
                    main.add(priorityLabel,0,2);
                    main.add(dueDateLabel,0,3);
                    main.add(saveEdit,0,4);

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
                    saveEdit.setStyle("-fx-background-color: #e48257");

                    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(event.getSource()==cancel){
                                //closes the edit task popup
                                editTask.close();
                            }
                            if(event.getSource()==saveEdit){
                                String newTitle = titleText.getText();
                                String newDescription = descriptionText.getText();
                                int newPriority = getPriorityInt(priorityList.getValue());
                                LocalDate newDate = dueDatePicker.getValue();


                                ZoneId defaultZoneID = ZoneId.systemDefault();
                                Date date;
                                if (newDate != null) {
                                    date = Date.from(newDate.atStartOfDay(defaultZoneID).toInstant());
                                }else{
                                    date = null;
                                }

                                task.setName(newTitle);
                                task.setDescription(newDescription);
                                if (date != null){
                                    task.setDueDate(date);
                                }
                                task.setPriority(newPriority);

                                IOManager.saveUser(App.getUser());
                                App.setState(new TaskViewState(stage, list.getName(), section.getName(), task.getName()));
                                editTask.close();
                            }
                        }
                    };
                    cancel.setOnMouseClicked(handler1);
                    saveEdit.setOnMouseClicked(handler1);

                }
                if(event.getSource() == addLabel){
                    //creates a popup to prompt the user for a label to add to the current task
                    Stage addLabel = new Stage();
                    addLabel.setTitle("Add Label");
                    //input fields
                    TextField newLabel= new TextField();
                    //labels
                    newLabel.setPromptText("New Label");
                    //buttons
                    Button submit = new Button("Submit");
                    Button cancel = new Button("Cancel");

                    //containers
                    HBox buttonsBox = new HBox();
                    VBox main = new VBox();

                    buttonsBox.getChildren().addAll(submit,cancel);
                    main.getChildren().addAll(newLabel,buttonsBox);

                    //styling
                    addLabel.setX(stage.getX() + (stage.getWidth() / 2) - 200);
                    addLabel.setY(stage.getY() + (stage.getHeight() / 2) - 150);
                    addLabel.setWidth(400);
                    addLabel.setHeight(300);

                    addLabel.setScene(new Scene(main));
                    addLabel.show();

                    main.setStyle("-fx-background-color: #f2edd7");
                    submit.setStyle("-fx-background-color: #e48257");
                    cancel.setStyle("-fx-background-color: #e48257");

                    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(event.getSource()==cancel){
                                //closes the create label popup
                                addLabel.close();
                            }
                            if(event.getSource()==submit||(name.getText()!="")){
                                //adds the given label to the current task
                                task.addLabel(newLabel.getText());
                                IOManager.saveUser(App.getUser());
                                App.setState(new TaskViewState(stage,list.getName(), section.getName(), task.getName()));
                                addLabel.close();
                            }

                        }
                    };
                    cancel.setOnMouseClicked(handler1);
                    submit.setOnMouseClicked(handler1);
                }
                if (event.getSource() == completed){
                    //marks the current task as complete
                    if (!task.isComplete()){
                        task.markComplete();
                    }
                    IOManager.saveUser(App.getUser());
                }
                if(event.getSource()==subtasks){
                    //switches to the subtask view state for the chosen subtask
                    Task subTask = subtasks.getSelectionModel().getSelectedItem();
                    App.setState(new SubtaskState(stage, list.getName(), section.getName(), task.getName(), subTask.getName()));
                }
            }
        };

        back.setOnMouseClicked(handler);
        logOut.setOnMouseClicked(handler);
        addSubtask.setOnMouseClicked(handler);
        editTask.setOnMouseClicked(handler);
        addLabel.setOnMouseClicked(handler);
        completed.setOnMouseClicked(handler);
        subtasks.setOnMouseClicked(handler);
    }

    //converts priority string to an integer
    int getPriorityInt(String value){
        return switch (value) {
            case "Low" -> 1;
            case "Medium" -> 2;
            case "High" -> 3;
            case "Highest" -> 4;
            default -> 1;
        };
    }
}
