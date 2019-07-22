package ui.model;

public class Fountain {
    private int floor;
    private String buildingName;
    private String type;
    private String description;

    public Fountain(int floor, String type, String buildingName, String description) {
        this.floor = floor;
        this.type = type;
        this.buildingName = buildingName;
        this.description = description;
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

    // EFFECTS: Prints out information about the given fountain
    public void PrintFountain(Fountain f) {
            System.out.println("Floor: " + f.getFloor());
            System.out.println("Building: " + f.getBuildingName());
            System.out.println("Type of Fountain: " + f.getType());
            System.out.println("Description of Location: " + f.getDescription() + "\n");
        }
}
