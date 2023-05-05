package DateAndTime;

/**
 * The interface that represents the time, with hours and minutes
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
public interface HourMinutes {
	
	/**
	 * Gets the hour at the time
	 * @return the hour
	 */
	int getHour();
	
	/**
	 * Gets the minutes at the time
	 * @return the minutes
	 */
	int getMinutes();
	
	/**
	 * Checks if a time with hour and minutes is valid
	 * @return true, if the hour is valid; false, otherwise
	 */
	boolean isValid();
	
	/**
	 * Gets the time of the move
	 * @return the time of the move
	 */
	String getTime();

}
