package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;
import java.util.Date;

// UserLists houses all of the user's lists at once and allows for some specific methods for changing lists

public class UserLists {
    /* Arraylist to store List   */
    private ArrayList<List> lists;

    /*Constructor */
    public UserLists(){
        lists = new ArrayList<>();
    }

    /* Methods */

    /* This method helps to make a new list*/
    public void makeList(String name,String description){
        List newList = new List(name, description);
        lists.add(newList);
    }
    public void makeList(List list){
        lists.add(list);
    }

    /* Method to return lists */
    public ArrayList<List> getLists(){
        ArrayList<List> allLists = new ArrayList<>();
        allLists.addAll(lists);
        allLists.addAll(generateMacroLists());
        return allLists;
        //returns lists
    }

    /* Method to return Archived List */
    public ArrayList<List> getArchivedLists(){
        ArrayList<List> archivedLists = new ArrayList<>();
        for (List list : lists){
            if (list.isArchived()){
                archivedLists.add(list);
            }
        }
        return archivedLists;
    }

    /* Method to return Non Archived List */
    public ArrayList<List> getNonArchivedLists(){
        ArrayList<List> nonArchivedLists = new ArrayList<>();
        for (List list : lists){
            if (!list.isArchived()){
                nonArchivedLists.add(list);
            }
        }
        nonArchivedLists.addAll(generateMacroLists());
        return nonArchivedLists;
    }

    public List getList(String listName){
        for (List list : getLists()){
            if (list.getName().equals(listName)){
                return list;
            }
        }
        return null;
    }
    public List getList(int listNum){
        return lists.get(listNum);
    }

    public void deleteList(String listName) {
        deleteList(getList(listName));
    }

    public void deleteList(List list) {
        lists.remove(list);
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    public ArrayList<List> generateMacroLists() {
        List today = new List("Today", "List of tasks that are due today");
        List overdue = new List("Overdue", "List of tasks that are overdue");
        List upcoming = new List("Upcoming", "List of tasks that are upcoming");
        List completed = new List("Completed", "List of tasks that are completed");

        Section completedDef = completed.getSection("Default Section");
        Section upcomingDef = upcoming.getSection("Default Section");
        Section overdueDef = overdue.getSection("Default Section");
        Section todayDef = today.getSection("Default Section");

        for (List list : lists){
            for (Section section : list.getSections()) {
                for (Task task : section.getTasks()){
                    switch (getTaskState(task)) {
                        case 0:
                            completedDef.addTask(task);
                            break;
                        case 1:
                            todayDef.addTask(task);
                            break;
                        case 2:
                            upcomingDef.addTask(task);
                            break;
                        case 3:
                            overdueDef.addTask(task);
                            break;
                    }
                    for (SubTask subTask : task.getSubTasks()){
                        switch (getTaskState(subTask)) {
                            case 0:
                                completedDef.addTask(subTask);
                                break;
                            case 1:
                                todayDef.addTask(subTask);
                                break;
                            case 2:
                                upcomingDef.addTask(subTask);
                                break;
                            case 3:
                                overdueDef.addTask(subTask);
                                break;
                        }
                    }
                }
            }
            for (SubList subList : list.getSubLists()){
                for (Section section : list.getSections()) {
                    for (Task task : section.getTasks()){
                        switch (getTaskState(task)) {
                            case 0:
                                completedDef.addTask(task);
                                break;
                            case 1:
                                todayDef.addTask(task);
                                break;
                            case 2:
                                upcomingDef.addTask(task);
                                break;
                            case 3:
                                overdueDef.addTask(task);
                                break;
                        }
                        for (SubTask subTask : task.getSubTasks()){
                            switch (getTaskState(subTask)) {
                                case 0:
                                    completedDef.addTask(subTask);
                                    break;
                                case 1:
                                    todayDef.addTask(subTask);
                                    break;
                                case 2:
                                    upcomingDef.addTask(subTask);
                                    break;
                                case 3:
                                    overdueDef.addTask(subTask);
                                    break;
                            }
                        }
                    }
                }
            }
        }
        ArrayList<List> macroLists = new ArrayList<>();
        macroLists.add(today);
        macroLists.add(upcoming);
        macroLists.add(overdue);
        macroLists.add(completed);
        return macroLists;
    }

    private int getTaskState(Task task){
        Date currentDate = new Date();
        if (task.isComplete()) {
            return 0;       // This is if the task is already complete
        }
        if (task.getDueDate().after(currentDate)) {
            if ((task.getDueDate().getTime() - currentDate.getTime()) < 43200000) {
                return 1;       // This is if the due date is upcoming in the next day
            } else {
                return 2;       // This is if the due date is upcoming later than the next day
            }
        }
        if (task.isOverDue()) {
            return 3;           // This is if the task is overdue
        }
        return -1;       // This is if there is an error

    }



}
