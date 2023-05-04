package edu.iastate.cs228.hw1;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author nick pinnello
 *
 * Tests Empty
 */
class EmptyTest extends CellTest {
    @Test
    void who() throws FileNotFoundException {
        Town e = new Town("EmptyTest.txt");
        assertEquals(State.EMPTY, e.grid[1][1].who());
    }

    @Test
    void empty() throws FileNotFoundException {
        Town o = new Town("EmptyTest.txt");
        assertTrue(o.grid[1][1].next(newTown()) instanceof Reseller);
    }

    @Test
    void casual() throws FileNotFoundException {
        Town e = new Town("EmptyTest.txt");
        e.grid[1][0] = new Casual(e, 1, 0);
        assertTrue(e.grid[1][1].next(newTown()) instanceof Reseller);
    }
}