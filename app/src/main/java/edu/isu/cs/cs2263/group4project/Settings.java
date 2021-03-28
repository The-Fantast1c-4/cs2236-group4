package edu.isu.cs.cs2263.group4project;

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

    public void setUserDataLocation(String location){
        userDataLocation = location;
        IOManager.writeSettings(this);
    }

    public String getUserDirectory(){
        return userDirectory;
    }

    public void setUserDirectory(String value){
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
