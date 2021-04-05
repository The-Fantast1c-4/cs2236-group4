package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;
import java.util.Date;

public class Task {
    private String name;
    private int priority;
    private String description;
    private Date dueDate;
    private boolean complete;
    private ArrayList<String> labels = new ArrayList<>();
    private ArrayList<SubTask> subTasks = new ArrayList<>();

    public Task(String name, int priority, String description, Date dueDate) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.dueDate = dueDate;
        this.complete = false;
    }

    public Task(String name, int priority, String description) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.complete = false;
        this.dueDate = new Date();
    }

    // Basic getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public ArrayList<SubTask> getSubTasks(){
        return subTasks;
    }

    public ArrayList<String> getLabels(){
        return labels;
    }

    public boolean isComplete() {
        return complete;
    }

    public void markComplete() {
        complete = true;
    }

    public void markIncomplete() {
        complete = false;
    }

    public boolean isOverDue() {
        //returns true if overdue
        return dueDate.after(new Date());       // Date() constructor automatically sets the time to the current time
    }
    public void addLabel(String label){
        //adds label
        labels.add(label);
    }
    public void deleteLabel(String label){
        //deletes label
        labels.remove(label);
    }
    public void addSubTask(SubTask subTask){
        //adds a subTask
        subTasks.add(subTask);
    }
    public boolean addSubTask(String taskName, int priority, String description, Date dueDate){
        boolean nameExists = false;
        for(SubTask task: subTasks){
            if(task.getName().equals(taskName)){
                nameExists = true;
            }
        }
        if (nameExists){
            // Need to handle what happens if user attempts to recreate existing name, do we overwrite?
            return false;
        } else {
            subTasks.add(new SubTask(taskName, priority, description, dueDate));
            return true;
        }
    }
    public boolean addSubTask(String taskName, int priority, String description){
        boolean nameExists = false;
        for(SubTask task: subTasks){
            if(task.getName().equals(taskName)){
                nameExists = true;
            }
        }
        if (nameExists){
            // Need to handle what happens if user attempts to recreate existing name, do we overwrite?
            return false;
        } else {
            subTasks.add(new SubTask(taskName, priority, description));
            return true;
        }
    }
    public void deleteSubTask(SubTask subTask){
        //deletes a subTask
        subTasks.remove(subTask);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                '}';
    }

    public Task clone() {
        return new Task(this.name, this.priority, this.description, this.dueDate);
    }

    public void accept(Visitor v){
        v.visit(this);
    }
}
