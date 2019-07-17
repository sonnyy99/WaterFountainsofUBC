package tests;

import org.junit.Before;
import org.junit.Test;
import ui.model.ListOfFountain;

import static org.junit.Assert.assertEquals;

public class ListOfFountainTest {
    private ListOfFountain testLoF;

    @Before
    public void newListOfFountain ()
    {
        testLoF = new ListOfFountain();
    }

    @Test
    public void testAddFountain() {
        assertEquals(0, testLoF.allFountains.size());
        testLoF.AddFountain(2, "The Nest", "Electronic", "By Blue Chip");
        assertEquals(1, testLoF.allFountains.size());
    }

    @Test
    public void testAdd2Fountains() {
        assertEquals(0, testLoF.allFountains.size());
        testLoF.AddFountain(2, "The Nest", "Electronic", "By Blue Chip");
        assertEquals(1, testLoF.allFountains.size());
        testLoF.AddFountain(1, "The Nest", "Electronic", "By The Corner Store");
        assertEquals(2, testLoF.allFountains.size());
    }

    @Test
    public void testRemove(){
        testLoF.AddFountain(2, "The Nest", "Electronic", "By Blue Chip");
        testLoF.allFountains.remove(0);
        assertEquals(0, testLoF.allFountains.size());
    }

    @Test
    public void testRemoveComplex1(){
        testLoF.AddFountain(2, "The Nest", "Electronic", "By Blue Chip");
        testLoF.AddFountain(1, "The Nest", "Electronic", "By The Corner Store");
        testLoF.allFountains.remove(1);
        assertEquals(1, testLoF.allFountains.size());
        testLoF.allFountains.remove(0);
        assertEquals(0, testLoF.allFountains.size());
    }

    @Test
    public void testRemoveComplex2(){
        testLoF.AddFountain(2, "The Nest", "Electronic", "By Blue Chip");
        testLoF.AddFountain(1, "The Nest", "Electronic", "By The Corner Store");
        testLoF.AddFountain(1, "SRC", "Electronic", "Inside the Birdcoop");
        testLoF.allFountains.remove(1);
        assertEquals(2, testLoF.allFountains.size());
        testLoF.allFountains.remove(1);
        assertEquals(1, testLoF.allFountains.size());
        testLoF.allFountains.remove(0);
        assertEquals(0, testLoF.allFountains.size());
    }

}

