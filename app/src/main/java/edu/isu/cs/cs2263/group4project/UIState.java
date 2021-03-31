package edu.isu.cs.cs2263.group4project;


import javafx.stage.Stage;

public interface UIState {
    void handle(Object event);
    void run();
}
