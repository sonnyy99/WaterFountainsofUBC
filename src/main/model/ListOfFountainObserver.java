package model;

import java.util.Observable;
import java.util.Observer;

public class ListOfFountainObserver implements Observer {
    private int updated = 0;

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("\nA fountain has been " + arg);
        updated += 1;
        System.out.println(updated + " fountain(s) have been added/removed during this session \n");
    }
}
