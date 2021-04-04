package edu.isu.cs.cs2263.group4project;

import com.google.common.collect.ForwardingTable;
import com.google.common.collect.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class TaskPageState implements UIState{
    private Stage stage;
    private List list;
    private String sectionName = "Default Section";

    public TaskPageState(Stage stage,String list) {
        this.list = App.getUser().getLists().getList(list);
        this.stage = stage;
    }

    public void handle(EventHandler event) {

    }

    public void run() {
        testState(stage);
    }
    public void testState(Stage stage){
        stage.setTitle(list.getName());
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
        tasks.getItems().addAll(App.getUser().getLists().getList(list.getName()).getSection(sectionName).getTasks());



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
        Button archiveList  = new Button();
        if(!list.isArchived()){
            archiveList.setText("Archive List");
        }else{archiveList.setText("UnArchive List");}
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
        //fill info
        for(Task userTask : App.getUser().getLists().getList(0).getSection(0).getTasks()){
            tasks.getItems().add(userTask);
        }
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

        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getSource()==back){
                    App.setState(new HomePageState(stage));
                }
                if(event.getSource()==logOut){
                    App.setUser(null);
                    App.setState(new LoginState(stage));
                }
                if(event.getSource()== makeTask){
                    Stage newTask = new Stage();
                    newTask.setTitle("New Task");
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


                    newTask.setX(stage.getX() + (stage.getWidth() / 2) - 200);
                    newTask.setY(stage.getY() + (stage.getHeight() / 2) - 150);
                    newTask.setWidth(400);
                    newTask.setHeight(300);

                    newTask.setScene(new Scene(main));
                    newTask.show();

                    //style
                    main.setStyle("-fx-background-color: #f2edd7");
                    cancel.setStyle("-fx-background-color: #e48257");
                    createTask.setStyle("-fx-background-color: #e48257");

                    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(event.getSource()==cancel){
                                newTask.close();
                            }
                            if(event.getSource()==createTask){
                                String title = titleText.getText();
                                String description = descriptionText.getText();
                                int priority = priorityList.getValue();
                                LocalDate dueDate = dueDatePicker.getValue();

                                ZoneId defaultZoneID = ZoneId.systemDefault();
                                Date date = Date.from(dueDate.atStartOfDay(defaultZoneID).toInstant());


                                Task tempTask = new Task(title,priority,description, date);
                                list.getSection(sectionName).addTask(tempTask);
                                IOManager.saveUser(App.getUser());
                                tasks.getItems().add(tempTask);
                                newTask.close();

                            }
                        }
                    };
                    cancel.setOnMouseClicked(handler1);
                    createTask.setOnMouseClicked(handler1);

                }
                if(event.getSource()==archiveList){
                    if(archiveList.getText().equals("Archive List")) {
                        list.archive();
                        archiveList.setText("UnArchive List");
                        IOManager.saveUser(App.getUser());
                    }
                    else if(archiveList.getText().equals("UnArchive List")){
                        list.unArchive();
                        archiveList.setText("Archive List");
                        IOManager.saveUser(App.getUser());
                    }
                }
            }
        };
        back.setOnMouseClicked(handler);
        logOut.setOnMouseClicked(handler);
        archiveList.setOnMouseClicked(handler);
        makeTask.setOnMouseClicked(handler);
    }


}
