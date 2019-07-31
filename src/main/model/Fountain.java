package main.model;

import java.util.Objects;

public class Fountain {
    private int floor;
    private String buildingName;
    private String type;
    private String description;
    private Building building;

    public Fountain(int floor, String buildingName, String type,
                    String description) {
        this.floor = floor;
        this.buildingName = buildingName;
        this.type = type;
        this.description = description;
    }

    public void addBuilding(Building b) {
        if (this.building == null) {
                this.building = b;
                b.addFountain(this);
            }
        }

    public int getFloor() {
        return this.floor;
    }

    public String getBuildingName() {
        return this.buildingName;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public Building getBuilding() {
        return this.building;
    }

    // EFFECTS: Prints out information about the given fountain
    public void printFountain(Fountain f) {
            System.out.println("Floor: " + f.getFloor());
            System.out.println("Building: " + f.getBuildingName());
            System.out.println("Type of Fountain: " + f.getType());
            System.out.println("Description of Location: " + f.getDescription() + "\n");
        }

    @Override
    public boolean equals(Object o) {
        if (this == o)  {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fountain fountain = (Fountain) o;
        return Objects.equals(building, fountain.building);
    }

    @Override
    public int hashCode() {

        return Objects.hash(building);
    }
}
