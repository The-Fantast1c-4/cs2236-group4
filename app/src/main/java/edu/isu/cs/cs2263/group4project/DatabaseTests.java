package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;

public class DatabaseTests {
    public static void main(String[] args) {

        Settings settings = new Settings();
        settings.initializeSettings();

        Admin admin = new Admin();
        UserInfo myInfo = new UserInfo("spierob2", "Robbie", "Spiers", "dumb college teen", "spierob2@isu.edu", "path", "hereismypassword");
        User me = new StandardUser(myInfo);
        UserInfo hisInfo = new UserInfo("mistryman", "Shivank", "Mistry", "info major", "shivu@gmail.com", "path2", "hereishispassword");
        User shivu = new StandardUser(hisInfo);


        ArrayList<UserInfo> infos = admin.getAllUsers();

        StandardUser user1 = IOManager.loadStandardUser("spierob2", "hereismypassword");
        UserLists myLists = user1.getLists();
        myLists.makeList("CS Project", "This is for our CS2263 semester project");
        IOManager.saveUser(user1);

        myLists.getList("CS Project").getSection("Default Section").addTask("Write searching code", 10, "Need to write the searching code");
        myLists.getList("CS Project").getSection("Default Section").getTask("Write searching code").addSubTask("Use cases", 5, "write the use cases");
    }
}
