package main.ui;

import main.model.Fountain;
import main.model.exceptions.FountainTypeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String args[]) throws IOException, FountainTypeException {
        FountainLocations fl = new FountainLocations();
        fl.run();
    }
}
