module group4project.app.main {
    requires javafx.controls;
    requires com.google.common;
    requires com.google.gson;
    exports edu.isu.cs.cs2263.group4project;
    opens edu.isu.cs.cs2263.group4project to com.google.gson;
}