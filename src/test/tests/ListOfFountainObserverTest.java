package tests;

import model.ListOfFountain;
import model.ListOfFountainObserver;
import model.FountainTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.ListOfFountain.allFountains;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfFountainObserverTest {
    private ListOfFountainObserver testlofo;
    private ListOfFountain testlof;

    @BeforeEach
    void setup() {
        testlof = new ListOfFountain();
        testlofo = new ListOfFountainObserver();
    }

    @Test
    public void testUpdate() throws FountainTypeException {
        testlof.addObserver(testlofo);
        testlof.addFountain(5, "The Nest", "Mechanical", "I don't know");
        assertEquals(1, testlofo.getUpdated());
        testlof.addFountain(7, "Buchanan Tower", "Mechanical", "Who knows");
        assertEquals(2, testlofo.getUpdated());
        testlof.removeFountain(allFountains.get(0));
        assertEquals(3, testlofo.getUpdated());
    }


}
