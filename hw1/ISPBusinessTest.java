package edu.iastate.cs228.hw1;

import static edu.iastate.cs228.hw1.ISPBusiness.getProfit;
import static edu.iastate.cs228.hw1.ISPBusiness.updatePlain;
/**
 * Adds usage of assertEquals method
 */
import static org.junit.jupiter.api.Assertions.*;
/**
 * enables @Test cases
 */
import org.junit.jupiter.api.Test;


/**
 * 
 * @author nick pinnello
 * tests ISPBusiness
 */
	class ISPBusinessTest {
	    @Test
	    void Test() {
	        Town t1 = new Town(4, 4);
	        t1.randomInit(10);
	        assertEquals("E E E E \n"
	        		+ "C C O E \n"
	        		+ "C O E O \n"
	        		+ "C E E E \n", updatePlain(t1).toString());
	    }

	    @Test
	    void Profit() {
	        Town t = new Town(3, 3);
	        t.randomInit(42);
	        assertEquals(3, getProfit(t));
	    }
	}