package ui.model;

import java.util.ArrayList;

public class ListOfFountain {
    public static ArrayList<Fountain> allFountains;

    public ListOfFountain() {
        allFountains = new ArrayList<>();
    }

    // REQUIRES: floor is a positive integer
    // MODIFIES: this
    // EFFECTS: Adds a fountain to the list of all fountains based on the user's input
    public void AddFountain(int floor, String buildingName, String type, String description) {
        Fountain f = new Fountain(floor, buildingName, type, description);
        allFountains.add(f);
    }

    // REQUIRES: Given fountain is not null
    // EFFECTS: Prints out information about the given fountain
    public void PrintFountain(Fountain f) {
        System.out.println("Floor: " + f.getFloor());
        System.out.println("Building: " + f.getBuildingName());
        System.out.println("Type of Fountain: " + f.getType());
        System.out.println("Description of Location: " + f.getDescription() + "\n");
    }

    // REQUIRES: List of fountains is not null
    // EFFECTS: Prints out information about every fountain in the given list
    public void PrintFountains(ListOfFountain lof) {
        for (Fountain f : lof.allFountains) {
            PrintFountain(f);
        }
    }
}