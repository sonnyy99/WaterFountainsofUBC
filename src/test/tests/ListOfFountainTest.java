package tests;

import model.Building;
import model.Fountain;
import model.ListOfFountain;
import model.FountainTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.ListOfBuilding.allBuildings;
import static model.ListOfBuilding.reloadAllBuildings;
import static model.ListOfFountain.*;
import static org.junit.jupiter.api.Assertions.*;

class ListOfFountainTest {
    private ListOfFountain testLoF;

    @BeforeEach
    void setup() {
        testLoF = new ListOfFountain();
    }

    @Test
    void testAddFountain() throws FountainTypeException {
        assertEquals(0, allFountains.size());
        testLoF.addFountain(2, "The Nest", "Electronic", "By Blue Chip");
        assertEquals(1, allFountains.size());
        assertEquals(2, allFountains.get(0).getFloor());
        assertEquals("The Nest", allFountains.get(0).getBuildingName());
        assertEquals("Electronic", allFountains.get(0).getType());
        assertEquals("By Blue Chip", allFountains.get(0).getDescription());
    }

    @Test
    void testAdd2Fountains() throws FountainTypeException {
        assertEquals(0, allFountains.size());
        testLoF.addFountain(2, "The Nest", "Electronic", "By Blue Chip");
        assertEquals(1, allFountains.size());
        testLoF.addFountain(1, "The Nest", "Mechanical", "By The Corner Store");
        assertEquals(2, allFountains.size());
    }

    @Test
    void testFountainExists() throws FountainTypeException {
        Fountain f = new Fountain(2, "The Nest", "Electronic", "By Blue Chip");
        allFountains.add(f);
        assertEquals(1, allFountains.size());
        testLoF.addFountain(2,"The Nest", "Electronic", "By Blue Chip");
        assertEquals(1, allFountains.size());
    }


    @Test
    void testRemove() throws FountainTypeException {
        testLoF.addFountain(2, "The Nest", "Electronic", "By Blue Chip");
        allFountains.remove(0);
        assertEquals(0, allFountains.size());
    }

    @Test
    void testRemove2() throws FountainTypeException {
        Fountain f = new Fountain(2, "The Nest", "Electronic", "By Blue Chip");
        allFountains.add(f);
        assertEquals(1, allFountains.size());
        testLoF.removeFountain(f);
        assertEquals(0, allFountains.size());
    }

    @Test
    void testRemoveComplex1() throws FountainTypeException {
        testLoF.addFountain(2, "The Nest", "Electronic", "By Blue Chip");
        testLoF.addFountain(1, "The Nest", "Electronic", "By The Corner Store");
        allFountains.remove(1);
        assertEquals(1, allFountains.size());
        allFountains.remove(0);
        assertEquals(0, allFountains.size());
    }

    @Test
    void testRemoveComplex2() throws FountainTypeException {
        testLoF.addFountain(2, "The Nest", "Mechanical", "By Blue Chip");
        testLoF.addFountain(1, "The Nest", "Electronic", "By The Corner Store");
        testLoF.addFountain(1, "SRC", "Electronic", "Inside the Birdcoop");
        allFountains.remove(1);
        assertEquals(2, allFountains.size());
        allFountains.remove(1);
        assertEquals(1, allFountains.size());
        allFountains.remove(0);
        assertEquals(0, allFountains.size());
    }

    @Test
    void testSetBuilding() {
        Fountain f = new Fountain(2, "The Nest", "Electronic", "By Blue Chip");
        setBuilding("The Nest", f);
        assertTrue(f.getBuilding().getName().equals("The Nest"));
    }

    @Test
    void testReloadAllFountains() {
        Fountain f = new Fountain(2, "The Nest", "Electronic", "By Blue Chip");
        allFountains.add(f);
        reloadAllFountains();
        assertEquals("The Nest",allFountains.get(0).getBuilding().getName());
        reloadAllBuildings();
        assertEquals(f, allBuildings.get(0).getFountains().get(0));
    }

    @Test
    void testFountainEquals() {
        Fountain f = new Fountain(2, "The Nest", "Electronic", "By Blue Chip");
        Fountain g = new Fountain(2, "The Nest", "Electronic", "By The Washrooms");
        Fountain h = new Fountain(2, "The Nest", "Electronic", "By Blue Chip");
        Fountain j = null;
        Building b = new Building("The Krusty Krab");
        assertFalse(f.equals(g));
        assertTrue(f.equals(h));
        assertFalse(f.equals(j));
        assertFalse(f.equals(b));
    }

    @Test
    void testHashCode() {
        Fountain f = new Fountain(2, "The Nest", "Electronic", "By Blue Chip");
        Fountain g = new Fountain(2, "The Nest", "Electronic", "By Blue Chip");
        assertEquals(f.hashCode(), g.hashCode());
    }
}

