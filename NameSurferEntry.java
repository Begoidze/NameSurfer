
/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears in the data
	 * file. Each line begins with the name, which is followed by integers
	 * giving the rank of that name for each decade.
	 */
	private String name = "";
	private int[] posPerYr = new int[NDECADES + 1];
	private String stpr = " ";
	private int i = 0;
	private String numberString = "";
	
	
	//Gives names and saves it.
	public NameSurferEntry(String line) {

		while (line.charAt(i) != stpr.charAt(0)) {

			name = name + line.charAt(i);
			i++;
		}
		
		posPerYrGiver( i,  line);
	}
	
	
	//Gives position and saves it.
	private void posPerYrGiver(int i, String line){
		for(int j = 0; j < NDECADES; j++){
			numberString = "";
			i++;
		
			while (line.charAt(i) != stpr.charAt(0)) {

				numberString = numberString + line.charAt(i);
				
				if(line.length() - 1 != i){
					i++;
				}else{
					break;
				}
				
			}
			int numberInt = Integer.parseInt(numberString);
			posPerYr[j] = numberInt;
		}
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular decade. The
	 * decade value is an integer indicating how many decades have passed since
	 * the first year in the database, which is given by the constant
	 * START_DECADE. If a name does not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		
		return posPerYr[decade];
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		return name + "" + Arrays.toString(posPerYr);
	}
	
}
