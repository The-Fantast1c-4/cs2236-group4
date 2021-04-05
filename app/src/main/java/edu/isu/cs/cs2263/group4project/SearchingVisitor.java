package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;

// Implementation of the Visitor design pattern to facilitate the searching of objects
// With this class, we can either search through all the lists to find a specified list, or we can search through one list to find matching tasks

public class SearchingVisitor implements Visitor{
    // Fields
    private String searchTerm;
    private ArrayList<List> listMatches = new ArrayList<>();
    private ArrayList<Task> taskMatches = new ArrayList<>();


    // The visitor is constructed with the desired search term (to match)
    public SearchingVisitor(String searchTerm){
        this.searchTerm = searchTerm;
    }

    // Getter methods
    public ArrayList<List> getListMatches(){
        return listMatches;
    }
    public ArrayList<Task> getTaskMatches() {
        return taskMatches;
    }

    // This visitor forms a tree structure, first we must iterate through all of the lists
    public void visit(UserLists lists) {
        for (List list : lists.getLists()){
            list.accept(this);
        }
    }

    // If any of the properties of the list contains the search term, the lsit will be added to listMatches
    public void visit(List list){

        boolean listContainsTerm = false;
        // Check if the search term is in the name
        if (list.getName().contains(searchTerm)){
            listContainsTerm = true;
        }
        // Check if the search term is in the description
        if (list.getDescription().contains(searchTerm)){
            listContainsTerm = true;
        }
        // Check if the search term is in any of the comments
        for (Comment comment : list.getComments()) {
            if (comment.getComment().contains(searchTerm)){
                listContainsTerm = true;
                break;
            }
        }
        // Check if the search term is in any of the sections names
        for (Section section : list.getSections()){
            // This line searches through the sections to find matching tasks
            section.accept(this);
            if (section.getName().contains(searchTerm)){
                listContainsTerm = true;
            }
        }
        // If any of the properties contained the search term, add the list to the matches
        if (listContainsTerm) {
            listMatches.add(list);
        }
    }

    // Look through each of the tasks in the section to check if they have a matching term
    public void visit(Section section){
        for (Task task : section.getTasks()) {
            task.accept(this);
        }
    }


    // Looks through all the task properties to see if they contain the search term
    // If they do contain it, then add the Task to the matches
    public void visit(Task task){
        boolean taskContainsTerm = false;
        if (task.getName().contains(searchTerm)){
            taskContainsTerm = true;
        }
        if (task.getDescription().contains(searchTerm)){
            taskContainsTerm = true;
        }
        for (String label : task.getLabels()) {
            if (label.contains(searchTerm)){
                taskContainsTerm = true;
                break;
            }
        }
        if (taskContainsTerm) {
            taskMatches.add(task);
        }

        for (SubTask subtask : task.getSubTasks()){
            subtask.accept(this);
        }
    }

    public void visit(Comment comment){
    }
}
