package edu.iastate.cs228.hw1;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author nick pinnello
 *
 * Tests TownCell
 */
class TownCellTest {
	@Test
    void Census() throws FileNotFoundException {
        Town t = new Town("TownTest.txt");
        int[] nCensus = new int[5];
        t.grid[1][1].census(nCensus);

        assertAll(() -> assertEquals(1, nCensus[0]),
                  () -> assertEquals(2, nCensus[1]),
                () -> assertEquals(3, nCensus[2]),
                () -> assertEquals(1, nCensus[3]),
                () -> assertEquals(1, nCensus[4])
        );
    }
}

