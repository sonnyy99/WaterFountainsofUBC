package ui.model.fileIO;

import java.io.FileNotFoundException;

public interface Loadable {
    void load(String s) throws FileNotFoundException;
}
