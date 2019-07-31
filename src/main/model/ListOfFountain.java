package main.model;

import main.model.exceptions.FountainTypeException;

import java.util.ArrayList;

public class ListOfFountain {
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
    public void AddFountain(int floor, String buildingName, String type,
                            String description) throws FountainTypeException {
        if (!(type.equals("Mechanical") || type.equals("Electronic"))) {
            throw new FountainTypeException("Error: Fountain type must be "
                    + "Mechanical or Electronic.");
        } else {
            Fountain f = new Fountain(floor, buildingName, type, description);
            setBuilding(buildingName, f);
            allFountains.add(f);
        }

}

    private void setBuilding(String buildingName, Fountain f) {
        if (ListOfBuilding.getBuilding(buildingName) != null) {
            f.addBuilding(ListOfBuilding.getBuilding(buildingName));
        } else {
            Building b = new Building(buildingName);
            ListOfBuilding.allBuildings.add(b);
            f.addBuilding(b);
        }
    }

    // REQUIRES: List of fountains is not null
    // EFFECTS: Prints out information about every fountain in the given list
    public void PrintFountains(ListOfFountain lof) {
        for (Fountain f : allFountains) {
            f.printFountain(f);
        }
    }
}
