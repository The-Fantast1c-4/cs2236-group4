package edu.isu.cs.cs2263.group4project;

import java.time.LocalDate;
import java.util.Date;

public class SubTask extends Task{
    public SubTask(String name, int priority, String description, LocalDate dueDate){
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
    public boolean addSubTask(String taskName, int priority, String description, LocalDate dueDate) {
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
