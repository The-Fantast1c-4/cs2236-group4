package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;
import java.util.Date;

// The list object contains the Project the user has specified
// Inside of each list object are sections, and these sections contain the tasks

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
    public String toString() {return name;}

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

    public ArrayList<SubList> getSubLists() {
        return subLists;
    }

//setter methods

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // Get one specific section that corresponds to the desired name
    public Section getSection(String name){
        // Search through the sections until the name matches
        for(Section section: sections){
            if(section.getName().equals(name)){
                return section;
            }
        }
        // If no match, then return null
        return null;
    }
    // Get the section corresponding to a specific number
    public Section getSection(int secNum){
        return sections.get(secNum);
    }

    // Get a sublist with a specific name
    public SubList getSublist(String name){
        // Search the sublists to find the one with the correct name
        for(SubList subList: subLists){
            if(subList.getName().equals(name)){
                return subList;
            }
        }
        return null;
    }

    public void archive(){
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

    // Create a new section and add it to the list of sections
    public boolean addSection(String sectionName){
        // First we must make sure that a section with this name does not already exist
        boolean nameExists = false;
        for(Section section: sections){
            if(section.getName().equals(sectionName)){
                nameExists = true;
            }
        }
        if (nameExists){
            // If a section of a given name already exists, the program will not create the section
            return false;
        } else {
            // Otherwise, create a new section and add it to the list
            sections.add(new Section(sectionName));
            return true;
        }
    }
    // Alternatively, we can directly add a section to a list
    public void addSection(Section section){
        sections.add(section);
    }

    // Create a new SubList (or subProject) and add it to the current List
    public boolean addSubList(String subListName, String subListDesc){
        // First, we must make sure that a sublist with this name does not already exist
        boolean nameExists = false;
        for(SubList subList: subLists){
            if(subList.getName().equals(subListName)){
                nameExists = true;
            }
        }
        if (nameExists){
            // If the name already exists, do not create the sublist
            return false;
        } else {
            subLists.add(new SubList(subListName, subListDesc));
            return true;
        }
    }
    public void addSubList(SubList subList){
        subLists.add(subList);
    }


    // Delete a comment from the list
    public void deleteComment(Comment comment){
        boolean wasRemoved = comments.remove(comment);
        if (!wasRemoved){
            throw new Error("Specified comment was not found in the list of comments.");
        }
    }

    // Delete a section from the list
    public void deleteSection(Section section){
        // deletes a section
        boolean wasRemoved = sections.remove(section);
        if (!wasRemoved){
            throw new Error("Specified section was not found in the list of sections");
        }
    }

    // Delete a sublist from the list
    public void deletesSubList(SubList subList){
        // deletes a sublist
        boolean wasRemoved = subLists.remove(subList);
        if (!wasRemoved){
            throw new Error("Specified sublist was not found in the list of sublists");
        }
    }

    // Reschedules all tasks in this list only if the current list is the overdue task list
    public void rescheduleAllTasks(Date newDate) {
        if (!getName().equals("Overdue")) {
            return;
        }
        for (Task task : getSection("Default Section").getTasks()) {
            task.setDueDate(newDate);
        }
    }

    // Allows us to implement the visitor pattern. Passes this object onto the visitor
    public void accept(Visitor v){
        v.visit(this);
    }

}



