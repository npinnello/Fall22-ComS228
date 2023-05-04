package edu.iastate.cs228.hw1;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

/**
 * 
 * @author nick pinnello
 *Test the grid for Casual class
 */
class CasualTest extends CellTest{

    @Test
    void who() {
        TownCell C = new Casual(new Town(1, 1), 0, 0);
        assertEquals(State.CASUAL, C.who());
    }

    @Test
    
    void casual1() throws FileNotFoundException {
        Town t = new Town("CasualTest.txt");
        System.out.println(t);
        assertTrue(t.grid[1][1].next(newTown()) instanceof Reseller);
    }

    @Test
    void reseller() throws FileNotFoundException {
        Town t = new Town("CasualTest.txt");
        t.grid[2][0] = new Reseller(newTown(), 2, 0);
        t.grid[0][0] = new Empty(newTown(), 2, 0);
        assertTrue(t.grid[1][1].next(newTown()) instanceof Outage);
    }

    @Test
    void streamer() throws FileNotFoundException {
        Town t = new Town("CasualTest.txt");
        t.grid[2][0] = new Streamer(newTown(), 2, 0);
        t.grid[0][0] = new Empty(newTown(), 2, 0);
        assertTrue(t.grid[1][1].next(newTown()) instanceof Streamer);
    }


    
}
