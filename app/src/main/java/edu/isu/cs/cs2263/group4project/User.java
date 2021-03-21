package edu.isu.cs.cs2263.group4project;

public class User {
    private UserInfo userInfo;

    public User(UserInfo info){
        userInfo = info;
    }

    public boolean attemptLogin(String password){
        return false;
    }

    public void logout(){}

    public UserInfo getUserInfo(){
        return userInfo;
    }
}
