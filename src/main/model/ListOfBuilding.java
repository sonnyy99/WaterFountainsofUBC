package main.model;

import java.util.ArrayList;

public class ListOfBuilding {
    public static ArrayList<Building> allBuildings;

    public ListOfBuilding() {
        allBuildings = new ArrayList<>();
    }

    public void AddBuilding(String buildingName) {
        Building b = new Building(buildingName);
        allBuildings.add(b);
    }

    public static Building getBuilding(String buildingName) {
        for (Building b: ListOfBuilding.allBuildings) {
            if (b.getName().equals(buildingName)) {
                return b;
            }
        }
        return null;
    }
}
