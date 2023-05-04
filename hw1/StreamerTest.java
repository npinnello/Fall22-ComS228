package edu.iastate.cs228.hw1;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author nick pinnello
 *
 * Tests Streamer
 */
class StreamerTest extends CellTest{
    @Test
    void who() {
        TownCell S = new Streamer(newTown(), 1, 1);
        assertEquals(State.STREAMER, S.who());
    }

    @Test
    void Empty() throws FileNotFoundException {
        Town t = new Town("StreamerTest.txt");
        t.grid[0][1] = new Casual(t, 0 , 1);
        assertTrue(t.grid[1][1].next(newTown()) instanceof Reseller);
    }

    @Test
    void Reseller() throws FileNotFoundException {
        Town t = new Town("StreamerTest.txt");
        assertTrue(t.grid[1][1].next(newTown()) instanceof Outage);
    }

    @Test
    void Outage() throws FileNotFoundException {
        Town t = new Town("StreamerTest.txt");
        t.grid[0][2] = new Casual(t, 0 , 2);
        assertTrue(t.grid[1][1].next(newTown()) instanceof Empty);
    }
}