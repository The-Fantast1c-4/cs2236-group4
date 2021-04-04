package edu.isu.cs.cs2263.group4project;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Settings {
    private String userDirectory;
    private boolean logSystemInfo;
    private String logLocation;
    private int itemsShown;
    private String userDataLocation;

    public Settings(){

    }

    public void loadSettings(){
        Settings tmpSet = IOManager.loadSettings();
        if (tmpSet == null){
            initializeSettings();
            return;
        }
        userDirectory = tmpSet.getUserDirectory();
        logSystemInfo = tmpSet.isLogSystemInfo();
        logLocation = tmpSet.getLogLocation();
        itemsShown = tmpSet.getItemsShown();
        userDataLocation = tmpSet.getUserDataDirectory();
    }

    public void initializeSettings(){
        logSystemInfo = false;
        userDirectory = "./config/users.json";
        userDataLocation = "./data/";
        logLocation = "";
        itemsShown = 10;

        IOManager.writeSettings(this);
    }

    public String getUserDataDirectory(){
        return userDataLocation;
    }

    public void setUserDataLocation(String targetLocation){
        ArrayList<UserInfo> infos = IOManager.loadUserMacro();
        ArrayList<String> usernames = new ArrayList<>();
        for (UserInfo user : infos) {
            usernames.add(user.getUsername() + ".json");
        }


        File currentLocation = Paths.get(userDataLocation).toFile();
        String[] pathNames = currentLocation.list();
        for (String pathName : pathNames) {
            // Need to make sure that we only move user files and not other files in the directory
            if (usernames.contains(pathName)) {
                try {
                    Files.move(Paths.get(userDataLocation + pathName), Paths.get(targetLocation + pathName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }




        userDataLocation = targetLocation;
        IOManager.writeSettings(this);
    }

    public String getUserDirectory(){
        return userDirectory;
    }

    public void setUserDirectory(String value){
        try {
            Files.move(Paths.get(userDirectory), Paths.get(value));
        } catch (Exception e) {
            e.printStackTrace();
        }

        userDirectory = value;
        IOManager.writeSettings(this);
    }

    public boolean isLogSystemInfo(){
        return logSystemInfo;
    }

    public void setLogSystemInfo(boolean value){
        logSystemInfo = value;
        IOManager.writeSettings(this);
    }

    public String getLogLocation(){
        return logLocation;
    }

    public void setLogLocation(String value){
        logLocation = value;
        IOManager.writeSettings(this);
    }

    public int getItemsShown(){
        return itemsShown;
    }

    public void setItemsShown(int value){
        itemsShown = value;
        IOManager.writeSettings(this);
    }

    public void saveSettings(){
        //save the users settings to ./config/config.json
        IOManager.writeSettings(this);
    }
}
