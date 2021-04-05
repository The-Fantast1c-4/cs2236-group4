package edu.isu.cs.cs2263.group4project;

// Sublists are the same as lists, however sublists cannot themselves also have sublists, so methods are overriden

public class SubList extends List {
    public SubList(String name, String description){
        super(name, description);
    }

    @Override
    public boolean addSubList(String subListName, String subListDesc) {
        return false;
    }

    @Override
    public void addSubList(SubList subList) {
        return;
    }

    @Override
    public void deletesSubList(SubList subList) {
        return;
    }



    public void accept(Visitor v){
        v.visit(this);
    }
}
