package edu.isu.cs.cs2263.group4project;

import java.util.Comparator;

public class ComparatorPriority implements Comparator<Task> {
    public int compare(Task a, Task b) {
        int aPriority = a.getPriority();
        int bPriority = b.getPriority();
        return aPriority - bPriority;
    }
}
