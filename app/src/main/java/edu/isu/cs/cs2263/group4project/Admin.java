package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;

// This class encases the admin user, which has special privileges different than a StandardUser
// However, the admin does not have their own lists, meaning their implementation is quite unique

public class Admin extends User{

    // No args constructor for ease of deserialization
    public Admin(){

    }

    // Overriden constructor
    public Admin(UserInfo info) {

    }

    // The admin only needs to be initialized when the program first starts up
    // This password can be changed, but it is always initialized to "admin"
    public void initializeAdmin() {
        setUserInfo(new UserInfo("admin", "System", "Admin", "SysAdmin",
                "admin@admin.com", "admin", "admin"));

        IOManager.saveUser(this);
        IOManager.saveUserMacro(this.getUserInfo());
    }



    // Returns a list of the users, but only their profile information, not their data
    public ArrayList<UserInfo> getAllUsers(){
        return IOManager.loadUserMacro();
    }

    // Allows the admin to change a user's password
    public boolean changeUserPassword(String username, String password) {
        // Step 1: Find the user's information packet (UserInfo) from persistent storage
        ArrayList<UserInfo> users = getAllUsers();
        UserInfo thisUser = null;
        for (UserInfo user : users) {
            if (user.getUsername().equals(username)) {
                thisUser = user;
                break;
            }
        }
        if (thisUser == null){          // This is triggered if the user does not exist
            return false;
        }
        // Step 2: Reset the password for this user
        thisUser.setPassword(password);
        // Step 3: Save their user packet again
        IOManager.saveUserMacro(thisUser);

        // Step 4: Load the entire user's information (lists and all), and save their profile information there as well
        if (thisUser.getUsername().equals("admin")) {
            Admin user = IOManager.loadAdmin(password);
            user.setUserInfo(thisUser);
            IOManager.saveUser(user);
        } else {
            StandardUser user = IOManager.loadStandardUser(username, password);
            user.setUserInfo(thisUser);
            IOManager.saveUser(user);
        }

        return true;
    }
}
