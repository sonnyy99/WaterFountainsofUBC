package ui.model;

import java.util.ArrayList;

public class ListOfFountain {
    public static ArrayList<Fountain> allFountains;

    public ListOfFountain() {
        allFountains = new ArrayList<>();
    }

    // REQUIRES: floor is a positive integer, type is
    //           one of: Electronic or Mechanical
    // MODIFIES: this
    // EFFECTS: Adds a fountain to the list of all fountains based on the user's input
    public void AddFountain(int floor, String buildingName, String type, String description) {
        if(type == "Electronic") {
            Fountain f = new ElectronicFountain(floor, buildingName, type, description);
            allFountains.add(f);
        }
        if(type == "Mechanical") {
            Fountain f = new MechanicalFountain(floor, buildingName, type, description);
            allFountains.add(f);
        }
    }

    // REQUIRES: List of fountains is not null
    // EFFECTS: Prints out information about every fountain in the given list
    public void PrintFountains(ListOfFountain lof) {
        for (Fountain f : lof.allFountains) {
            f.PrintFountain(f);
        }
    }
}