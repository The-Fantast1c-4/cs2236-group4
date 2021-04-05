package edu.isu.cs.cs2263.group4project;

// StandardUsers are the general users which do not have admin privileges
// They have lists in addition to their UserInfo profile

public class StandardUser extends User{

    private UserLists lists;

    // Constructor
    public StandardUser(UserInfo info) {
        super(info);
        // Must initialize to have a userLists object
        lists = new UserLists();
        IOManager.saveUser(this);
        IOManager.saveUserMacro(info);
    }

    public UserLists getLists(){
        return lists;
    }


}
