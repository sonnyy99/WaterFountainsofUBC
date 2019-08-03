package model;

import java.util.ArrayList;

import static model.Building.loadFountains;

public class ListOfBuilding {
    public static ArrayList<Building> allBuildings;

    public ListOfBuilding() {
        allBuildings = new ArrayList<>();
    }

    public static Building getBuilding(String buildingName) {
        for (Building b: ListOfBuilding.allBuildings) {
            if (b.getName().equals(buildingName)) {
                return b;
            }
        }
        return null;
    }

    public static void reloadAllBuildings() {
        for (Building b: allBuildings) {
            b.fountainsInBuilding = new ArrayList<>();
            loadFountains(b);
        }
    }

}
