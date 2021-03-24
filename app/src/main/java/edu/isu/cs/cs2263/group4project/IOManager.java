package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;

public class IOManager {
    private String pathToUserInfo;

    public IOManager(){}

    public IOManager(String pathToUserInfo){
        this.pathToUserInfo = pathToUserInfo;
    }

    public String getPath(){return pathToUserInfo;}

    public void setPath(String pathToUserInfo) { this.pathToUserInfo = pathToUserInfo; }

    public boolean writeSettings(Settings settings){
        return false;
    }

    public Settings loadSettings(){
        return null;
    }

    public void logSysInfo(String info){
        return;
    }

    public void writeUsers(ArrayList<User> users){
        return;
    }

    public ArrayList<UserInfo> loadUserMacro(){
        return null;
    }

    public User loadUser(String username, String password){
        return null;
    }

    public void saveUser(User user){
        return;
    }
}
