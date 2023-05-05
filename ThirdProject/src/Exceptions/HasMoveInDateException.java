package Exceptions;

@SuppressWarnings("serial")
/**
 * The exception that checks if a user has already a move in a specific date
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
public class HasMoveInDateException extends Exception{
	
	/**
	 * Checks if the user has a move in the date
	 */
	public HasMoveInDateException() {
		super();
	}

}
