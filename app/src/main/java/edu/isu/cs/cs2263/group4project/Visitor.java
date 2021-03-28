package edu.isu.cs.cs2263.group4project;

public interface Visitor {
    void visit(UserLists lists);
    void visit(List list);
    void visit(Section section);
    void visit(Comment comment);
    void visit(Task task);

    // It should be noted that the classes SubList and SubTask are accessible through their superclass methods

}
