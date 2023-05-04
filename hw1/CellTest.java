package edu.iastate.cs228.hw1;

/**
 * @author nick pinnello
 *
 * use to extend in class creating new towns
 */
public abstract class CellTest {
    protected Town newTown() {
        return new Town(2, 2);
    }
}