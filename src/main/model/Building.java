package main.model;

import java.util.ArrayList;
import java.util.Objects;

public class Building {
    private String name;
    protected transient ArrayList<Fountain> fountainsInBuilding;

    public Building(String name) {
        this.name = name;
        this.fountainsInBuilding = new ArrayList<>();
    }

    public void addFountain(Fountain f) {
        if(!fountainsInBuilding.contains(f)) {
            fountainsInBuilding.add(f);
            f.addBuilding(this);
        }
    }

    public static void loadFountains(Building b) {
        for (Fountain f: ListOfFountain.allFountains) {
            if (f.getBuildingName().equals(b.getName())) {
                b.fountainsInBuilding.add(f);
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Fountain> getFountains() {
        return this.fountainsInBuilding;
    }

}


