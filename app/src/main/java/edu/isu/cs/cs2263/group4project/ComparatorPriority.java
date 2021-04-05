package edu.isu.cs.cs2263.group4project;

import java.util.Comparator;

// This comparator is necessary to sort the Tasks in the Filter class according to their priority
// See the documentation in the Filter class for more information

public class ComparatorPriority implements Comparator<Task> {
    public int compare(Task a, Task b) {
        int aPriority = a.getPriority();
        int bPriority = b.getPriority();
        return aPriority - bPriority;
    }
}
