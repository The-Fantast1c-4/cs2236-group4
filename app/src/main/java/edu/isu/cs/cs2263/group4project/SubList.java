package edu.isu.cs.cs2263.group4project;

import java.util.ArrayList;

public class SubList extends List {
    public SubList(String name, String description){
        super(name, description);
    }

    @Override
    public void addSubList(SubList subList) {
        super.addSubList(subList);
    }

    @Override
    public void deletesSubList(SubList subList) {
        super.deletesSubList(subList);
    }



    public void accept(Visitor v){
        v.visit(this);
    }
}
