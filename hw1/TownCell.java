package edu.iastate.cs228.hw1;

/**
 * 
 * @author Nick Pinnello
 *	Also provide appropriate comments for this class
 *
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Checks all neigborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0; 

		for (int r = -1; r <= 1; r++) {
			if (row + r < 0 || row + r >= plain.grid.length) continue;
			for (int c = -1; c <= 1; c++) {
				if ((c == 0 && r == 0) || col + c < 0 || col + c >= plain.grid[0].length) continue;
				switch(plain.grid[row + r][col + c].who()) {
					case CASUAL:
						nCensus[CASUAL]++;
						break;
					case STREAMER:
						nCensus[STREAMER]++;
						break;
					case RESELLER:
						nCensus[RESELLER]++;
						break;
					case EMPTY:
						nCensus[EMPTY]++;
						break;
					case OUTAGE:
						nCensus[OUTAGE]++;
						break;
				}
			}
		}

	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
	/**
	 * 
	 * @param nCensus
	 * @return nCensus 
	 * boolean helper method to determine the value of nCensus and update the grid if it contains an Outage.
	 */
	protected boolean containsOutage(int nCensus[]) {
		return nCensus[EMPTY] + nCensus[OUTAGE] <= 1;
}	
	
	
}
