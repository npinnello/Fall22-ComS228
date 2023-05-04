package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author nick pinnello
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		
		for(TownCell cells[] : tOld.grid) {
			for (TownCell cell : cells) {
				tNew.grid[cell.row][cell.col] = cell.next(tNew);
			}
		}
		
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		int profit = 0;
		for(TownCell cells[] : town.grid) {
			for (TownCell cell : cells) {
				if (cell.who() == State.CASUAL) profit++;
			}
		}
				return profit;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		Town town = null;
		int profit = 0;

		
		try (Scanner s = new Scanner(System.in)) {
			while(true) {
				
				int input = s.nextInt();
				if (input == 1) {
					System.out.println("Starting grid:");
					try {
						town = new Town(s.next());
					} catch (Exception e) {
						System.out.println("Invalid file!\n");
						e.printStackTrace();
						System.exit(-1);
					}

					break;
				}
				else if (input == 2)
				{
					System.out.println("Please enter a random seed:");
					int seed = s.nextInt();
					System.out.println("Please enter a length");
					int length = s.nextInt();
					System.out.println("Please enter a width:");
					int width = s.nextInt();

					town = new Town(length, width);
					town.randomInit(seed);

					break;
				}
				
			}
		}
		profit += getProfit(town); 
		for (int cycle = 1; cycle <= 11; cycle++) { 
			town = updatePlain(town);
			profit += getProfit(town);

			
		}

	
		System.out.println((profit / (double)(town.getWidth() * town.getLength() * 12) * 100));
	}
}
