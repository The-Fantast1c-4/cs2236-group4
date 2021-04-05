package edu.isu.cs.cs2263.group4project;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

// This object holds all of the settings for the program

public class Settings {
    // Fields
    private String userDirectory;           // This defines where the user profiles (UserInfo) is housed
    private boolean logSystemInfo;          // This tells the program whether or not to log system information
    private String logLocation;             // This tells the program where to log system information
    private int itemsShown;                 // This defines how many items to show for each search
    private String userDataLocation;        // This tells the program where to store user's data (like Lists)

    // No args constructor
    public Settings(){

    }

    // Initializes the settings to factory conditions. Necessary when program is started on a new machine
    public void initializeSettings(){
        logSystemInfo = false;
        userDirectory = "./config/users.json";
        userDataLocation = "./data/";
        logLocation = "";
        itemsShown = 10;

        IOManager.writeSettings(this);      // Stores the data
    }

    // Getters and setters
    public String getUserDataDirectory(){
        return userDataLocation;
    }

    public void setUserDataLocation(String targetLocation){
        // When the data location is moved, we must also migrate all existing files
        // First, we get a list of all the users to compare to the existing files
        ArrayList<UserInfo> infos = IOManager.loadUserMacro();
        ArrayList<String> usernames = new ArrayList<>();
        for (UserInfo user : infos) {
            usernames.add(user.getUsername() + ".json");
        }

        // This makes sure that only user files are migrated, not any other files
        File currentLocation = Paths.get(userDataLocation).toFile();
        String[] pathNames = currentLocation.list();
        for (String pathName : pathNames) {
            // Need to make sure that we only move user files and not other files in the directory
            if (usernames.contains(pathName)) {
                try {
                    // Migrate the file
                    Files.move(Paths.get(userDataLocation + pathName), Paths.get(targetLocation + pathName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Now we can finally set the new location in Settings and save
        userDataLocation = targetLocation;
        IOManager.writeSettings(this);
    }

    public String getUserDirectory(){
        return userDirectory;
    }


    public void setUserDirectory(String value){
        // Again, we need to migrate this file when its specified location is changed
        try {
            Files.move(Paths.get(userDirectory), Paths.get(value));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Write setting and auto-save
        userDirectory = value;
        IOManager.writeSettings(this);
    }

    public boolean isLogSystemInfo(){
        return logSystemInfo;
    }

    public void setLogSystemInfo(boolean value){
        // Auto-saves this change
        logSystemInfo = value;
        IOManager.writeSettings(this);
    }

    public String getLogLocation(){
        return logLocation;
    }

    public void setLogLocation(String value){
        // Auto-saves this change
        logLocation = value;
        IOManager.writeSettings(this);
    }

    public int getItemsShown(){
        return itemsShown;
    }

    public void setItemsShown(int value){
        // Auto-saves this change
        itemsShown = value;
        IOManager.writeSettings(this);
    }

    public void saveSettings(){
        //save the users settings to ./config/config.json
        IOManager.writeSettings(this);
    }
}
