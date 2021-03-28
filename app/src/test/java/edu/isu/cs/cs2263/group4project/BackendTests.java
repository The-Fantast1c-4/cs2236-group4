package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;

public class BackendTests {
    public static void main(String[] args) {
        Settings settings = IOManager.loadSettings();
        settings.initializeSettings();

        Admin admin = new Admin();
        UserInfo myInfo = new UserInfo("spierob2", "Robbie", "Spiers", "dumb college teen", "spierob2@isu.edu", "path", "hereismypassword");
        User me = new StandardUser(myInfo);
        UserInfo hisInfo = new UserInfo("mistryman", "Shivank", "Mistry", "info major", "shivu@gmail.com", "path2", "hereishispassword");
        User shivu = new StandardUser(hisInfo);


        ArrayList<UserInfo> infos = admin.getAllUsers();
    }
}
