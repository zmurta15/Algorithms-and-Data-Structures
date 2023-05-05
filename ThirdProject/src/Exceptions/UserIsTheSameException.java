package Exceptions;

/**^
 * The exception if the user asking for lift is the same.
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
@SuppressWarnings("serial")
public class UserIsTheSameException extends Exception {
	/**
	 * Checks if the user asking for lift is the same as the owner.
	 */
	public UserIsTheSameException() {
		super();
	}
}
