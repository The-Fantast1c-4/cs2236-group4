package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;

public class List {
    private String name;
    private String description;
    private boolean isArchived = false;
    private ArrayList<Section> sections = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<SubList> subLists = new ArrayList<>();

    public List(String name, String description) {
        this.name = name;
        this.description = description;
        addSection("Default Section");
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
        for(Section section: sections){
            if(section.getName().equals(name)){
                return section;
            }
        }
        return null;
    }
    public void Archive(){
        this.isArchived = true;
    }
    public void unArchive(){
        this.isArchived = false;
    }
    public void addComment(String commentText){
        comments.add(new Comment(commentText));
    }
    public void addComment(Comment comment){
        comments.add(comment);
    }
    public boolean addSection(String sectionName){
        boolean nameExists = false;
        for(Section section: sections){
            if(section.getName().equals(sectionName)){
                nameExists = true;
            }
        }
        if (nameExists){
            // Need to handle what happens if user attempts to recreate existing name, do we overwrite?
            return false;
        } else {
            sections.add(new Section(sectionName));
            return true;
        }
    }
    public void addSection(Section section){
        sections.add(section);
    }
    public boolean addSubList(String subListName, String subListDesc){
        boolean nameExists = false;
        for(SubList subList: subLists){
            if(subList.getName().equals(subListName)){
                nameExists = true;
            }
        }
        if (nameExists){
            // Need to handle what happens if user attempts to recreate existing name, do we overwrite?
            return false;
        } else {
            subLists.add(new SubList(subListName, subListDesc));
            return true;
        }
    }
    public void addSubList(SubList subList){
        subLists.add(subList);
    }


    public void deleteComment(Comment comment){
        boolean wasRemoved = comments.remove(comment);
        if (!wasRemoved){
            throw new Error("Specified comment was not found in the list of comments.");
        }
    }
    public void deleteSection(Section section){
        // deletes a section
        boolean wasRemoved = sections.remove(section);
        if (!wasRemoved){
            throw new Error("Specified section was not found in the list of sections");
        }
    }
    public void deletesSubList(SubList subList){
        // deletes a sublist
        boolean wasRemoved = subLists.remove(subList);
        if (!wasRemoved){
            throw new Error("Specified sublist was not found in the list of sublists");
        }
    }

    public void accept(Visitor v){
        v.visit(this);
    }

}



