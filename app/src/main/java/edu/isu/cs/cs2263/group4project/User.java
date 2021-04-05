package edu.isu.cs.cs2263.group4project;

public abstract class User {
    private UserInfo userInfo;

    public User() {
        userInfo = null;
    }

    public User(UserInfo info){
        userInfo = info;
    }

    public boolean attemptLogin(String password){
        return userInfo.attemptLogin(password);
    }

    public void logout(){}

    public UserInfo getUserInfo(){
        return userInfo;
    }

    public void setUserInfo(UserInfo info) {
        userInfo = info;
    }
}
