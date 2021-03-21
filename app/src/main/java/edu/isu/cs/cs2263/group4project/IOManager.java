package edu.isu.cs.cs2263.group4project;

public class IOManager {
    private String pathToUserInfo;

    public IOManager(){}

    public IOManager(String pathToUserInfo){
        this.pathToUserInfo = pathToUserInfo;
    }

    public String getUserInfoPath(){return pathToUserInfo;}

    

}
