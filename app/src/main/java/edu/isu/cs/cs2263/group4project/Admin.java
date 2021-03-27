package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;

public class Admin extends User{

    public Admin(){
        super(new UserInfo("admin", "System", "Admin", "SysAdmin",
                "admin@admin.com", "admin", "admin"));

        IOManager.saveUser(this);
        IOManager.saveUserMacro(this.getUserInfo());
    }

    public void changeAdminPassword(String password){
        this.getUserInfo().setPassword(password);
    }

    public ArrayList<UserInfo> getAllUsers(){
        return IOManager.loadUserMacro();
    }

    public boolean changeUserPassword(String username, String password) {
        ArrayList<UserInfo> users = getAllUsers();
        UserInfo thisUser = null;
        for (UserInfo user : users) {
            if (user.getUsername().equals(username)) {
                thisUser = user;
                break;
            }
        }
        if (thisUser == null){
            return false;
        }
        thisUser.setPassword(password);
        return true;
    }

    public void updateSettings(Settings settings){
        IOManager.writeSettings(settings);
    }
}
