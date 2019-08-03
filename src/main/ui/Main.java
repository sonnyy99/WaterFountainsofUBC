package ui;


import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FountainLocations fl = new FountainLocations();
        ReadWebPage.read();
        fl.run();
    }
}
