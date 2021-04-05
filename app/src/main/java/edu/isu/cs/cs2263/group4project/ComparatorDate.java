package edu.isu.cs.cs2263.group4project;

import java.util.Comparator;
import java.util.Date;
// This comparator is necessary to sort the Tasks in the Filter class according to their date
// See the documentation in the Filter class for more information

public class ComparatorDate implements Comparator<Task> {
    public int compare(Task a, Task b) {
        Date aDate = a.getDueDate();
        Date bDate = b.getDueDate();
        return aDate.compareTo(bDate);
    }
}
