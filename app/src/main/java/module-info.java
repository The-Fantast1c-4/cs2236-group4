module group4project.app.main {
    requires javafx.controls;
    requires com.google.common;
    requires com.google.gson;
    requires java.base;
    exports edu.isu.cs.cs2263.group4project;
    //opens java.time to com.google.gson;
    opens edu.isu.cs.cs2263.group4project to com.google.gson;
}