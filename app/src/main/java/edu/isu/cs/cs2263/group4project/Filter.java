package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;
import java.util.Collections;

public class Filter {

    public ArrayList<Task> getListElements(List list){
        ArrayList<Task> listElements = new ArrayList<>();
        for (Section section : list.getSections()) {
            for (Task task : section.getTasks()) {
                listElements.add(task);
                listElements.addAll(task.getSubTasks());
            }
        }
        return listElements;
    }

    public ArrayList<Task> sortByLabel(List list){
        //sort list by tag
        ArrayList<Task> tasks = getListElements(list);
        Collections.sort(tasks, new ComparatorLabel());
        return tasks;
    }

    public ArrayList<Object> sortByPriority(List list){
        //sort list by priority
        ArrayList<Task> tasks = getListElements(list);
        Collections.sort(tasks, new ComparatorPriority());
        return null;
    }

    public ArrayList<Object> sortByDate(List list){
        //sort list by due date
        ArrayList<Task> tasks = getListElements(list);
        Collections.sort(tasks, new ComparatorDate());
        return null;
    }
}
