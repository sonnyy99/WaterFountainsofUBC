package model;


import java.io.FileNotFoundException;

public interface Loadable {
    void loadFountains(String s) throws FileNotFoundException;

    void loadBuildings(String s) throws FileNotFoundException;
}
