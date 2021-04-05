package edu.isu.cs.cs2263.group4project;


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

    @Override
    public String toString() {
        return comment;
    }
}
