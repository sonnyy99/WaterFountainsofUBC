package ui.model;

import ui.model.exceptions.FountainTypeException;

import java.util.ArrayList;

public class ListOfFountain {
    public static ArrayList<Fountain> allFountains;

    public ListOfFountain() {
        allFountains = new ArrayList<>();
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
            allFountains.add(f);
        }
    }

    // REQUIRES: List of fountains is not null
    // EFFECTS: Prints out information about every fountain in the given list
    public void PrintFountains(ListOfFountain lof) {
        for (Fountain f : allFountains) {
                f.PrintFountain(f);
        }
    }

    // REQUIRES: List of fountains is not null
    // EFFECTS: Prints out information about every fountain in the given list
    //          If they are of the specified type
    public void PrintFountainsType(ListOfFountain lof, String type) {
        for (Fountain f : allFountains) {
            if (f.getType().equals(type)) {
                    f.PrintFountain(f);
            }
        }
    }

    // REQUIRES: List of fountains is not null
    // EFFECTS: Prints out information about every fountain in the given list
    //          If they are in the given building
    public void PrintFountains(ListOfFountain lof, String buildingName) {
        for (Fountain f : allFountains) {
            if (f.getBuildingName().equals(buildingName)) {
                    f.PrintFountain(f);
            }
        }
    }

    // REQUIRES: List of fountains is not null
    // EFFECTS: Prints out information about every fountain in the given list
    //          If they are in the given building and of the given type
    public void PrintFountains(ListOfFountain lof, String buildingName,
                               String type) {
        for (Fountain f : allFountains) {
            if (f.getBuildingName().equals(buildingName)
                    && f.getType().equals(type)) {
                f.PrintFountain(f);
            }
        }
    }

    // REQUIRES: List of fountains is not null
    // EFFECTS: Prints out information about every fountain in the given list
    //          If they are in the given building and floor
    public void PrintFountains(ListOfFountain lof, String buildingName,
                               int floor) {
        for (Fountain f : allFountains) {
            if (f.getBuildingName().equals(buildingName)
                    && f.getFloor() == floor) {
                f.PrintFountain(f);
            }
        }
    }

    // REQUIRES: List of fountains is not null
    // EFFECTS: Prints out information about every fountain in the given list
    //          If they are in the given building, floor, and of the given type
    public void PrintFountains(ListOfFountain lof, String buildingName,
                               int floor, String type) {
        for (Fountain f : allFountains) {
            if (f.getBuildingName().equals(buildingName)
                    && f.getFloor() == floor && f.getType().equals(type)) {
                f.PrintFountain(f);
            }
        }
    }

}