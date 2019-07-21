package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ui.model.Fountain;
import ui.model.ListOfFountain;
import ui.model.fileIO.Loadable;
import ui.model.fileIO.Saveable;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static ui.model.ListOfFountain.allFountains;

public class FountainLocations implements Loadable, Saveable {
    public void run() throws IOException {
        final String fileName = "input.json";
        ListOfFountain lof = new ListOfFountain();

        try {
            load(fileName);
        }
        catch (IOException io) {
            System.out.println("Error: A file named " + fileName
                    + " could not be loaded");
            System.out.println("Creating a new file named " + fileName + "\n");
            save(fileName);
        }


        String in = "";
        System.out.println("Type 'EXIT' when you would like to close the program.");

        while(!in.equals("EXIT")) {
            Scanner userOption = new Scanner(System.in);
            options();
            in = userOption.nextLine();

            if (in.equals("1")) {
                NewFountain(lof);
            }

            if (in.equals("2")) {
                RemoveFountain(lof);
            }

            if(in.equals("3")) {
                lof.PrintFountains(lof);
            }
        }

        save(fileName);
    }

    private void RemoveFountain(ListOfFountain lof) {
        Scanner userRemove = new Scanner(System.in);
        System.out.println("Which entry would you like to remove? (To delete first entry type '1')");
        String stringFountainRemoved = userRemove.nextLine();
        int FountainRemoved = Integer.parseInt(stringFountainRemoved);

        allFountains.remove(FountainRemoved - 1);
    }

    private void NewFountain(ListOfFountain lof) {
        Scanner userFloor = new Scanner(System.in);
        System.out.println("What floor is the water fountain on?");
        System.out.println("Ex: 1");
        String stringFloor = userFloor.nextLine();
        int floor = Integer.parseInt(stringFloor);

        Scanner userBuildingName = new Scanner(System.in);
        System.out.println("What building is in the water fountain in?");
        String buildingName = userBuildingName.nextLine();

        Scanner userType = new Scanner(System.in);
        System.out.println("What type of fountain is it? (Mechanical or Electronic)");
        String type = userType.nextLine();

        Scanner userDescription = new Scanner(System.in);
        System.out.println("Describe where the water fountain is (close to which classrooms)");
        String description = userDescription.nextLine();

        lof.AddFountain(floor, buildingName, type, description);
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
        Gson gson = new Gson ();

        // Got this from here: https://stackoverflow.com/questions/
        // 27014417/how-to-use-gson-to-convert-json-to-arraylist-if-
        // the-list-contain-different-class
        TypeToken<ArrayList<Fountain>> token = new TypeToken<ArrayList<Fountain>>() {};
        allFountains = gson.fromJson(br, token.getType());
    }
}









