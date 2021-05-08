package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;
import java.util.Date;

// Tasks encode our to-do information, such as a priority specification and a due date

public class Task {
    // Fields
    private String name;
    private int priority;
    private String description;
    private Date dueDate;
    private boolean complete;
    private ArrayList<String> labels = new ArrayList<>();
    private ArrayList<SubTask> subTasks = new ArrayList<>();

    // Constructor with a specific due date
    public Task(String name, int priority, String description, Date dueDate) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.dueDate = dueDate;
        this.complete = false;
    }

    // Constructor without a specific due date
    // Default due date is today
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

    // Check to see if the task is overdue
    public boolean isOverDue() {
        //returns true if overdue
        if (isComplete()) {
            return false;
        }
        return dueDate.before(new Date());       // Date() constructor automatically sets the time to the current time
    }

    // Add a new label
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

    // Construct a new sub task
    public boolean addSubTask(String taskName, int priority, String description, Date dueDate){
        // First, make sure that the subtask name does not already exist
        boolean nameExists = false;
        for(SubTask task: subTasks){
            if(task.getName().equals(taskName)){
                nameExists = true;
            }
        }
        if (nameExists){
            // If name already exists, do not construct
            return false;
        } else {
            subTasks.add(new SubTask(taskName, priority, description, dueDate));
            return true;
        }
    }

    // Same constructor but without a date
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

    public SubTask getSubTask(String name){
        //returns a subtask with the given name
        for(SubTask subTask: subTasks){
            if(subTask.getName().equals(name)){
                return subTask;
            }
        }
        return null;
    }

    public void deleteSubTask(SubTask subTask){
        //deletes a subTask
        subTasks.remove(subTask);
    }

    @Override
    public String toString() {
        return name;
    }

    // Hacky method to duplicate the task without accidentally duplicating its SubTasks
    public Task clone() {
        return new Task(this.name, this.priority, this.description, this.dueDate);
    }

    // Implementation for the visitor pattern
    public void accept(Visitor v){
        v.visit(this);
    }
}
