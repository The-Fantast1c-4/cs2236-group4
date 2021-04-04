package edu.isu.cs.cs2263.group4project;

import java.time.LocalDate;
import java.util.Comparator;

public class ComparatorDate implements Comparator<Task> {
    public int compare(Task a, Task b) {
        LocalDate aDate = a.getDueDate();
        LocalDate bDate = b.getDueDate();
        return aDate.compareTo(bDate);
    }
}
