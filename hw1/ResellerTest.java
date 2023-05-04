package edu.iastate.cs228.hw1;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author nick pinnello
 *
 * Tests Reseller
 */
class ResellerTest extends CellTest{
    @Test
    void who() {
        TownCell R = new Reseller(newTown(), 1, 1);
        assertEquals(State.RESELLER, R.who());
    }

    @Test
    void casual() throws FileNotFoundException {
        Town t = new Town("ResellerTest.txt");
        assertTrue(t.grid[1][1].next(newTown()) instanceof Empty);
    }

    @Test
    void empty() throws FileNotFoundException {
        Town t = new Town("ResellerTest.txt");
        t.grid[0][2] = new Casual(t, 0, 2);
        for (int i = 0; i < 3; i++) t.grid[2][i] = new Empty(t, 2, i);
        assertTrue(t.grid[1][1].next(newTown()) instanceof Empty);
    }


}