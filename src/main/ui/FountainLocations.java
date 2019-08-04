package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Building;
import model.Fountain;
import model.ListOfFountain;
import model.ListOfFountainObserver;
import model.exceptions.FountainTypeException;
import model.fileio.Loadable;
import model.fileio.Saveable;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static model.ListOfBuilding.*;
import static model.ListOfFountain.allFountains;
import static model.ListOfFountain.reloadAllFountains;

public class FountainLocations implements Loadable, Saveable {
    public void run() throws IOException {
        final String fileFountains = "fountains.json";
        final String fileBuildings = "buildings.json";
        ListOfFountain lof = new ListOfFountain();
        ListOfFountainObserver lofo = new ListOfFountainObserver();
        lof.addObserver(lofo);

        tryLoadBuildings(fileBuildings);
        tryLoadFountains(fileFountains);

        reloadAllBuildings();
        reloadAllFountains();

        //System.out.println("Type 'EXIT' when you would like to close the program.");
        //chooseOptions(lof);
        //saveFountain(fileFountains);
        //saveBuilding(fileBuildings);
    }

    private void tryLoadFountains(String fileFountains) throws IOException {
        try {
            loadFountains(fileFountains);
        } catch (IOException io) {
            System.out.println("Error: A file named " + fileFountains + " could not be loaded");
            System.out.println("Creating a new file named " + fileFountains + "\n");
            saveFountain(fileFountains);
        }
    }

    private void tryLoadBuildings(String fileBuildings) throws IOException {
        try {
            loadBuildings(fileBuildings);
        } catch (IOException io) {
            System.out.println("Error: A file named " + fileBuildings + " could not be loaded");
            System.out.println("Creating a new file named " + fileBuildings + "\n");
            saveBuilding(fileBuildings);
        }
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
        String stringFountainRemoved = getRemoved();

        if (stringFountainRemoved.equals("CANCEL")) {
            chooseOptions(lof);
        } else {
            try {
                int fountainRemoved = Integer.parseInt(stringFountainRemoved);

                if (fountainRemoved < 0) {
                    System.out.println("Error: Integer must be positive. Please try again.");
                    removeFountain(lof);
                } else {
                    tryRemoveFountain(lof, fountainRemoved);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Not a valid integer. Please try again.");
                removeFountain(lof);
            }
        }
    }

    private String getRemoved() {
        Scanner userRemove = new Scanner(System.in);
        System.out.println("Which entry would you like to remove?");
        System.out.println("To delete first entry type '1'");
        System.out.println("To cancel type 'CANCEL'");

        return userRemove.nextLine();
    }

    private void tryRemoveFountain(ListOfFountain lof, int fountainRemoved) {
        try {
            Fountain f = allFountains.get(fountainRemoved - 1);
            Building b = f.getBuilding();
            b.getFountains().remove(f);
            lof.removeFountain(f);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Fountain does not exist at this "
                    + "index. Please enter a valid index.");
            removeFountain(lof);
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
        String stringFloor = getFloor();

        try {
            int floor = Integer.parseInt(stringFloor);

            if (floor < 0) {
                System.out.println("Error: Integer must be positive. Please try again.");
                newFountain(lof);
            } else {
                String buildingName = getBuildingName();
                String type = getFountainType();
                String description = getDescription();
                tryAddFountain(lof, floor, buildingName, type, description);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Not a valid integer. Please try again.");
            newFountain(lof);
        }
    }

    private String getFloor() {
        Scanner userFloor = new Scanner(System.in);
        System.out.println("What floor is the water fountain on?");
        System.out.println("Ex: 1");
        return userFloor.nextLine();
    }

    private String getDescription() {
        Scanner userDescription = new Scanner(System.in);
        System.out.println("Describe where the water fountain is "
                + "(close to which classrooms)");
        return userDescription.nextLine();
    }

    private String getFountainType() {
        Scanner userType = new Scanner(System.in);
        System.out.println("What type of fountain is it? "
                + "(Mechanical or Electronic)");
        return userType.nextLine();
    }

    private String getBuildingName() {
        Scanner userBuildingName = new Scanner(System.in);
        System.out.println("What building is in the water fountain in?");
        return userBuildingName.nextLine();
    }

    private void tryAddFountain(ListOfFountain lof, int floor, String buildingName, String type, String description) {
        try {
            lof.addFountain(floor, buildingName, type, description);
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
    public void loadFountains(String s) throws FileNotFoundException {
        String path = s;
        BufferedReader br = new BufferedReader(new FileReader(path));
        Gson gson = new Gson();

        // Got this from here: https://stackoverflow.com/questions/
        // 27014417/how-to-use-gson-to-convert-json-to-arraylist-if-
        // the-list-contain-different-class
        TypeToken<ArrayList<Fountain>> token = new TypeToken<ArrayList<Fountain>>() {};
        allFountains = gson.fromJson(br, token.getType());
    }

    public void loadBuildings(String s) throws FileNotFoundException {
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









