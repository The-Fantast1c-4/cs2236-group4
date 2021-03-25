package edu.isu.cs.cs2263.group4project;

public class Settings {
    private String userDirectory;
    private boolean logSystemInfo;
    private String logLocation;
    private int itemsShown;
    private String userDataLocation;

    public Settings(){
        logSystemInfo = false;
        userDirectory = "./config/users.json";
        userDataLocation = "./data";
        logLocation = "";
        itemsShown = 10;
    }

    public String getUserDataDirectory(){
        return userDataLocation;
    }

    public void setUserDataLocation(String location){
        userDataLocation = location;
    }

    public String getUserDirectory(){
        return userDirectory;
    }

    public void setUserDirectory(String value){
        userDirectory = value;
    }

    public boolean isLogSystemInfo(){
        return logSystemInfo;
    }

    public void setLogSystemInfo(boolean value){
        logSystemInfo = value;
    }

    public String getLogLocation(){
        return logLocation;
    }

    public void setLogLocation(String value){
        logLocation = value;
    }

    public int getItemsShown(){
        return itemsShown;
    }

    public void setItemsShown(int value){
        itemsShown = value;
    }

    public void saveSettings(){
        //save the users settings to ./config/config.json
    }
}
