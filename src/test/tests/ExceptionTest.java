package tests;

import main.model.ListOfFountain;
import main.model.exceptions.FountainTypeException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
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
        try {
            testLoF.AddFountain(2, "The Nest", "Electronic", "By Blue Chip");
        } catch (FountainTypeException e) {
            fail("FountainTypeException should not have been thrown");
        }

        try {
            testLoF.AddFountain(1, "The Birdcoop", "Mechanical", "By the staircase");
        } catch (FountainTypeException e) {
            fail("FountainTypeException should not have been thrown");
        }

        assertEquals(2, ListOfFountain.allFountains.size());

        try {
            testLoF.AddFountain(3, "The Sub", "Bad", "By the washrooms");
            fail("Should've thrown FountainTypeException");
        }
        catch (FountainTypeException e) {
            System.out.println("All tests passed");
        }
    }

}

