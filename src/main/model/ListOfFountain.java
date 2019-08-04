package model;

import model.exceptions.FountainTypeException;

import java.util.ArrayList;
import java.util.Observable;

public class ListOfFountain extends Observable {
    public static ArrayList<Fountain> allFountains;

    public ListOfFountain() {
        allFountains = new ArrayList<>();
        ListOfBuilding lob = new ListOfBuilding();
    }

    // REQUIRES: floor is a positive integer, type is
    //           one of: Electronic or Mechanical
    // MODIFIES: this
    // EFFECTS: Adds a fountain to the list of all fountains based on the
    //          user's input
    public void addFountain(int floor, String buildingName, String type,
                            String description) throws FountainTypeException {
        if (!(type.equals("Mechanical") || type.equals("Electronic"))) {
            throw new FountainTypeException("Error: Fountain type must be "
                    + "Mechanical or Electronic.");
        } else {
            Fountain f = new Fountain(floor, buildingName, type, description);
            setBuilding(buildingName, f);
            allFountains.add(f);
            setChanged();
            notifyObservers("added");
        }
    }

    public void removeFountain(Fountain f) {
        allFountains.remove(f);
        setChanged();
        notifyObservers("removed");
    }

    public static void setBuilding(String buildingName, Fountain f) {
        Building b = ListOfBuilding.getBuilding((buildingName));
        if (b != null) {
            f.addBuilding(b);
        } else {
            b = new Building(buildingName);
            ListOfBuilding.allBuildings.add(b);
            f.addBuilding(b);
        }
    }

    public static void reloadAllFountains() {
        for (Fountain f: allFountains) {
            setBuilding(f.getBuildingName(), f);
        }
    }

}
