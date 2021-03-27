package edu.isu.cs.cs2263.group4project;

public class StandardUser extends User{

    private UserLists lists;

    public StandardUser(UserInfo info) {
        super(info);
        lists = new UserLists();
    }


}
