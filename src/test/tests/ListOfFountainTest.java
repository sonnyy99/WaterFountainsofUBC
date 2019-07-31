package tests;

import main.model.ListOfFountain;
import main.model.exceptions.FountainTypeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ListOfFountainTest {
    private ListOfFountain testLoF;

    @Before
    public void setup() {
        testLoF = new ListOfFountain();
    }

    @Test
    public void testAddFountain() throws FountainTypeException {
        assertEquals(0, ListOfFountain.allFountains.size());
        testLoF.AddFountain(2, "The Nest", "Electronic", "By Blue Chip");
        assertEquals(1, ListOfFountain.allFountains.size());
    }

    @Test
    public void testAdd2Fountains() throws FountainTypeException {
        assertEquals(0, ListOfFountain.allFountains.size());
        testLoF.AddFountain(2, "The Nest", "Electronic", "By Blue Chip");
        assertEquals(1, ListOfFountain.allFountains.size());
        testLoF.AddFountain(1, "The Nest", "Mechanical", "By The Corner Store");
        assertEquals(2, ListOfFountain.allFountains.size());
    }

    @Test
    public void testRemove() throws FountainTypeException {
        testLoF.AddFountain(2, "The Nest", "Electronic", "By Blue Chip");
        ListOfFountain.allFountains.remove(0);
        assertEquals(0, ListOfFountain.allFountains.size());
    }

    @Test
    public void testRemoveComplex1() throws FountainTypeException {
        testLoF.AddFountain(2, "The Nest", "Electronic", "By Blue Chip");
        testLoF.AddFountain(1, "The Nest", "Electronic", "By The Corner Store");
        ListOfFountain.allFountains.remove(1);
        assertEquals(1, ListOfFountain.allFountains.size());
        ListOfFountain.allFountains.remove(0);
        assertEquals(0, ListOfFountain.allFountains.size());
    }

    @Test
    public void testRemoveComplex2() throws FountainTypeException {
        testLoF.AddFountain(2, "The Nest", "Mechanical", "By Blue Chip");
        testLoF.AddFountain(1, "The Nest", "Electronic", "By The Corner Store");
        testLoF.AddFountain(1, "SRC", "Electronic", "Inside the Birdcoop");
        ListOfFountain.allFountains.remove(1);
        assertEquals(2, ListOfFountain.allFountains.size());
        ListOfFountain.allFountains.remove(1);
        assertEquals(1, ListOfFountain.allFountains.size());
        ListOfFountain.allFountains.remove(0);
        assertEquals(0, ListOfFountain.allFountains.size());
    }

}

