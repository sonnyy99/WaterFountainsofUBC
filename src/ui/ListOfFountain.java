package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class ListOfFountain {
    private ArrayList<Fountain> allFountains;

    public ListOfFountain() {
        allFountains = new ArrayList<>();
    }

    public void addFountain(int floor, String buildingName, String type, String description) {
        Fountain f = new Fountain(floor, buildingName, type, description);
        allFountains.add(f);
    }

    public static void main(String args[]) {
        ListOfFountain lof = new ListOfFountain();
        lof.addFountain(1, "The Nest", "Electronic", "By the corner store");

        String in = "";
        System.out.println("Type 'EXIT' when you would like to close the program.");

        while(!in.equals("EXIT")) {
            Scanner userOption = new Scanner(System.in);
            System.out.println("What would you like to do?");
            System.out.println("[1] Make a new entry for a water fountain");
            System.out.println("[2] Remove an entry from the system");
            System.out.println("[3] Print out a list of all current water fountains");

            in = userOption.nextLine();

            if (in.equals("1")) {
                Scanner userFloor = new Scanner(System.in);
                System.out.println("What floor is the water fountain on?");
                System.out.println("Ex: 1");
                String stringFloor = userFloor.nextLine();
                int floor = Integer.parseInt(stringFloor);

                Scanner userBuildingName = new Scanner(System.in);
                System.out.println("What building is in the water fountain in?");
                String buildingName = userBuildingName.nextLine();

                Scanner userType = new Scanner(System.in);
                System.out.println("What type of fountain is it? (Handle or Electronic)");
                String type = userType.nextLine();

                Scanner userDescription = new Scanner(System.in);
                System.out.println("Describe where the water fountain is (close to which classrooms)");
                String description = userDescription.nextLine();

                lof.addFountain(floor, buildingName, type, description);
            }

            if (in.equals("2")) {
                Scanner userRemove = new Scanner(System.in);
                System.out.println("Which entry would you like to remove? (To delete first entry type '1')");
                String stringFountainRemoved = userRemove.nextLine();
                int FountainRemoved = Integer.parseInt(stringFountainRemoved);
                lof.allFountains.remove(FountainRemoved - 1);
            }

            if(in.equals("3")) {
                for(Fountain f: lof.allFountains) {
                    System.out.println("Floor: " + f.getFloor());
                    System.out.println("Building: " + f.getBuildingName());
                    System.out.println("Type of Fountain: " + f.getType());
                    System.out.println("Description of Location: " + f.getDescription());
                }

            }

        }

    }
}
