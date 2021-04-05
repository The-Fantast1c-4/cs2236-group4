package edu.isu.cs.cs2263.group4project;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChangeSettingsState implements UIState {
    private Stage stage;
    public ChangeSettingsState(Stage stage) {
        this.stage=stage;
    }
    Settings settings = IOManager.loadSettings();

    String userDirectory = settings.getUserDirectory();
    boolean logSystemInfo = settings.isLogSystemInfo();
    String logLocation = settings.getLogLocation();
    int itemsShown = settings.getItemsShown();
    String userDataLocation = settings.getUserDataDirectory();


    public void handle(EventHandler event) {
    }

    public void run() {
        this.testState(stage);
    }



    public void testState(Stage stage) {
        //create nodes
        stage.setTitle("Change Settings");
        FileChooser browse = new FileChooser();
        browse.setTitle("Choose Log location");
        Button browseBtn=new Button("Choose Log location");
        Label sULabel = new Label("Store User");
        Label itemLabel = new Label("Items per page");
        ChoiceBox itemCount = new ChoiceBox();
        for (int i = 1; i<31; i++){
            itemCount.getItems().add(i);
        }


        Label label2=new Label("Changes Saved     ");
        Button backBtn = new Button("Back to Admin Page");
        Button saveBtn = new Button(" Save Changes");
        Button browseUserDataBtn=new Button("Browse User Data Location");

        CheckBox cb = new CheckBox("Log System Info?");
        cb.setAllowIndeterminate(false);

        Button browseDirectory=new Button("Choose User Directory Location");
        GridPane gridPane=new GridPane();
        gridPane.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(50);
        gridPane.setVgap(50);


        gridPane.add(browseDirectory,0,1);
        gridPane.add(cb,0,3);
        gridPane.add(browseBtn,0,5);
        gridPane.add(browseUserDataBtn,4,1);
        gridPane.add(backBtn,4,7);
        gridPane.add(itemCount,5,5);
        gridPane.add(itemLabel,4,5);
        gridPane.add(saveBtn,2,7);


        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getSource()==browseDirectory) {
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    directoryChooser.setTitle("Choose User Directory");
                    File selectedDirectory = directoryChooser.showDialog(stage);
                    String absolutePath = selectedDirectory.getAbsolutePath();

                    // These next lines of code convert the absolute path into a relative path

                    Path currentAbsolutePath = Paths.get("").toAbsolutePath();

                    userDirectory = "./" + currentAbsolutePath.relativize(Paths.get(absolutePath)).toString() + "/users.json";

                    browseDirectory.setText(userDirectory);
                    //user selected directory will be stored here.
                }
                if (event.getSource()==cb){

                    if (cb.isSelected()){
                        logSystemInfo=true;
                    } else {
                        logSystemInfo=false;
                    }
                }
                //yes or not will be in logSystemInfo boolean

                if (event.getSource()==browseBtn){
                    File selected=browse.showOpenDialog(stage);

                    String absolutePath = selected.getAbsolutePath();

                    Path currentAbsolutePath = Paths.get("").toAbsolutePath();

                    logLocation = "./" + currentAbsolutePath.relativize(Paths.get(absolutePath)).toString() + "/";

                    browseBtn.setText(logLocation);
                }
                // log location saved in logLocation

                if (event.getSource()==browseUserDataBtn){
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    directoryChooser.setTitle("Choose User Directory");
                    File selectedDirectory = directoryChooser.showDialog(stage);
                    String absolutePath = selectedDirectory.getAbsolutePath();

                    Path currentAbsolutePath = Paths.get("").toAbsolutePath();

                    userDataLocation = "./" + currentAbsolutePath.relativize(Paths.get(absolutePath)).toString() + "/";
                    browseUserDataBtn.setText(userDataLocation);
                }
                //User data location : will be in userDataLocation

                if(event.getSource()==itemCount){
                    itemsShown =(Integer)itemCount.getSelectionModel().getSelectedItem();
                    System.out.println(itemsShown);
                }
                //no of items to be shown : in itemsShown
                EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        //The settings will be changed here:-----------
                        settings.setUserDirectory(userDirectory);       // All of these methods auto-save
                        settings.setLogSystemInfo(logSystemInfo);
                        settings.setLogLocation(logLocation);
                        settings.setItemsShown(itemsShown);
                        settings.setUserDataLocation(userDataLocation);

                        gridPane.add(label2,2,3);


                    }
                };
                saveBtn.setOnMouseClicked(handler1);

                if (event.getSource()==backBtn){
                    App.setState(new AdminState(stage));
                }

            }
        };
        browseDirectory.setOnMouseClicked(handler);
        cb.setOnMouseClicked(handler);
        browseBtn.setOnMouseClicked(handler);
        browseUserDataBtn.setOnMouseClicked(handler);
        itemCount.setOnMouseClicked(handler);
        backBtn.setOnMouseClicked(handler);



        //style nodes
        browseBtn.setStyle("-fx-background-color: #ADD8E6;");
        browseDirectory.setStyle("-fx-background-color: #ADD8E6;");
        cb.setStyle("-fx-background-color: #ADD8E6;");
        browseUserDataBtn.setStyle("-fx-background-color: #ADD8E6;");
        backBtn.setStyle("-fx-background-color: #e48257;");
        itemCount.setStyle("-fx-background-color: #ADD8E6;");
        gridPane.setStyle("-fx-background-color: #f2edd7;");
        saveBtn.setStyle("-fx-background-color: #e48257;");
        label2.setStyle("-fx-text-fill: green;");


        //set scene
        Scene scene = new Scene(gridPane,1000,600);
        stage.setScene(scene);
        stage.show();
    }


}
