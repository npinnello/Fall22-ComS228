package edu.iastate.cs228.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

/**
 * @author nick pinnello
 *
 * Tests Town
 */
class TownTest {
    @Test
    void randomInit() {
        Town t = new Town(4, 4);
        t.randomInit(10);
        assertEquals(t.toString(),
                        "O R O R \n" +
                        "E E C O \n" +
                        "E S O S \n" +
                        "E O R R \n");
    }

    @Test
    void Construct() throws FileNotFoundException {
        Town t = new Town("TownTest.txt");
        assertEquals("E O S \n"
        		+ "   E R R \n"
        		+ "   C C C \n", t.toString());
    }

    @Test
    void Dimensions() {
        Town t = new Town(10,100);
        assertAll(() -> assertEquals(t.getLength(), 10),
                  () -> assertEquals(t.getWidth(), 100));

    }
}
