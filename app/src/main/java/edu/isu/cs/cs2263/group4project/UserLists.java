package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;

public class UserLists {
    /* Arraylist to store List   */
    private ArrayList<List> lists;

    /*Constructor */
    public UserLists(){
        lists = new ArrayList<>();
    }

    /* Methods */

    /* This method helps to make a new list*/
    public void makeList(String name,String description){
        List newList = new List(name, description);
        lists.add(newList);
    }

    /* Method to return lists */
    public ArrayList<List> getLists(){
        return lists;
        //returns lists
    }

    /* Method to return Archived List */
    public ArrayList<List> getArchivedLists(){
        ArrayList<List> archivedLists = new ArrayList<>();
        for (List list : lists){
            if (list.isArchived()){
                archivedLists.add(list);
            }
        }
        return archivedLists;
    }

    /* Method to return Non Archived List */
    public ArrayList<List> getNonArchivedLists(){
        ArrayList<List> nonArchivedLists = new ArrayList<>();
        for (List list : lists){
            if (list.isArchived()){
                nonArchivedLists.add(list);
            }
        }
        return nonArchivedLists;
    }

    public List getList(String listName){
        for (List list : lists){
            if (list.getName().equals(listName)){
                return list;
            }
        }
        return null;
    }





}
