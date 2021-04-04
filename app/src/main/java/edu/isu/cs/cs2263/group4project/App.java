/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.isu.cs.cs2263.group4project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class App extends Application {
    private static UIState state;
    private static StandardUser user;
    private static Admin admin;
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Here is where we put the call to our first state subclass
        setState(new LoginState(primaryStage));
        state.run();


    }

    public void testState(Stage stage){
        Scene scene = new Scene(new VBox(10), 595, 200);
        stage.setTitle("Test Layout");
        stage.setScene(scene);
        stage.show();
    }
    public static void setState(UIState state){
        App.state = state;
        App.state.run();
    }

    public static UIState getState() {
        return state;
    }

    public static void setUser(StandardUser user) {
        App.user = user;
    }

    public static void setAdmin(Admin admin) {
        App.admin = admin;
    }

    public static StandardUser getUser() {
        return user;
    }

    public static Admin getAdmin() {
        return admin;
    }
}
