package ui.model;

public class MechanicalFountain extends Fountain {

    public MechanicalFountain(int floor, String buildingName, String type, String description) {
        super(type, floor, description, buildingName);
    }

    @Override
    // REQUIRES: Given fountain is not null
    // EFFECTS: Prints out information about the given fountain
    public void PrintFountain(Fountain f) {
        System.out.println("Floor: " + f.getFloor());
        System.out.println("Building: " + f.getBuildingName());
        System.out.println("Type of Fountain: Mechanical");
        System.out.println("Description of Location: " + f.getDescription() + "\n");
    }

    @Override
    public void howToFill() {
        System.out.println("Hold down the lever/button manually");
    }

}
