package edu.isu.cs.cs2263.group4project;

import com.google.common.collect.ForwardingTable;
import com.google.common.collect.Table;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
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

    public TaskPageState(Stage stage,String list,String section){
        this.list = App.getUser().getLists().getList(list);
        this.stage = stage;
        this.sectionName = section;
    }


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
        Button searchButton = new Button("Search");
        TextField searchBar = new TextField();
        Button logOut = new Button("LogOut");
        Button back = new Button("Back");
            //buttons for button bar
        Button delete = new Button("Delete");
        Button moveList = new Button("Move List");
        Button moveSection = new Button("Move Section");
        Button duplicate = new Button("Duplicate");
        Button viewTask = new Button("View Task");

        TableView<Task> tasks = new TableView();
        TableColumn<Task, String> taskColumn = new TableColumn<>("Task");
        TableColumn<Task, Date> dateColumn = new TableColumn<>("Due Date");
        TableColumn<Task, String> completeColumn = new TableColumn<>("Completed?");
        TableColumn<Task,String> overDueColumn=new TableColumn<>("Status");

        ArrayList<Label> sections = new ArrayList<>();
        for(Section sect: App.getUser().getLists().getList(list.getName()).getSections()){
            sections.add(new Label(sect.getName()));
        }
        for(Label label : sections){
            if (label.getText().equals(sectionName)){
                label.setStyle("-fx-text-fill: red");

            }
        }

        tasks.getColumns().addAll(taskColumn,dateColumn,completeColumn,overDueColumn);
        //Showing only incomplete tasks, complete tasks can be seen only when sorted by completed.
        ArrayList<Task> temp=list.getSection(sectionName).getTasks();
        for (Task task: temp){
            if (!task.isComplete()) {
                tasks.getItems().addAll(task);
            }
        }

        taskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        completeColumn.setCellValueFactory(cellData -> {
            boolean complete = cellData.getValue().isComplete();
            String completeAsString;
            if(complete == true)
            {
                completeAsString= "Yes";
            }
            else
            {
                completeAsString = "No";
            }

            return new ReadOnlyStringWrapper(completeAsString);
        });
        overDueColumn.setCellValueFactory(cellData -> {
            boolean overdue = cellData.getValue().isOverDue();
            String overdueAsString;
            if(overdue == true)
            {
                overdueAsString= "Overdue";
            }
            else
            {
                overdueAsString= "On time";

            }

            return new ReadOnlyStringWrapper(overdueAsString);
        });


        ComboBox comments = new ComboBox();
        ObservableList commentList = FXCollections.observableArrayList(list.getComments());
        comments.getItems().addAll(commentList);
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
        Button deleteSection = new Button("Delete Section");
        Button deleteList = new Button("Delete List");

        Label subListLabel = new Label("Sublists");

        ObservableList<SubList> subListsList = FXCollections.observableArrayList(list.getSubLists());
        ListView<SubList> subLists = new ListView<SubList>(subListsList);


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
        bottomButtonBar.getChildren().addAll(makeTask,addSection,archiveList,deleteSection,deleteList);
        sectionLabels.getChildren().addAll(sections);
        taskBox.getChildren().addAll(sectionLabels,tasks);
        topButtonBar.getChildren().addAll(delete,moveList,moveSection,duplicate,viewTask);
        commentBox.getChildren().addAll(comments,addComment);
        rightSide.getChildren().addAll(commentBox,sorting,labelSort,prioritySort,dueDateSort,completedSort,subListLabel,subLists,addSublist);
        leftSide.getChildren().addAll(topButtonBar,taskBox,bottomButtonBar);
        body.getChildren().addAll(leftSide,rightSide);
        topBar.getChildren().addAll(searchButton,searchBar,back,logOut);
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
        tasks.setMinWidth(500);
        taskBox.setSpacing(15);
        subLists.setMaxSize(100000,200);
        topButtonBar.setSpacing(2);
        bottomButtonBar.setSpacing(10);
        //fill info

        //style nodes
        back.setStyle("-fx-background-color: #e48257");
        logOut.setStyle("-fx-background-color: #e48257");
        searchButton.setStyle("-fx-background-color: #e48257");

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
        deleteSection.setStyle("-fx-background-color: #e48257");
        deleteList.setStyle("-fx-background-color: #e48257");


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
                                int priority = getPriorityInt(priorityList.getValue());
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
                if(event.getSource()==addSection){
                    Stage newSection = new Stage();
                    newSection.setTitle("New Section");
                    //create nodes
                    TextField name = new TextField();
                    name.setPromptText("Name");
                    Button save = new Button("Save");
                    Button cancel = new Button("Cancel");

                    //create containers
                    HBox buttonBox = new HBox();
                    VBox main = new VBox();

                    //fill containers
                    buttonBox.getChildren().addAll(save,cancel);
                    main.getChildren().addAll(name,buttonBox);


                    newSection.setX(stage.getX() + (stage.getWidth() / 2) - 200);
                    newSection.setY(stage.getY() + (stage.getHeight() / 2) - 150);
                    newSection.setWidth(400);
                    newSection.setHeight(300);

                    newSection.setScene(new Scene(main));
                    newSection.show();

                    main.setStyle("-fx-background-color: #f2edd7");
                    save.setStyle("-fx-background-color: #e48257");
                    cancel.setStyle("-fx-background-color: #e48257");

                    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(event.getSource()==cancel){
                                newSection.close();
                            }
                            if(event.getSource()==save||(name.getText()!="")){
                                String sName = name.getText();
                                App.getUser().getLists().getList(list.getName()).addSection(sName);
                                IOManager.saveUser(App.getUser());
                                sectionLabels.getChildren().add(new Label(sName));
                                newSection.close();
                                App.setState(new TaskPageState(stage, list.getName(),sName));
                            }

                        }
                    };
                    cancel.setOnMouseClicked(handler1);
                    save.setOnMouseClicked(handler1);
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
                for(Label lab : sections){
                    if(event.getSource()==lab){
                        sectionName = lab.getText();
                        for(Label label : sections){
                            label.setStyle("-fx-text-fill: black");
                        }
                        lab.setStyle("-fx-text-fill: red");
                        tasks.getItems().clear();
                        //ArrayList<Task> temp=list.getSection(sectionName).getTasks();
                        for (Task task: list.getSection(sectionName).getTasks()){
                           // ArrayList<Task> temp=list.getSection(sectionName).getTasks();
                            if (!task.isComplete()) {
                                tasks.getItems().addAll(task);
                            }
                        }

                    }
                }
                if (event.getSource()==searchButton){
                    String searchTerm = searchBar.getText();
                    SearchingVisitor v = new SearchingVisitor(searchTerm);
                    list.accept(v);
                    ArrayList<Task> matches = v.getTaskMatches();
                    tasks.getItems().clear();
                    tasks.getItems().addAll(matches);
                    for(Label label : sections){
                        label.setStyle("-fx-text-fill: black");
                    }
                }

                if (event.getSource()==delete){
                    Stage delTask = new Stage();
                    delTask.setTitle("Delete Task");
                    Button del=new Button("Delete");
                    Button cancel=new Button("Cancel");
                    Label label=new Label("Are you sure to delete?");
                    label.setStyle("-fx-text-fill: Red");
                    GridPane gridPane1=new GridPane();
                    gridPane1.add(label,2,1);
                    gridPane1.add(del,1,4);
                    gridPane1.add(cancel,3,4);
                    gridPane1.setStyle("-fx-background-color: #f2edd7");
                    del.setStyle("-fx-background-color: #e48257");
                    cancel.setStyle("-fx-background-color: #e48257");
                    gridPane1.setVgap(10);
                    delTask.setScene(new Scene(gridPane1));
                    delTask.show();
                    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            if (event.getSource() == del) {
                                Task tempTask = tasks.getSelectionModel().getSelectedItem();
                                App.getUser().getLists().getList(list.getName()).getSection(sectionName).deleteTask(tempTask);
                                App.setState(new TaskPageState(stage, list.getName(), sectionName));
                                IOManager.saveUser(App.getUser());
                                delTask.close();

                            } else{
                                if (event.getSource()==cancel){
                                    delTask.close();
                                }

                            }
                        }

                    };
                    del.setOnMouseClicked(handler1);
                    cancel.setOnMouseClicked(handler1);
                }
                if (event.getSource()==deleteSection){
                    Stage delSection = new Stage();
                    delSection.setTitle("Delete Section");
                    Button del=new Button("Delete");
                    Button cancel=new Button("Cancel");
                    Label label=new Label("Are you sure to delete?");
                    label.setStyle("-fx-text-fill: Red");
                    GridPane gridPane1=new GridPane();
                    gridPane1.add(label,2,1);
                    gridPane1.add(del,1,4);
                    gridPane1.add(cancel,3,4);
                    gridPane1.setStyle("-fx-background-color: #f2edd7");
                    del.setStyle("-fx-background-color: #e48257");
                    cancel.setStyle("-fx-background-color: #e48257");
                    gridPane1.setVgap(5);
                    delSection.setScene(new Scene(gridPane1));
                    delSection.show();
                    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            if (event.getSource() == del) {
                                list.deleteSection(list.getSection(sectionName));
                                IOManager.saveUser(App.getUser());
                                App.setState(new TaskPageState(stage,list.getName()));
                                delSection.close();
                            } else{
                                if (event.getSource()==cancel){
                                    delSection.close();
                                }

                            }
                        }

                    };
                    del.setOnMouseClicked(handler1);
                    cancel.setOnMouseClicked(handler1);
                }
                if (event.getSource()==deleteList){
                    Stage delList = new Stage();
                    delList.setTitle("Delete List");
                    Button del=new Button("Delete");
                    Button cancel=new Button("Cancel");
                    Label label=new Label("Are you sure to delete?");
                    label.setStyle("-fx-text-fill: Red");
                    GridPane gridPane1=new GridPane();
                    gridPane1.add(label,2,1);
                    gridPane1.add(del,1,4);
                    gridPane1.add(cancel,3,4);
                    gridPane1.setVgap(5);
                    gridPane1.setStyle("-fx-background-color: #f2edd7");
                    del.setStyle("-fx-background-color: #e48257");
                    cancel.setStyle("-fx-background-color: #e48257");
                    delList.setScene(new Scene(gridPane1));
                    delList.show();
                    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            if (event.getSource() == del) {
                                App.getUser().getLists().deleteList(list);
                                IOManager.saveUser(App.getUser());
                                App.setState(new HomePageState(stage));
                                delList.close();
                            } else{
                                if (event.getSource()==cancel){
                                    delList.close();
                                }

                            }
                        }

                    };
                    del.setOnMouseClicked(handler1);
                    cancel.setOnMouseClicked(handler1);

                }

                if (event.getSource()==duplicate){
                    Task tempTask = tasks.getSelectionModel().getSelectedItem();
                    list.getSection(sectionName).cloneTask(tempTask);

                    tasks.getItems().add(tempTask);
                }
                if(event.getSource()==addSublist) {
                    Stage newList = new Stage();
                    newList.setTitle("New Sub List");
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
                                list.addSubList(sName,sDescription);
                                IOManager.saveUser(App.getUser());
                                subLists.getItems().add(list.getSublist(sName));
                                newList.close();
                            }

                        }
                    };
                    cancel.setOnMouseClicked(handler1);
                    save.setOnMouseClicked(handler1);
                }
                if(event.getSource()==subLists){ }
                if(event.getSource()==labelSort){
                    prioritySort.setSelected(false);
                    completedSort.setSelected(false);
                    dueDateSort.setSelected(false);
                    ArrayList<Task> sortedTasks = Filter.sortBy(list.getSection(sectionName), "label");
                    tasks.getItems().clear();
                    tasks.getItems().addAll(sortedTasks);
                }
                if(event.getSource()==prioritySort){
                    labelSort.setSelected(false);
                    completedSort.setSelected(false);
                    dueDateSort.setSelected(false);
                    ArrayList<Task> sortedTasks = Filter.sortBy(list.getSection(sectionName), "priority");
                    tasks.getItems().clear();
                    tasks.getItems().addAll(sortedTasks);
                }
                if(event.getSource()==completedSort){
                    prioritySort.setSelected(false);
                    labelSort.setSelected(false);
                    dueDateSort.setSelected(false);
                    ArrayList<Task> sortedTasks = list.getSection(sectionName).getCompletedTasks();
                    tasks.getItems().clear();
                    tasks.getItems().addAll(sortedTasks);
                }
                if(event.getSource()==dueDateSort){
                    prioritySort.setSelected(false);
                    completedSort.setSelected(false);
                    labelSort.setSelected(false);
                    ArrayList<Task> sortedTasks = Filter.sortBy(list.getSection(sectionName), "date");
                    tasks.getItems().clear();
                    tasks.getItems().addAll(sortedTasks);
                }
                if(event.getSource()==viewTask){
                    Task tempTask = tasks.getSelectionModel().getSelectedItem();
                    App.setState(new TaskViewState(stage,list.getName(),sectionName, tempTask.getName()));
                }
                if(event.getSource()==moveList){

                    Stage moveList = new Stage();
                    moveList.setTitle("Move List");
                    //create nodes
                    TextField name = new TextField();
                    name.setPromptText("Name");
                    Button save = new Button("Save");
                    Button cancel = new Button("Cancel");

                    //create containers
                    HBox buttonBox = new HBox();
                    VBox main = new VBox();

                    //fill containers
                    buttonBox.getChildren().addAll(save,cancel);
                    main.getChildren().addAll(name,buttonBox);


                    moveList.setX(stage.getX() + (stage.getWidth() / 2) - 200);
                    moveList.setY(stage.getY() + (stage.getHeight() / 2) - 150);
                    moveList.setWidth(400);
                    moveList.setHeight(300);

                    moveList.setScene(new Scene(main));
                    moveList.show();

                    main.setStyle("-fx-background-color: #f2edd7");
                    save.setStyle("-fx-background-color: #e48257");
                    cancel.setStyle("-fx-background-color: #e48257");

                    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(event.getSource()==cancel){
                                moveList.close();
                            }
                            if(event.getSource()==save||(name.getText()!="")){
                                Task tempTask = tasks.getSelectionModel().getSelectedItem();
                                String sName = name.getText();
                                if(App.getUser().getLists().getList(sName)==null){
                                    App.getUser().getLists().makeList(sName,"");
                                    App.getUser().getLists().getList(sName).getSection(0).addTask(tempTask);
                                }else{App.getUser().getLists().getList(sName).getSection(0).addTask(tempTask); }
                                list.getSection(sectionName).deleteTask(tempTask);
                                IOManager.saveUser(App.getUser());
                                App.setState(new TaskPageState(stage,list.getName(),sectionName));
                                moveList.close();
                            }

                        }
                    };
                    cancel.setOnMouseClicked(handler1);
                    save.setOnMouseClicked(handler1);
                }
                if(event.getSource()==moveSection){

                    Stage moveSection = new Stage();
                    moveSection.setTitle("Move Section");
                    //create nodes
                    TextField name = new TextField();
                    name.setPromptText("New Section");
                    Button save = new Button("Save");
                    Button cancel = new Button("Cancel");

                    //create containers
                    HBox buttonBox = new HBox();
                    VBox main = new VBox();

                    //fill containers
                    buttonBox.getChildren().addAll(save,cancel);
                    main.getChildren().addAll(name,buttonBox);


                    moveSection.setX(stage.getX() + (stage.getWidth() / 2) - 200);
                    moveSection.setY(stage.getY() + (stage.getHeight() / 2) - 150);
                    moveSection.setWidth(400);
                    moveSection.setHeight(300);

                    moveSection.setScene(new Scene(main));
                    moveSection.show();

                    main.setStyle("-fx-background-color: #f2edd7");
                    save.setStyle("-fx-background-color: #e48257");
                    cancel.setStyle("-fx-background-color: #e48257");

                    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(event.getSource()==cancel){
                                moveSection.close();
                            }
                            if(event.getSource()==save||(name.getText()!="")){
                                Task tempTask = tasks.getSelectionModel().getSelectedItem();
                                String sName = name.getText();
                                if(list.getSection(sName)==null){
                                    list.addSection(sName);
                                    list.getSection(sName).addTask(tempTask);
                                }else{list.getSection(sName).addTask(tempTask); }
                                list.getSection(sectionName).deleteTask(tempTask);
                                IOManager.saveUser(App.getUser());
                                App.setState(new TaskPageState(stage,list.getName(),sectionName));
                                moveSection.close();
                            }

                        }
                    };
                    cancel.setOnMouseClicked(handler1);
                    save.setOnMouseClicked(handler1);
                }
                if(event.getSource()==addComment){

                    Stage addComment = new Stage();
                    addComment.setTitle("Add Comment");
                    //create nodes
                    TextField name = new TextField();
                    name.setPromptText("New Comment");
                    Button save = new Button("Save");
                    Button cancel = new Button("Cancel");

                    //create containers
                    HBox buttonBox = new HBox();
                    VBox main = new VBox();

                    //fill containers
                    buttonBox.getChildren().addAll(save,cancel);
                    main.getChildren().addAll(name,buttonBox);


                    addComment.setX(stage.getX() + (stage.getWidth() / 2) - 200);
                    addComment.setY(stage.getY() + (stage.getHeight() / 2) - 150);
                    addComment.setWidth(400);
                    addComment.setHeight(300);

                    addComment.setScene(new Scene(main));
                    addComment.show();

                    main.setStyle("-fx-background-color: #f2edd7");
                    save.setStyle("-fx-background-color: #e48257");
                    cancel.setStyle("-fx-background-color: #e48257");

                    EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(event.getSource()==cancel){
                                addComment.close();
                            }
                            if(event.getSource()==save||(name.getText()!="")){
                                list.addComment(new Comment(name.getText()));
                                IOManager.saveUser(App.getUser());
                                App.setState(new TaskPageState(stage,list.getName(),sectionName));
                            }

                        }
                    };
                    cancel.setOnMouseClicked(handler1);
                    save.setOnMouseClicked(handler1);
                }
                if(event.getSource()==subLists){
                    List subList = subLists.getSelectionModel().getSelectedItem();
                    App.setState(new SubListState(stage,list.getName(),subList.getName()));
                }
            }
        };
        back.setOnMouseClicked(handler);
        logOut.setOnMouseClicked(handler);
        archiveList.setOnMouseClicked(handler);
        makeTask.setOnMouseClicked(handler);
        addSection.setOnMouseClicked(handler);
        for (Label label : sections){
            label.setOnMouseClicked(handler);
        }
        searchButton.setOnMouseClicked(handler);
        delete.setOnMouseClicked(handler);
        deleteSection.setOnMouseClicked(handler);
        deleteList.setOnMouseClicked(handler);
        duplicate.setOnMouseClicked(handler);
        addSublist.setOnMouseClicked(handler);
        subLists.setOnMouseClicked(handler);
        labelSort.setOnMouseClicked(handler);
        prioritySort.setOnMouseClicked(handler);
        completedSort.setOnMouseClicked(handler);
        dueDateSort.setOnMouseClicked(handler);
        viewTask.setOnMouseClicked(handler);
        moveList.setOnMouseClicked(handler);
        moveSection.setOnMouseClicked(handler);
        addComment.setOnMouseClicked(handler);
        subLists.setOnMouseClicked(handler);
    }

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
