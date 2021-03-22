package edu.isu.cs.cs2263.group4project;

public class SearchingVisitor implements Visitor{
    private String searchTerm;

    public SearchingVisitor(String searchTerm){
        this.searchTerm = searchTerm;
    }

    public boolean visit(List list){
        // If this list contains the searchTerm, return true
        return false;
    }

    public boolean visit(Section section){
        // If this section contains the searchTerm, return true
        return false;
    }

    public boolean visit(Task task){
        // If this task contains the searchTerm, return true
        return false;
    }

    public boolean visit(Comment comment){
        // If this comment contains the searchTerm, return true
        return false;
    }
}
