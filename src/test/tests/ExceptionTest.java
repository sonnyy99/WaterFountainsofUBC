package tests;

import model.ListOfFountain;
import model.exceptions.FountainTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class ExceptionTest {

    private ListOfFountain testLoF;

    @BeforeEach
    void newListOfFountain()
    {
        testLoF = new ListOfFountain();
    }

    @Test
    void testAddFountain() throws FountainTypeException {
        try {
            testLoF.addFountain(2, "The Nest", "Electronic", "By Blue Chip");
        } catch (FountainTypeException e) {
            fail("FountainTypeException should not have been thrown");
        }

        try {
            testLoF.addFountain(1, "The Birdcoop", "Mechanical", "By the staircase");
        } catch (FountainTypeException e) {
            fail("FountainTypeException should not have been thrown");
        }

        assertEquals(2, ListOfFountain.allFountains.size());

        try {
            throw new FountainTypeException();
        } catch (FountainTypeException e) {
            System.out.println("Test throwing with no message");
        }

        try {
            testLoF.addFountain(3, "The Sub", "Bad", "By the washrooms");
            fail("Should've thrown FountainTypeException");
        }
        catch (FountainTypeException e) {
            System.out.println("All tests passed");
        }
    }

}

