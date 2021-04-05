package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;
import java.util.Date;

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

    public static void DeleteTask(Section currentSection, Task currentTask){
        // Throws an error if task is not found
        currentSection.deleteTask(currentTask);
        // After doing this, you must call IOManager.saveUser(currentUser);
    }

    public static void MoveTaskToList(Section currentSection, Task currentTask, List targetList){
        // You will need to implement some feature to generate the possible target lists
        // In other words, use the User.getLists().getLists() method to pull the lists

        currentSection.moveToList(targetList, currentTask);
        // After doing this, you must call IOManager.saveUser(currentUser);
    }

    public static void CreateSublist(List workingList, String name, String desc){
        workingList.addSubList(name, desc);
        // After doing this, you must call IOManager.saveUser(currentUser);
    }

    public static void CreateSection(List workingList, String sectionName){
        workingList.addSection(new Section(sectionName));
        // After doing this, you must call IOManager.saveUser(currentUser);
    }

    public static void ArchiveList(List workingList){
        workingList.archive();

        // After doing this, you must call IOManager.saveUser(currentUser);
    }

    public static void SortList(Section workingList, String searchBy){
        // Options for searchBy: "label", "priority", "date"
        ArrayList<Task> sortedTasks = Filter.sortBy(workingList, searchBy);
    }

    public static void MakeTask(Section workingSection, String taskName, int priority, String description){
        // There are two methods, you can either specify a date or it will automatically select a date for you
        Date date = new Date();
        workingSection.addTask(taskName, priority, description);
        // Alternatively, if a date is specified then you can use the following method:
        workingSection.addTask(taskName, priority, description, date);

        // After doing either of these, you must call IOManager.saveUser(currentUser);
    }

    public static void DuplicateTask(Section workingSection, Task task){
        Task newTask = workingSection.cloneTask(task);
        workingSection.addTask(newTask);
        // After doing this, you must call IOManager.saveUser(currentUser);
    }

    public static void ViewCompletedTasks(Section workingSection){
        ArrayList<Task> completedTasks = workingSection.getCompletedTasks();
    }

    public static void AddComment(List workingList, String commentText){
        workingList.addComment(commentText);
        // After doing this, you must call IOManager.saveUser(currentUser);
    }

    // ____________________ Commands for the task view ___________________________


    public static void EditTask(Task workingTask){
        // Step 1: Load the information about the task
        String name = workingTask.getName();
        int priority = workingTask.getPriority();
        String description = workingTask.getDescription();
        Date dueDate = workingTask.getDueDate();

        // Step 2: Allow the user to change these values, then read them back in

        // Step 3: Set these values
        workingTask.setName(name);
        workingTask.setPriority(priority);
        workingTask.setDescription(description);
        workingTask.setDueDate(dueDate);

        // After doing this, you must call IOManager.saveUser(currentUser);
    }

    public static void MarkComplete(Task currentTask){
        currentTask.markComplete();

        // To mark as incomplete, use the following code:
        currentTask.markIncomplete();

        // After doing either of these, you must call IOManager.saveUser(currentUser);
    }

    public static void MakeSubTask(Task currentTask, String taskName, int priority, String description) {
        // There are two methods, you can either specify a date or it will automatically select a date for you
        Date date = new Date();
        currentTask.addSubTask(taskName, priority, description);
        // Alternatively, if a date is specified then you can use the following method:
        currentTask.addSubTask(taskName, priority, description, date);

        // After doing either of these, you must call IOManager.saveUser(currentUser);
    }



    // _____________________ Commands for the list of lists ______________________________
    public static void ViewArchivedLists(UserLists lists){
        ArrayList<List> archivedLists = lists.getArchivedLists();
    }

    public static void ViewUnarchivedLists(UserLists lists){
        ArrayList<List> archivedLists = lists.getNonArchivedLists();
    }

    public static void MakeList(UserLists lists, String name, String description){
        lists.makeList(name, description);

        // After doing either of these, you must call IOManager.saveUser(currentUser);
    }

    // ____________________ ETC. _______________________________

    public static void SearchTasks(List currentList, String searchTerm){
        SearchingVisitor v = new SearchingVisitor(searchTerm);
        currentList.accept(v);
        ArrayList<Task> matches = v.getTaskMatches();
    }

    public static void SearchLists(UserLists userLists, String searchTerm){
        SearchingVisitor v = new SearchingVisitor(searchTerm);
        userLists.accept(v);
        ArrayList<List> matches = v.getListMatches();
    }

}
