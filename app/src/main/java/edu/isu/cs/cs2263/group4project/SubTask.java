package edu.isu.cs.cs2263.group4project;

import java.util.Date;

// Subtasks are the same as Tasks, except they themselves cannot have Subtasks, and so those methods are overriden

public class SubTask extends Task{
    public SubTask(String name, int priority, String description, Date dueDate){
        super(name, priority, description, dueDate);
    }

    public SubTask(String name, int priority, String description){
        super(name, priority, description);
    }

    @Override
    public void addSubTask(SubTask subTask) {
        return;
    }

    @Override
    public boolean addSubTask(String taskName, int priority, String description, Date dueDate) {
        return false;
    }

    @Override
    public boolean addSubTask(String taskName, int priority, String description) {
        return false;
    }

    @Override
    public void deleteSubTask(SubTask subTask) {
        return;
    }
}
