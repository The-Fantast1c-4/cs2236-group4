package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;
import java.util.Collections;

public class Filter {

    protected static ArrayList<Task> getListElements(Section section){
        ArrayList<Task> listElements = new ArrayList<>();
        for (Task task : section.getTasks()) {
            listElements.add(task);
            listElements.addAll(task.getSubTasks());
        }
        return listElements;
    }

    public static ArrayList<Task> sortBy(Section section, String sortTerm){
        switch (sortTerm) {
            case "label":
                return sortByLabel(section);
            case "priority":
                return sortByPriority(section);
            case "date":
                return sortByDate(section);
            default:
                return null;
        }
    }

    public static ArrayList<Task> sortByLabel(Section section){
        //sort list by tag
        ArrayList<Task> tasks = getListElements(section);
        Collections.sort(tasks, new ComparatorLabel());
        return tasks;
    }

    public static ArrayList<Task> sortByPriority(Section section){
        //sort list by priority
        ArrayList<Task> tasks = getListElements(section);
        Collections.sort(tasks, new ComparatorPriority());
        return tasks;
    }

    public static ArrayList<Task> sortByDate(Section section){
        //sort list by due date
        ArrayList<Task> tasks = getListElements(section);
        Collections.sort(tasks, new ComparatorDate());
        return tasks;
    }
}
