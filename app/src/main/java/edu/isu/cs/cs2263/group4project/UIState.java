package edu.isu.cs.cs2263.group4project;


import javafx.event.EventHandler;
import javafx.stage.Stage;

public interface UIState {
    void handle(EventHandler event);
    void run();
}
