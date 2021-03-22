package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;

public class List {
    private String name;
    private String description;
    private boolean isArchived = false;
    private ArrayList<Section> sections;
    private ArrayList<Comment> comments;
    private ArrayList<SubList> subLists;

    public List(String name, String description) {
        this.name = name;
        this.description = description;
    }
//getter methods
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
//setter methods

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Section getSection(String name){
        //returns section with name
        return new Section();
    }
    public void Archive(){
        //archives a list
    }
    public void unArchive(){
        //unarchives a list
    }
    public void addComment(Comment comment){
        //adds comment
    }
    public void addSection(Section section){
        // adds a section
    }
    public void addSubList(SubList subList){
        // adds a sublist
    }
    public void deleteComment(Comment comment){
        //deletes comment
    }
    public void deleteSection(Section section){
        // deletes a section
    }
    public void deletesSubList(SubList subList){
        // deletes a sublist
    }

    public void accept(Visitor v){
        v.visit(this);
    }

}



