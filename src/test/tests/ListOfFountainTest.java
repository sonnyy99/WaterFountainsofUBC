package tests;

import model.ListOfFountain;
import model.exceptions.FountainTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfFountainTest {
    private ListOfFountain testLoF;

    @BeforeEach
    public void setup() {
        testLoF = new ListOfFountain();
    }

    @Test
    public void testAddFountain() throws FountainTypeException {
        assertEquals(0, ListOfFountain.allFountains.size());
        testLoF.addFountain(2, "The Nest", "Electronic", "By Blue Chip");
        assertEquals(1, ListOfFountain.allFountains.size());
    }

    @Test
    public void testAdd2Fountains() throws FountainTypeException {
        assertEquals(0, ListOfFountain.allFountains.size());
        testLoF.addFountain(2, "The Nest", "Electronic", "By Blue Chip");
        assertEquals(1, ListOfFountain.allFountains.size());
        testLoF.addFountain(1, "The Nest", "Mechanical", "By The Corner Store");
        assertEquals(2, ListOfFountain.allFountains.size());
    }

    @Test
    public void testRemove() throws FountainTypeException {
        testLoF.addFountain(2, "The Nest", "Electronic", "By Blue Chip");
        ListOfFountain.allFountains.remove(0);
        assertEquals(0, ListOfFountain.allFountains.size());
    }

    @Test
    public void testRemoveComplex1() throws FountainTypeException {
        testLoF.addFountain(2, "The Nest", "Electronic", "By Blue Chip");
        testLoF.addFountain(1, "The Nest", "Electronic", "By The Corner Store");
        ListOfFountain.allFountains.remove(1);
        assertEquals(1, ListOfFountain.allFountains.size());
        ListOfFountain.allFountains.remove(0);
        assertEquals(0, ListOfFountain.allFountains.size());
    }

    @Test
    public void testRemoveComplex2() throws FountainTypeException {
        testLoF.addFountain(2, "The Nest", "Mechanical", "By Blue Chip");
        testLoF.addFountain(1, "The Nest", "Electronic", "By The Corner Store");
        testLoF.addFountain(1, "SRC", "Electronic", "Inside the Birdcoop");
        ListOfFountain.allFountains.remove(1);
        assertEquals(2, ListOfFountain.allFountains.size());
        ListOfFountain.allFountains.remove(1);
        assertEquals(1, ListOfFountain.allFountains.size());
        ListOfFountain.allFountains.remove(0);
        assertEquals(0, ListOfFountain.allFountains.size());
    }

}

