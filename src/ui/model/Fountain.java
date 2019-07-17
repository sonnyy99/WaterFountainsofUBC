package ui.model;

public abstract class Fountain {
    protected int floor;
    protected String buildingName;
    protected String type;
    protected String description;

    public Fountain(String type, int floor, String description, String buildingName) {
        this.type = type;
        this.floor = floor;
        this.description = description;
        this.buildingName = buildingName;
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

    // REQUIRES: Given fountain is not null
    // EFFECTS: Prints out information about the given fountain
    public void PrintFountain(Fountain f) {
        System.out.println("Floor: " + f.getFloor());
        System.out.println("Building: " + f.getBuildingName());
        System.out.println("Type of Fountain: " + f.getType());
        System.out.println("Description of Location: " + f.getDescription() + "\n");
    }

    public abstract void howToFill();
}
