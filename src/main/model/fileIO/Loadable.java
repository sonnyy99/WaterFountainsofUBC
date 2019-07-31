package main.model.fileIO;

import java.io.FileNotFoundException;

public interface Loadable {
    void loadFountain(String s) throws FileNotFoundException;
    void loadBuilding(String s) throws FileNotFoundException;
}
