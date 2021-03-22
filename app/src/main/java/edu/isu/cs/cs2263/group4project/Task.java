package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;
import java.util.Date;

public class Task {
    private String name;
    private int priority;
    private String description;
    private Date dueDate;
    private boolean complete;
    private ArrayList<String> labels;
    private ArrayList<SubTask> subTasks;

    public Task(String name, int priority, String description, Date dueDate) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.dueDate = dueDate;
    }

    public Task(String name, int priority, String description) {
        this.name = name;
        this.priority = priority;
        this.description = description;
    }

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

    public boolean isComplete() {
        return complete;
    }

    public boolean isOverDue() {
        //returns true if overdue
        return false;
    }
    public void addLabel(String label){
        //adds label
    }
    public void deleteLabel(String label){
        //deletes label
    }
    public void addSubTask(SubTask subTask){
        //adds a subTask
    }
    public void deleteSubTask(SubTask subTask){
        //deletes a subTask
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                '}';
    }

    public Task clone(){
        //clones the task
        return null;
    }

    public void accept(Visitor v){
        v.visit(this);
    }
}
