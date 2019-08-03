package model.fileio;


import java.io.IOException;

public interface Saveable {
    void saveFountain(String s) throws IOException;

    void saveBuilding(String s) throws IOException;
}
