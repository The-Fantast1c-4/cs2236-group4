package edu.isu.cs.cs2263.group4project;

import java.util.Comparator;
import java.util.Date;

public class ComparatorDate implements Comparator<Task> {
    public int compare(Task a, Task b) {
        Date aDate = a.getDueDate();
        Date bDate = b.getDueDate();
        return aDate.compareTo(bDate);
    }
}
