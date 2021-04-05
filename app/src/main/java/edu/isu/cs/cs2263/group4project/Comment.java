package edu.isu.cs.cs2263.group4project;

// This class holds the comments that users can apply to their Lists
// One could also just use a string for this

public class Comment {
    private String comment;

    /*Constructor */
    public Comment(String comment){
        this.comment=comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void accept(Visitor v){
        v.visit(this);
    }
}
