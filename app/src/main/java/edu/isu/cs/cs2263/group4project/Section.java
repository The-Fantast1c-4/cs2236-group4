package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;

public class Section {
    private String name;
    private ArrayList<Task> tasks;

    public Section(String name) {
        this.name = name;
    }
    public Section() {
        name = "Section 1";
    }
//getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public Task getTask(String name){
        // returns a task
    }
    public void addTask(Task task){
        //creates task
    }
    public void deleteTask(Task task){
        //deletes task
    }
    public void moveToList(List list, Task task){
        //moves task to another list
    }


}
