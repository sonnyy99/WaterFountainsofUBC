package ui;

import ui.model.exceptions.FountainTypeException;

import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException, FountainTypeException {
        FountainLocations fl = new FountainLocations();
        fl.run();
    }
}
