package main.model;

import java.util.ArrayList;

public class Building {
    private String name;
    private transient ArrayList<Fountain> fountainsInBuilding;

    public Building(String name) {
        this.name = name;
        fountainsInBuilding = new ArrayList<>();
    }

    public void addFountain(Fountain f) {
        if(!fountainsInBuilding.contains(f)) {
            fountainsInBuilding.add(f);
            f.addBuilding(this);
        }
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Fountain> getFountains() {
        return this.fountainsInBuilding;
    }
}


