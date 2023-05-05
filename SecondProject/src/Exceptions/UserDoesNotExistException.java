package Exceptions;

/**
 * The exception that checks if the user exists.
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
@SuppressWarnings("serial")
public class UserDoesNotExistException extends Exception {
	/**
	 * Checks if the user exists.
	 */
	public UserDoesNotExistException() {
		super();
	}
}
