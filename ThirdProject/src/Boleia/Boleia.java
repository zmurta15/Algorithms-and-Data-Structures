package Boleia;

import Exceptions.*;
import dataStructures.*;

/**
 * The interface that represents the application
 * 
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
public interface Boleia {
	
	/**
	 * Checks if there is a user logged in the app.
	 * @return true, if the user is logged: false, otherwise.
	 */
	boolean hasLoggedUser();
	
	/**
	 * Shows to user all the commands.
	 * @throws NoLoggedUserException - if the user is not logged
	 */
	void ajuda() throws NoLoggedUserException;
	
	/**
	 * Ends the app.
	 * @throws SessionModeOnException - if there is a user logged
	 */
	void termina() throws SessionModeOnException;
	
	/**
	 * Ends the session of the logged user.
	 * @throws NoLoggedUserException - if there isn't a logged user.
	 */
	String sai() throws NoLoggedUserException;
	
	/**
	 * Gets the logged user.
	 * @return the logged user.
	 */
	User getLoggedUser();
	
	/**
	 * Regists a new user in the app.
	 * @param email - email of the new user
	 * @param name - name of the new user
	 * @param password - password of the new user
	 * @throws SessionModeOnException - if there isn't a logged user.
	 */
	void regista(String email, String name, String password) throws SessionModeOnException;
	
	/**
	 * Logins a user to the appS
	 * @param email - email of the user
	 * @param password - password of the user
	 * @throws SessionModeOnException - if there is already a logged user
	 * @throws UserDoesNotExistException - if the email of the user does not exist
	 */
	void entrada(String email, String password) throws SessionModeOnException, UserDoesNotExistException;
	
	/**
	 * Regists a new move to the app's logged useer
	 * @param origin - the move's origin
	 * @param destination - the move's destination
	 * @param date - the move's date
	 * @param time - the move's time
	 * @param duration - the move's duration
	 * @param seats - the move's available seats
	 * @throws NoLoggedUserException - if there is no logged user in the app
	 * @throws InvalidArgumentsException - if the arguments(date, time, duration, seats) are invalid
	 * @throws HasMoveInDateException - if the logged user has already a move (either a move or a lift) in the date 
	 */
	void nova(String origin, String destination, String date, String time, int duration, int seats) throws NoLoggedUserException, InvalidArgumentsException, HasMoveInDateException;
	
	/**
	 * Removes a move of the logged user
	 * @param date - date of the move to remove
	 * @throws NoLoggedUserException - if the app is not on session mode
	 * @throws InvalidDateException - if the date is not valid
	 * @throws HasNotMoveInDateException - if the user has not a move in date
	 * @throws HasAlreadyUsersInCarException - if the move has already users in car
	 */
	void remove(String date) throws NoLoggedUserException, InvalidDateException, HasNotMoveInDateException, HasAlreadyUsersInCarException;
	
	/**
	 * Add the logged user to a move
	 * @param email - email of the owner of the move
	 * @param date - date of the move
	 * @throws NoLoggedUserException - if the app is not on session mode
	 * @throws UserDoesNotExistException - if the user does not exist
	 * @throws InvalidDateException - if the date is not valid
	 * @throws HasNotMoveInDateException - if the user who owns the move has not move in date
	 * @throws UserIsTheSameException - if the user is the same as the logged user
	 * @throws HasMoveInDateException - if the logged user has already a move or lift in date
	 */
	void boleia(String email, String date) throws NoLoggedUserException, UserDoesNotExistException, InvalidDateException, HasNotMoveInDateException, UserIsTheSameException, HasMoveInDateException;
	
	/**
	 * Removes a user from the lift wiht date
	 * @param date - date of the lift
	 * @throws NoLoggedUserException - if the app is not on session mode
	 * @throws InvalidDateException - if the date is not valid
	 * @throws HasNotLiftInDateException - if the logged user has not a lift with date
	 */
	void retira(String date) throws NoLoggedUserException, InvalidDateException, HasNotLiftInDateException;
	
	/**
	 * Gives the information about the move with owner with email and date date.
	 * @param email - email of the owner 
	 * @param date - date of the move
	 * @return move with owner with email and date 
	 * @throws NoLoggedUserException - if the app is not on session mode
	 * @throws UserDoesNotExistException - if the user does not exist
	 * @throws InvalidDateException - if the date is not valid
	 * @throws HasNotMoveInDateException - if the move does not exist
	 */
	Move consulta(String email, String date) throws NoLoggedUserException, UserDoesNotExistException, InvalidDateException, HasNotMoveInDateException;
	
	/**
	 * Gives a iterator for all the moves of the loggedUser
	 * @return iterator of entries for all the moves of the logged user
	 * @throws NoLoggedUserException - if the app is not on session mode
	 * @throws NoMovesException - if the user has no moves
	 */
	Iterator<Entry<Date, Move>> listaMinhas() throws NoLoggedUserException, NoMovesException;
	
	/**
	  Gives a iterator for all the lifts of the loggedUser
	 * @return iterator of entries for all the lifts of the logged user
	 * @throws NoLoggedUserException - if the app is not on session mode
	 * @throws NoLiftsException - if the user has no lifts
	 */
	Iterator<Entry<Date, Move>> listBoleias() throws NoLoggedUserException, NoLiftsException;
	
	/**
	 * Gives iterator for all the moves of user with email
	 * @param email - email of the user
	 * @return iterator of entries for all the moves of the user with email
	 * @throws NoLoggedUserException - if the app is not on session mode
	 * @throws UserDoesNotExistException - if the user with email does not exist
	 * @throws NoMovesException - if the user has no moves
	 */
	Iterator<Entry<Date, Move>> listaPorEmail(String email) throws NoLoggedUserException, UserDoesNotExistException, NoMovesException;
	
	/**
	 * Gives iterator for all the moves on the app.
	 * @return iterator of entries for all the moves on the app
	 * @throws NoLoggedUserException - if the app is not on session mode
	 */
	Iterator<Entry<Date, SortedMap<String, Move>>> listaTodas() throws NoLoggedUserException;
	
	/**
	 * Gives iterator for all the moves with date date.
	 * @param data - date of the moves
	 * @return iterator of entries to all the moves with date 
	 * @throws NoLoggedUserException - if the app is not on session mode
	 * @throws InvalidDateException - if the date is not valid
	 * @throws NoMovesException - if there is no move on the date
	 */
	Iterator<Entry<String, Move>> listaPorData(String data) throws NoLoggedUserException, InvalidDateException, NoMovesException;
	
	/**
	 * Gets the number of users registed in the app.
	 * @return the number of regists in the app.
	 */
	int getNumberRegists();
	
	/**
	 * Checks if there is a user with the email.
	 * @param email - user's email
	 * @return true, if the user exists; false, otherwise
	 */
	boolean hasUserWithEmail(String email);
	
	/**
	 * Checks if there a user with the email.
	 * @param email - user's name
	 * @throws HasAlreadyUserException - if there is a user.
	 */
	void setExceptionEmail (String email) throws HasAlreadyUserException;
	
	/**
	 * Checks if there is no user with the email 
	 * @param email - the user's email
	 * @throws UserDoesNotExistException - if there is no user with the given email
	 */
	void setExceptionUserDoesNotExist (String email) throws UserDoesNotExistException;
	
	/**
	 * Checks if the app is on session mode.
	 * @throws SessionModeOnException -if the app is on session mode
	 */
	void setExceptionSessionModeOn() throws SessionModeOnException;
	
	/**
	 * Gets the user password
	 * @param email - the user's email
	 * @return the password of the user with the given email
	 */
	String getUserPass(String email);
	
	/**
	 * Gets the number of users in waiting list of the move
	 * @param email - email of the owner of the move
	 * @param date - date of the move
	 * @return the number of users in the waiting list of the move
	 */
	int getWaiting(String email, String date);
	
}
