package main.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.model.Building;
import main.model.Fountain;
import main.model.ListOfFountain;
import main.model.exceptions.FountainTypeException;
import main.model.fileIO.Loadable;
import main.model.fileIO.Saveable;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static main.model.ListOfBuilding.allBuildings;
import static main.model.ListOfBuilding.getBuilding;
import static main.model.ListOfBuilding.loadAllBuildings;
import static main.model.ListOfFountain.allFountains;

public class FountainLocations implements Loadable, Saveable {
    public void run() throws IOException {
        final String fileFountains = "fountains.json";
        final String fileBuildings = "buildings.json";
        ListOfFountain lof = new ListOfFountain();

        try {
            loadBuilding(fileBuildings);
        } catch (IOException io) {
            System.out.println("Error: A file named " + fileBuildings + " could not be loaded");
            System.out.println("Creating a new file named " + fileBuildings + "\n");
            saveBuilding(fileBuildings);
        }

        try {
            loadFountain(fileFountains);
        } catch (IOException io) {
            System.out.println("Error: A file named " + fileFountains + " could not be loaded");
            System.out.println("Creating a new file named " + fileFountains + "\n");
            saveFountain(fileFountains);
        } finally {
            System.out.println("Type 'EXIT' when you would like to close the program.");
        }

        loadAllBuildings();
        chooseOptions(lof);
        saveFountain(fileFountains);
        saveBuilding(fileBuildings);
    }

    private void chooseOptions(ListOfFountain lof) {
        String in = "";

        while (!in.equals("EXIT")) {
            Scanner userOption = new Scanner(System.in);
            options();
            in = userOption.nextLine();

            if (in.equals("1")) {
                newFountain(lof);
            }

            if (in.equals("2")) {
                removeFountain(lof);
            }

            if (in.equals("3")) {
                printFountains(allFountains);
            }

            if (in.equals("4")) {
                printFountainsInBuilding(lof);
            }
        }
    }

    private void removeFountain(ListOfFountain lof) {
        isListEmpty(lof);

        Scanner userRemove = new Scanner(System.in);
        System.out.println("Which entry would you like to remove?");
        System.out.println("To delete first entry type '1'");
        System.out.println("To cancel type 'CANCEL'");

        String stringFountainRemoved = userRemove.nextLine();

        if (stringFountainRemoved.equals("CANCEL")) {
            chooseOptions(lof);
        } else {
            try {
                int fountainRemoved = Integer.parseInt(stringFountainRemoved);

                if (fountainRemoved < 0) {
                 System.out.println("Error: Integer must be positive. Please try again.");
                    removeFountain(lof);
                } else {
                    try {
                        allFountains.remove(fountainRemoved - 1);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error: Fountain does not exist at this "
                                + "index. Please enter a valid index.");
                        removeFountain(lof);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Not a valid integer. Please try again.");
                removeFountain(lof);
            }
        }
    }

    private void isListEmpty(ListOfFountain lof) {
        if (allFountains.size() == 0) {
            System.out.println("The list of fountains is already empty. "
                    + "Please select a different option.");
            chooseOptions(lof);
        }
    }

    private void newFountain(ListOfFountain lof) {
        Scanner userFloor = new Scanner(System.in);
        System.out.println("What floor is the water fountain on?");
        System.out.println("Ex: 1");
        String stringFloor = userFloor.nextLine();

        try {
            int floor = Integer.parseInt(stringFloor);

            if (floor < 0) {
                System.out.println("Error: Integer must be positive. Please try again.");
                newFountain(lof);
            } else {
                Scanner userBuildingName = new Scanner(System.in);
                System.out.println("What building is in the water fountain in?");
                String buildingName = userBuildingName.nextLine();

                Scanner userType = new Scanner(System.in);
                System.out.println("What type of fountain is it? "
                        + "(Mechanical or Electronic)");
                String type = userType.nextLine();

                Scanner userDescription = new Scanner(System.in);
                System.out.println("Describe where the water fountain is "
                        + "(close to which classrooms)");
                String description = userDescription.nextLine();

                tryAddFountain(lof, floor, buildingName, type, description);
            }
        } catch (NumberFormatException e) {
                System.out.println("Error: Not a valid integer. Please try again.");
                newFountain(lof);
        }
    }

    private void tryAddFountain(ListOfFountain lof, int floor, String buildingName, String type, String description) {
        try {
            lof.AddFountain(floor, buildingName, type, description);
        } catch (FountainTypeException e) {
            System.out.println("Error: Type must be Mechanical or "
                    + "Electronic. Please try again. \n");
            newFountain(lof);
        }
    }

    public void printFountainsInBuilding(ListOfFountain lof) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Which building would you like to know the fountains of?");
        String buildingName = userInput.nextLine();

        Building b = getBuilding(buildingName);

        try {
            printFountains(b.getFountains());
        } catch (NullPointerException e) {
            System.out.println("Error: This building does not exist or does not have fountains.");
            System.out.println("Returning to the main menu. \n");
        }
    }

    private void options() {
        System.out.println("What would you like to do?");
        System.out.println("[1] Make a new entry for a water fountain");
        System.out.println("[2] Remove an entry from the system");
        System.out.println("[3] Print out a list of all current water fountains");
        System.out.println("[4] Print out all fountains in a selected building");
    }

    // Got some code from here: https://stackoverflow.com/questions/
    // 29319434/how-to-saveFountain-data-with-gson-in-a-json-file
    public void saveFountain(String s) throws IOException {
        try (Writer writer = new FileWriter(s)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(allFountains, writer);
        }
    }

    public void saveBuilding(String s) throws IOException {
        try (Writer writer = new FileWriter(s)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(allBuildings, writer);
        }
    }

    // Got some code from here: https://stackoverflow.com/questions/
    // 29965764/how-to-parse-json-file-with-gson
    public void loadFountain(String s) throws FileNotFoundException {
        String path = s;
        BufferedReader br = new BufferedReader(new FileReader(path));
        Gson gson = new Gson();

        // Got this from here: https://stackoverflow.com/questions/
        // 27014417/how-to-use-gson-to-convert-json-to-arraylist-if-
        // the-list-contain-different-class
        TypeToken<ArrayList<Fountain>> token = new TypeToken<ArrayList<Fountain>>() {};
        allFountains = gson.fromJson(br, token.getType());
    }

    public void loadBuilding(String s) throws FileNotFoundException {
        String path = s;
        BufferedReader br = new BufferedReader(new FileReader(path));
        Gson gson = new Gson();

        TypeToken<ArrayList<Building>> token = new TypeToken<ArrayList<Building>>() {};
        allBuildings = gson.fromJson(br, token.getType());
    }

    // REQUIRES: List of fountains is not null
    // EFFECTS: Prints out information about every fountain in the given list
    public void printFountains(ArrayList<Fountain> fountains) {
        for (Fountain f : fountains) {
            printFountain(f);
        }
    }

    // EFFECTS: Prints out information about the given fountain
    public void printFountain(Fountain f) {
        System.out.println("Floor: " + f.getFloor());
        System.out.println("Building: " + f.getBuildingName());
        System.out.println("Type of Fountain: " + f.getType());
        System.out.println("Description of Location: " + f.getDescription() + "\n");
    }
}









