package Boleia;

import dataStructures.*;

/**
 * The interface that represents the move, which can be and deslocation or a lift
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
public interface Move {	
	
	/**
	 * Gets the move's origin
	 * @return the move origin
	 */
	String getOrigin();
	
	/**
	 * Gets the move's destination
	 * @return the move's destination
	 */
	String getDestination();
	
	/**
	 * Gets the move's date
	 * @return the move's date
	 */
	Date getDate();
	
	/**
	 * Gets the move's hours and minutes
	 * @return the move's hours and minutes
	 */
	HourMinutes getTime();
	
	/**
	 * Gets the move's duration
	 * @return the move's duration
	 */
	int getDuration();
	
	/**
	 * Gets the move's total number of seats
	 * @return the move's total number of seats
	 */
	int getTotalSeats();
	
	/**
	 * Gets the move's number of empty seats 
	 * @return he move's number of empty seats 
	 */
	int getEmptySeats();
	
	/**
	 * Gets the move's owner (who created the move)
	 * @return the move's owner
	 */
	User getOwner();
	
	/**
	 * Checks if there already users in the move
	 * @return true, if already has users in the car, false, otherwise
	 */
	boolean hasUsersInCar();
	
	/**
	 * Add users to this move
	 * @param u - user to be add to the move
	 */
	void addToMove(User u);
	
	/**
	 * Gets the number of people in the queue
	 * @return the number of people in queue
	 */
	int getInQueue();
	
	/**
	 * Remove a user from the move
	 * @param u - user to remove
	 */
	void removeUser(User u);
	
	/**
	 * Iterator to the users in the car
	 * @return a iterator to the users
	 */
	Iterator<User> initIt();
	
	/**
	 * Increase the number of empty seats
	 */
	void incEmptySeats();
}
