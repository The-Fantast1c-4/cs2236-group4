package edu.isu.cs.cs2263.group4project;

import java.util.Comparator;

// This comparator is necessary to sort the Tasks in the Filter class according to their first label
// See the documentation in the Filter class for more information

public class ComparatorLabel implements Comparator<Task> {
    public int compare(Task a, Task b) {
        String aLabel = a.getLabels().get(0);
        String bLabel = b.getLabels().get(0);
        return aLabel.compareTo(bLabel);
    }
}
