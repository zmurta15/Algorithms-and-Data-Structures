package Exceptions;

/**
 * The exception that checks if there is already a user with the email in the app.
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
@SuppressWarnings("serial")
public class HasAlreadyUserException extends Exception {
	/**
	 * Check if the user already exists.
	 */
	public HasAlreadyUserException() {
		super();
	}

}
