package ui.model;

import java.io.FileNotFoundException;

public interface Loadable {
    void load(String s) throws FileNotFoundException;
}
