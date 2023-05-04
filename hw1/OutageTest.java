package edu.iastate.cs228.hw1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author nickpinnello
 *
 * Tests Outage
 */
class OutageTest extends CellTest{
    @Test
    void Outage() {
        TownCell O = new Outage(newTown(), 0 , 0);
        assertEquals(State.OUTAGE, O.who());
    }

//    @Test
//    void next() {
//        TownCell O = new Empty;
//    }
}
