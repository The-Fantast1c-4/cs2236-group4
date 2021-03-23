package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;
import java.util.Date;

public class Section {
    private String name;
    private ArrayList<Task> tasks;

    public Section(String name) {
        this.name = name;
    }
    public Section() {
        name = "Default Section";
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
        for(Task task: tasks){
            if (task.getName().equals(name)){
                return task;
            }
        }
        return null;
    }
    public void addTask(Task task){
        //creates task
        tasks.add(task);
    }
    public boolean addTask(String taskName, int priority, String description, Date dueDate){
        boolean nameExists = false;
        for(Task task: tasks){
            if(task.getName().equals(taskName)){
                nameExists = true;
            }
        }
        if (nameExists){
            // Need to handle what happens if user attempts to recreate existing name, do we overwrite?
            return false;
        } else {
            tasks.add(new Task(taskName, priority, description, dueDate));
            return true;
        }
    }
    public boolean addTask(String taskName, int priority, String description){
        boolean nameExists = false;
        for(Task task: tasks){
            if(task.getName().equals(taskName)){
                nameExists = true;
            }
        }
        if (nameExists){
            // Need to handle what happens if user attempts to recreate existing name, do we overwrite?
            return false;
        } else {
            tasks.add(new Task(taskName, priority, description));
            return true;
        }
    }
    public void deleteTask(Task task){
        //deletes task
        boolean wasRemoved = tasks.remove(task);
        if (!wasRemoved){
            throw new Error("Specified task was not found in the given section");
        }
    }
    public void moveToList(List list, Task task){
        //moves task to another list
        // Here we could ask for the target section in the GUI
        Section targetSection = list.getSection("Default Section");
        targetSection.addTask(task);
        deleteTask(task);
    }

    public void accept(Visitor v){
        v.visit(this);
    }


}
