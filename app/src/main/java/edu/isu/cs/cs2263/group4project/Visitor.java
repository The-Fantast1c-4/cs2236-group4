package edu.isu.cs.cs2263.group4project;

public interface Visitor {
    boolean visit(List list);
    boolean visit(Section section);
    boolean visit(Comment comment);
    boolean visit(Task task);

    // It should be noted that the classes SubList and SubTask are accessible through their superclass methods

}
