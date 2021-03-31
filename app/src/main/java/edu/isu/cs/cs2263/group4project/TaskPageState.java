package edu.isu.cs.cs2263.group4project;

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

    }


}
