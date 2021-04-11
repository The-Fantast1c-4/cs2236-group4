package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;
import java.util.Collections;

// This class implements external routines to sort a section according to the properties of its tasks
// Possible sorting options include: "label", "priority", and "date"

public class Filter {

    // This method sorts through a section and returns all of the tasks and subtasks
    protected static ArrayList<Task> getListElements(Section section){
        ArrayList<Task> listElements = new ArrayList<>();
        for (Task task : section.getTasks()) {
            listElements.add(task);
            listElements.addAll(task.getSubTasks());
        }
        return listElements;
    }

    // This method controls the sorting by delegating the sortTerm to auxiliary methods
    public static ArrayList<Task> sortBy(Section section, String sortTerm){
        ArrayList<Task> sorted;
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

    // This method sorts the Section by the first label in each task
    public static ArrayList<Task> sortByLabel(Section section){
        //sort list by tag
        ArrayList<Task> tasks = getListElements(section);
        Collections.sort(tasks, new ComparatorLabel());             // The comparator is used to define the sorting operation
        return tasks;
    }

    // This method sorts the Section by the priority of each task
    public static ArrayList<Task> sortByPriority(Section section){
        //sort list by priority
        ArrayList<Task> tasks = getListElements(section);
        Collections.sort(tasks, new ComparatorPriority());
        return tasks;
    }

    // This method sorts the Section by the due date of each task
    public static ArrayList<Task> sortByDate(Section section){
        //sort list by due date
        ArrayList<Task> tasks = getListElements(section);
        Collections.sort(tasks, new ComparatorDate());
        return tasks;
    }
}
