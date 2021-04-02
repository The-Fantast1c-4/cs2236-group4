package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;

public class Command {
    public static void main(String[] args) {

    }

    public static void MakeUser(String username, String firstName, String lastName, String email, String password){
        boolean usernameExists = false;
        for (UserInfo user : IOManager.loadUserMacro()){
            if (user.getUsername().equals(username)){
                usernameExists = true;
                break;
            }
        }

        if (!usernameExists) {
            // Here you must ask the user for a bio and path to picture
            String bio = "bio";
            String pathToPic = "path";
            UserInfo info = new UserInfo(username, firstName, lastName, bio, email, pathToPic, password);
            StandardUser user = new StandardUser(info);
        }
    }

    // Admin methods

    public static void ChangeSetting(){
        Settings settings = IOManager.loadSettings();

        String userDirectory = settings.getUserDirectory();
        boolean logSystemInfo = settings.isLogSystemInfo();
        String logLocation = settings.getLogLocation();
        int itemsShown = settings.getItemsShown();
        String userDataLocation = settings.getUserDataDirectory();

        // Display all five of these settings to the user
        // Then a user will change the value of one of these
        // Parse the user input
        // Now we will save their input

        settings.setUserDirectory(userDirectory);       // All of these methods auto-save
        settings.setLogSystemInfo(logSystemInfo);
        settings.setLogLocation(logLocation);
        settings.setItemsShown(itemsShown);
        settings.setUserDataLocation(userDataLocation);
    }

    public static void SeeUser(){
        // The admin should already be stored in memory by some method like App.getUser()
        Admin admin = IOManager.loadAdmin("admin");
        ArrayList<UserInfo> users = admin.getAllUsers();
        // Use this array to construct your ListView in JavaFX. Then when you want to see a user, simply do something like users.get()

    }

    public static void ChangePassword(UserInfo user, String newPassword) {
        // Again, I assume that you already have the admin stored somewhere. Do NOT use this next line of code
        Admin admin = IOManager.loadAdmin("admin");
        admin.changeUserPassword(user.getUsername(), newPassword);
    }

    public static void ChangeAdminPassword(String newPassword){
        Admin admin = IOManager.loadAdmin("admin");
        admin.changeAdminPassword(newPassword);
    }

    // Commands that can be executed in the list view

    public static void DeleteTask(){

    }

    public static void MoveTaskToList(){

    }

    public static void CreateSublist(){

    }

    public static void CreateSection(){

    }

    public static void ArchiveList(){

    }

    public static void SortList(){

    }

    public static void MakeTask(){

    }

    public static void DuplicateTask(){

    }

    public static void ViewCompletedTasks(){

    }

    public static void AddComment(){

    }

    // ____________________ Commands for the task view ___________________________


    public static void EditTask(){

    }

    public static void MarkComplete(){

    }

    public static void MakeSubTask(){

    }



    // _____________________ Commands for the list of lists ______________________________
    public static void ViewArchivedLists(){

    }

    public static void MakeList(){

    }

    // ____________________ ETC. _______________________________

    public static void Search(){

    }

}
