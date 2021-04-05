package edu.isu.cs.cs2263.group4project;

public class InitializeData {
    public static void main(String[] args) {
        Settings settings = new Settings();
        settings.initializeSettings();

        Admin admin = new Admin();
        admin.initializeAdmin();
    }
}
