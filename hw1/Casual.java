package edu.iastate.cs228.hw1;


/**
 * 
 * @author nick pinnello
 *
 */
public class Casual extends TownCell {
    /**
     * Constructor for Casual
     *
     * @param p Town which the cell is in
     * @param r row
     * @param c column
     */
    public Casual(Town p, int r, int c) {
        super(p, r, c);
    }

    /**
     * @return Enum type of Casual
     */
    @Override
    public State who() {
        return State.CASUAL;
    }

    /**
     * Implements Casual
     * @param tNew: town of the next cycle
     * @return Cell type next cycle
     */
    @Override
    public TownCell next(Town tNew) {
        int nCensus[] = new int[5];
        this.census(nCensus);
        TownCell output = new Casual(tNew, row, col);

        if(containsOutage(nCensus)) {
            output = new Reseller(tNew, row, col);
        }
        else if (nCensus[RESELLER] > 0) {
            output = new Outage(tNew, row, col);
        }
        else if (nCensus[STREAMER] > 0) {
            output = new Streamer(tNew, row, col);
        }
        else if (nCensus[CASUAL] >= 5) {
            output = new Streamer(tNew, row, col);
        }

        return output;
    }
}
