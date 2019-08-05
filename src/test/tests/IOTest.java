package tests;

import model.ListOfBuilding;
import model.ListOfFountain;
import model.exceptions.FountainTypeException;
import model.fileio.Loadable;
import model.fileio.Saveable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.FountainLocations;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class IOTest {
    private FountainLocations testFL;
    private ListOfFountain testLOF;
    private ListOfBuilding testLOB;

    @BeforeEach
    void startup() {
        testFL = new FountainLocations();
        testLOF = new ListOfFountain();
        testLOB = new ListOfBuilding();
    }

    @Test
    void testLoadAll() throws FileNotFoundException {
        testLoad(testFL);
        assertEquals(2, ListOfFountain.allFountains.get(0).getFloor());
        assertEquals("The Nest", ListOfFountain.allFountains.get(0).getBuildingName());
        assertEquals("Electronic", ListOfFountain.allFountains.get(0).getType());
        assertEquals("By Blue Chip", ListOfFountain.allFountains.get(0).getDescription());
        assertEquals("The Nest", ListOfFountain.allFountains.get(0).getBuilding().getName());
    }

    private void testLoad(Loadable testFL) throws FileNotFoundException {
        testFL.loadFountains("testFountain.json");
        testFL.loadBuildings("testBuilding.json");
    }

    @Test
    void testSaveAll() throws IOException, FountainTypeException {
        testLOF.addFountain(2, "The Nest", "Electronic", "By Blue Chip");
        testSave(testFL);
        testLoad(testFL);
        assertEquals(2, ListOfFountain.allFountains.get(0).getFloor());
        assertEquals("The Nest", ListOfFountain.allFountains.get(0).getBuildingName());
        assertEquals("Electronic", ListOfFountain.allFountains.get(0).getType());
        assertEquals("By Blue Chip", ListOfFountain.allFountains.get(0).getDescription());
    }

    private void testSave(Saveable testFL) throws IOException {
        testFL.saveFountain("testFountain.json");
        testFL.saveBuilding("testBuilding.json");
    }
}