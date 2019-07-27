package tests;

import main.model.ListOfFountain;
import main.model.exceptions.FountainTypeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ExceptionTest {

    private ListOfFountain testLoF;

    @Before
    public void newListOfFountain ()
    {
        testLoF = new ListOfFountain();
    }

    @Test
    public void testAddFountain() throws FountainTypeException {
        testLoF.AddFountain(2, "The Nest", "Electronic", "By Blue Chip");
        testLoF.AddFountain(1, "The Birdcoop", "Mechanical", "By the staircase");
        assertEquals(2, testLoF.allFountains.size());
        // testLoF.AddFountain(3, "The Sub", "Bad", "By the washrooms");
    }

}

