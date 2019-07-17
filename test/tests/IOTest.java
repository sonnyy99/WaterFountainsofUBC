package tests;

import org.junit.Before;
import org.junit.Test;
import ui.FountainLocations;
import ui.model.ListOfFountain;
import ui.model.Loadable;
import ui.model.Saveable;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class IOTest {
    private FountainLocations testFL;
    private ListOfFountain testLOF;

    @Before
    public void startup() throws IOException {
        testFL = new FountainLocations();
        testLOF = new ListOfFountain();
    }

    @Test
    public void testLoadAll() throws FileNotFoundException {
        testLoad(testFL);
        assertEquals(3, ListOfFountain.allFountains.get(0).getFloor());
        assertEquals("a", ListOfFountain.allFountains.get(0).getBuildingName());
        assertEquals("b", ListOfFountain.allFountains.get(0).getType());
        assertEquals("c", ListOfFountain.allFountains.get(0).getDescription());
    }

    public void testLoad(Loadable testFL) throws FileNotFoundException {
        testFL.load();
    }

    @Test
    public void testSaveAll() throws IOException {
        testLOF.AddFountain(2, "The Nest", "Electronic", "By Blue Chip");
        testSave(testFL);
    }

    public void testSave(Saveable testFL) throws IOException {
        testFL.save();
    }
}