package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;
import java.util.Date;

// Sections are a data structure that form subsets of Tasks. The sections themselves contain the Tasks

public class Section {
    // Fields
    private String name;
    private ArrayList<Task> tasks;

    // Two constructors
    public Section(String name) {
        this.name = name;
        tasks = new ArrayList<>();
    }
    public Section() {
        name = "Default Section";
        tasks = new ArrayList<>();
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

    // Get a specific task with a specified name
    public Task getTask(String name){
        // returns a task
        for(Task task: tasks){
            if (task.getName().equals(name)){
                return task;
            }
        }
        return null;
    }

    // Return a list of only the completed tasks
    public ArrayList<Task> getCompletedTasks(){
        ArrayList<Task> completed = new ArrayList<>();
        for (Task task : tasks){
            if (task.isComplete()){
                completed.add(task);
            }
        }
        return completed;
    }

    // Adds a task to the section
    public void addTask(Task task){
        //creates task
        tasks.add(task);
    }

    // Adds a task to the section, but constructs the task internally
    public boolean addTask(String taskName, int priority, String description, Date dueDate){
        boolean nameExists = false;
        // Checks to see if the task name already exists
        for(Task task: tasks){
            if(task.getName().equals(taskName)){
                nameExists = true;
            }
        }
        if (nameExists){
            return false;
        } else {
            tasks.add(new Task(taskName, priority, description, dueDate));
            return true;
        }
    }

    // Adds a task without a specified date to the section
    public boolean addTask(String taskName, int priority, String description){
        boolean nameExists = false;
        // Checks to see if the task name already exists
        for(Task task: tasks){
            if(task.getName().equals(taskName)){
                nameExists = true;
            }
        }
        if (nameExists){
            // If name already exists, do not create new task
            return false;
        } else {
            tasks.add(new Task(taskName, priority, description));
            return true;
        }
    }

    // Removes a task from the section
    public void deleteTask(Task task){
        //deletes task
        boolean wasRemoved = tasks.remove(task);
        if (!wasRemoved){
            throw new Error("Specified task was not found in the given section");
        }
    }

    // Moves a task from this section to another list, in the default section
    public void moveToList(List list, Task task){
        //moves task to another list
        // Here we could ask for the target section in the GUI
        Section targetSection = list.getSection("Default Section");
        targetSection.addTask(task);
        deleteTask(task);
    }

    // Duplicates a task
    public Task cloneTask(Task task){
        return task.clone();
    }

    // Necessary for the Visitor infrastructure
    public void accept(Visitor v){
        v.visit(this);
    }


}
