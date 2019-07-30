package main.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.model.Fountain;
import main.model.ListOfFountain;
import main.model.exceptions.FountainTypeException;
import main.model.fileIO.Loadable;
import main.model.fileIO.Saveable;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static main.model.ListOfFountain.allFountains;

public class FountainLocations implements Loadable, Saveable {
    public void run() throws IOException, FountainTypeException {
        final String fileName = "input.json";
        ListOfFountain lof = new ListOfFountain();

        try {
            load(fileName);
        } catch (IOException io) {
            System.out.println("Error: A file named " + fileName + " could not be loaded");
            System.out.println("Creating a new file named " + fileName + "\n");
            save(fileName);
        } finally {
            System.out.println("Type 'EXIT' when you would like to close the program.");
        }

        chooseOptions(lof);
        save(fileName);
    }

    private void chooseOptions(ListOfFountain lof) {
        String in = "";

        while (!in.equals("EXIT")) {
            Scanner userOption = new Scanner(System.in);
            options();
            in = userOption.nextLine();

            if (in.equals("1")) {
                NewFountain(lof);
            }

            if (in.equals("2")) {
                RemoveFountain(lof);
            }

            if (in.equals("3")) {
                lof.PrintFountains(lof);
            }
        }
    }

    private void RemoveFountain(ListOfFountain lof) {
        isListEmpty(lof);

        Scanner userRemove = new Scanner(System.in);
        System.out.println("Which entry would you like to remove? "
                + "(To delete first entry type '1')");
        String stringFountainRemoved = userRemove.nextLine();

        try {
            int FountainRemoved = Integer.parseInt(stringFountainRemoved);

            if (FountainRemoved < 0) {
                System.out.println("Error: Integer must be positive. Please try again.");
                RemoveFountain(lof);
            } else {
                try {
                    allFountains.remove(FountainRemoved - 1);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Fountain does not exist at this "
                            + "index. Please enter a valid index.");
                    RemoveFountain(lof);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Not a valid integer. Please try again.");
            RemoveFountain(lof);
        }
    }

    private void isListEmpty(ListOfFountain lof) {
        if (allFountains.size() == 0) {
            System.out.println("The list of fountains is already empty. "
                    + "Please select a different option.");
            chooseOptions(lof);
        }
    }

    private void NewFountain(ListOfFountain lof) {
        Scanner userFloor = new Scanner(System.in);
        System.out.println("What floor is the water fountain on?");
        System.out.println("Ex: 1");
        String stringFloor = userFloor.nextLine();

        try {
            int floor = Integer.parseInt(stringFloor);

            if (floor < 0) {
                System.out.println("Error: Integer must be positive. Please try again.");
                NewFountain(lof);
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

                try {
                    lof.AddFountain(floor, buildingName, type, description);
                } catch (FountainTypeException e) {
                    System.out.println("Error: Type must be Mechanical or "
                            + "Electronic. Please try again. \n");
                    NewFountain(lof);
                }
            }
        } catch (NumberFormatException e) {
                System.out.println("Error: Not a valid integer. Please try again.");
                NewFountain(lof);
        }
    }

    private void options() {
        System.out.println("What would you like to do?");
        System.out.println("[1] Make a new entry for a water fountain");
        System.out.println("[2] Remove an entry from the system");
        System.out.println("[3] Print out a list of all current water fountains");
    }

    // Got some code from here: https://stackoverflow.com/questions/
    // 29319434/how-to-save-data-with-gson-in-a-json-file
    public void save(String s) throws IOException {
        try (Writer writer = new FileWriter(s)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(allFountains, writer);
        }
    }

    // Got some code from here: https://stackoverflow.com/questions/
    // 29965764/how-to-parse-json-file-with-gson
    public void load(String s) throws FileNotFoundException {
        String path = s;
        BufferedReader br = new BufferedReader(new FileReader(path));
        Gson gson = new Gson();

        // Got this from here: https://stackoverflow.com/questions/
        // 27014417/how-to-use-gson-to-convert-json-to-arraylist-if-
        // the-list-contain-different-class
        TypeToken<ArrayList<Fountain>> token = new TypeToken<ArrayList<Fountain>>() {};
        allFountains = gson.fromJson(br, token.getType());
    }
}









