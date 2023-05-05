package User;

import Move.*;
import dataStructures.*;
import DateAndTime.*;

/**
 * The interface that represents an user of the app
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
public interface User {
	
	/**
	 * Adds the move to the user in a specific date
	 * @param date - the specific date when the move occurs
	 * @param move - the specific move that the user adds
	 */
	void addMove(Date date, Move move);
	
	/**
	 * Adds the lift to the user in a specific date
	 * @param date - the specific date when the lift occurs
	 * @param lift - the specific lift that the user adds
	 */
	void addLift(Date date, Move lift);
	
	/**
	 * Gets the user's email.
	 * @return user's email.
	 */
	String getEmail();
	
	/**
	 * Gets the user's name
	 * @return user's name
	 */
	String getName();
	
	/**
	 * Gets the user's password
	 * @return user's password
	 */
	String getPassword();
	
	/**
	 * Increments the number of visits of the user
	 */
	void incNVisits();
	
	/**
	 * Gets the user's number of visits to the app
	 * @return - the user's number of visits to the app
	 */
	int getNVisits();
	
	/**
	 * Checks if there the user has already a move in the date
	 * @param date - the date passed as a string
	 * @return true, if the user has a move in the date; false, otherwise
	 */
	boolean hasMoveOrLiftInDate(Date date);
	
	/**
	 * Gets the number of the moves of the user
	 * @return the number of moves of the user
	 */
	int getNMoves();
	
	/**
	 * Checks if the user has a move in date
	 * @param date - date of the move
	 * @return true, if the user as move in date, false otherwise
	 */
	boolean hasMoveInDate(Date date);
	/**
	 * Checks if the user has lift with date
	 * @param date - date of the lift
	 * @return true, if has a lift with date, false, otherwise
	 */
	boolean hasLiftInDate (Date date);
	
	/**
	 * Remove lift from the user
	 * @param date - date of the lift
	 */
	void removeLift(Date date);
	
	/**
	 * Check if there are people in the move with date of the user
	 * @param date - date of the move
	 * @return true, if has already users, false otherwise
	 */
	boolean hasAlreadyUserInCar(Date date);
	
	/**
	 * Removes a move of the user.
	 * @param date - date of the move
	 */
	void removeMove(Date date);
	/**
	 * Gets the move with date
	 * @param date - date of the moves
	 * @return the move with date date
	 */
	Move getMoveWithDate(Date date);
	
	/**
	 * Iterator for all the moves of the user
	 * @return a iterator of entries for all the moves with date
	 */
	Iterator<Entry<Date, Move>> iteratorMoves();
	
	/**
	 * Iterator for all the lifts that the user is in
	 * @return a iterator of entries for all the entries that the user is in with date
	 */
	Iterator<Entry<Date, Move>> iteratorLifts();
	
	/**
	 * Checks if the user is at least on one lift
	 * @return true, if the user is in, false otherwise
	 */
	boolean hasLifts();
	/**
	 * Checks if the user has at least one move
	 * @return true, if the user has one at least, false otherwise. 
	 */
	boolean hasMoves();
}
