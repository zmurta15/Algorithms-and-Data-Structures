package Exceptions;

/**
 * The exception that checks if there isn't a logged user
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
@SuppressWarnings("serial")
public class NoLoggedUserException extends Exception {
	/**
	 * Checks if there isn't a loged user.
	 */
	public NoLoggedUserException() {
		super();
	}
}
