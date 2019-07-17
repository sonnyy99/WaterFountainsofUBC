package ui.model;

public class ElectronicFountain extends Fountain {

    public ElectronicFountain(int floor, String buildingName, String type, String description) {
        super(type, floor, description, buildingName);
    }

    @Override
    // REQUIRES: Given fountain is not null
    // EFFECTS: Prints out information about the given fountain
    public void PrintFountain(Fountain f) {
        System.out.println("Floor: " + f.getFloor());
        System.out.println("Building: " + f.getBuildingName());
        System.out.println("Type of Fountain: Electronic");
        System.out.println("Description of Location: " + f.getDescription() + "\n");
    }
    @Override
    public void howToFill() {
        System.out.println("Place bottle in front of sensor");
    }
}
