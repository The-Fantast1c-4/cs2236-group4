package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;

public class SearchingVisitor implements Visitor{
    private String searchTerm;
    private ArrayList<List> listMatches = new ArrayList<>();
    private ArrayList<Task> taskMatches = new ArrayList<>();

    public SearchingVisitor(String searchTerm){
        this.searchTerm = searchTerm;
    }

    public ArrayList<List> getListMatches(){
        return listMatches;
    }
    public ArrayList<Task> getTaskMatches() {
        return taskMatches;
    }

    public void visit(UserLists lists) {
        for (List list : lists.getLists()){
            list.accept(this);
        }
    }

    public void visit(List list){
        // If this list contains the searchTerm, return true
        boolean listContainsTerm = false;
        if (list.getName().contains(searchTerm)){
            listContainsTerm = true;
        }
        if (list.getDescription().contains(searchTerm)){
            listContainsTerm = true;
        }
        for (Comment comment : list.getComments()) {
            if (comment.getComment().contains(searchTerm)){
                listContainsTerm = true;
                break;
            }
        }
        for (Section section : list.getSections()){
            section.accept(this);
            if (section.getName().contains(searchTerm)){
                listContainsTerm = true;
            }
        }
        if (listContainsTerm) {
            listMatches.add(list);
        }
    }

    public void visit(Section section){
        // If this section contains the searchTerm, return true
        for (Task task : section.getTasks()) {
            task.accept(this);
        }
    }

    public void visit(Task task){
        // If this task contains the searchTerm, return true
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
