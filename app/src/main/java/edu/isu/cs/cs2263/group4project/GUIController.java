package edu.isu.cs.cs2263.group4project;

public class GUIController {
    private static UIState state;
    GUIController(){
    }

    public static UIState getState() {
        return state;
    }

    public static void setState(UIState state) {
        GUIController.state = state;
    }
}
