package Boleia;

/**
 * The interface that represents the date
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
public interface Date extends Comparable<Date>{
	
	/**
	 * Checks if the date is valid.
	 * @return true, if the date is valid, false otherwise
	 */
	boolean isValid();
	
	/**
	 * Gets the year of the date
	 * @return the year of the date
	 */
	int getYear();
	/**
	 * Gets the day of the date
	 * @return the day of the date
	 */
	int getDay();
	
	/**
	 * Gets the month of the date
	 * @return the month of the date
	 */
	int getMonth();
	
	/**
	 * Get all the date in string
	 * @return the complete date
	 */
	String getDate();
}
