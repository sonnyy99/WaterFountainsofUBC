package ui;

public class Fountain {
    private int floor;
    private String buildingName;
    private String type;
    private String description;

    public Fountain(int floor, String buildingName, String type, String description) {
        this.floor = floor;
        this.buildingName = buildingName;
        this.type = type;
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

}
