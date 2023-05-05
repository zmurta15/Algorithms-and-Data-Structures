package Boleia;
import User.*;
import Move.*;

import java.io.Serializable;

import Exceptions.*;
import dataStructures.*;
import DateAndTime.*;

/**
 * The class that represents the application
 * 
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
public class BoleiaClass implements Boleia, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Number of user registed in the app.
	 */
	private int nRegists;
	
	/**
	 * User who is logged on the app.
	 */
	private User loggedUser;
	
	/**
	 * Dictionary of all the users of the app by their emails.
	 */
	private Map<String, User> emailUsers;
	
	
	/**
	 * Dictionary of all the moves in date
	 */
	private SortedMap<Date, SortedMap<String, Move>> boleias;
	
	
	
	/**
	 * Creates the app.
	 */
	public BoleiaClass() {
		nRegists = 0;
		loggedUser = null;
		emailUsers = new MapWithJavaClass<String, User>();
		boleias = new SortedMapWithJavaClass<Date,SortedMap<String,Move>>();
	}
	
	public void regista(String email, String name, String password) throws SessionModeOnException{
		if(hasLoggedUser()) {
			throw new SessionModeOnException();
		} 
		else {
			User user = new UserClass(email, name, password);
			emailUsers.insert(email, user);
			nRegists++;
		}
	}
	
	public void entrada(String email, String password) throws SessionModeOnException, UserDoesNotExistException {
		if (hasLoggedUser()) {
			throw new SessionModeOnException();
		}
		else if(!hasUserWithEmail(email)) {
			throw new UserDoesNotExistException();
		}
		else {
			loggedUser = emailUsers.find(email);
			loggedUser.incNVisits();
		}
	}
	
	public void nova(String origin, String destination, String date, String time, int duration, int seats) throws NoLoggedUserException, InvalidArgumentsException, HasMoveInDateException {
		Date d= setDate(date);
		HourMinutes t = setTime(time);
		if(!hasLoggedUser()) {
			throw new NoLoggedUserException();
		}
		else if(!validArguments(d,t, duration, seats)) {
			throw new InvalidArgumentsException();
		}
		else if(loggedUser.hasMoveOrLiftInDate(d)) {
			throw new HasMoveInDateException();
		}
		else {
			Move m = new MoveClass(origin, destination, d, t, duration, seats, loggedUser);
			if(boleias.find(d) != null) {
				boleias.find(d).insert(loggedUser.getEmail(),m);
			}
			else {
				SortedMap<String, Move> moves = new SortedMapWithJavaClass<String, Move>();
				moves.insert(loggedUser.getEmail(), m);
				boleias.insert(d, moves);
			}
			loggedUser.addMove(d, m);
		}
	}
	
	public void remove(String date) throws NoLoggedUserException, InvalidDateException, HasNotMoveInDateException, HasAlreadyUsersInCarException{
		Date d= setDate(date);
		if(!hasLoggedUser()) {
			throw new NoLoggedUserException();
		}
		else if(!d.isValid()) {
			throw new InvalidDateException();
		}
		else if(!loggedUser.hasMoveInDate(d)) {
			throw new HasNotMoveInDateException();
		}
		else if(loggedUser.hasAlreadyUserInCar(d)) {
			throw new HasAlreadyUsersInCarException();
		}
		else {
			SortedMap<String, Move> s = boleias.find(d);
			s.remove(loggedUser.getEmail());
			if(boleias.find(d).size() == 0) {
				boleias.remove(d);
			}
			loggedUser.removeMove(d);
		}
	}
	
	public void boleia(String email, String date) throws NoLoggedUserException, UserDoesNotExistException, InvalidDateException, HasNotMoveInDateException, UserIsTheSameException, HasMoveInDateException {
		Date d = setDate(date);
		if(!hasLoggedUser()) {
			throw new NoLoggedUserException();
		}
		else if (!hasUserWithEmail(email)) {
			throw new UserDoesNotExistException();
		}
		else if(!d.isValid()) {
			throw new InvalidDateException();
		} 
		else if(!emailUsers.find(email).hasMoveInDate(d)) {
			throw new HasNotMoveInDateException();
		}
		else if(loggedUser.getEmail().equals(email)) {
			throw new UserIsTheSameException();
		}
		else if(loggedUser.hasMoveOrLiftInDate(d)) {
			throw new HasMoveInDateException();
		}
		else {
			User u = emailUsers.find(email);
			Move m = u.getMoveWithDate(d);
			int inQueuebefore = m.getInQueue();
			m.addToMove(loggedUser);
			int inQueueAfter = m.getInQueue();
			if (inQueuebefore == inQueueAfter)
				loggedUser.addLift(d,m); //ver se o utilizador vai para a fila de espera ou para dentro do carro e lembrar de adciionar a boleia quando ele entrar no carro
					
		}
	}
	
	public void retira(String date) throws NoLoggedUserException, InvalidDateException, HasNotLiftInDateException {
		Date d = setDate(date);
		if(!hasLoggedUser()) {
			throw new NoLoggedUserException();
		}
		else if(!d.isValid()) {
			throw new InvalidDateException();
		} 
		else if(!loggedUser.hasLiftInDate(d)) {
			throw new HasNotLiftInDateException();
		}
		else {
			loggedUser.removeLift(d);
		}
	}
	
	public Move consulta(String email, String date) throws NoLoggedUserException, UserDoesNotExistException, InvalidDateException, HasNotMoveInDateException {
		Date d = setDate(date);
		if(!hasLoggedUser()) {
			throw new NoLoggedUserException();
		}
		else if (!hasUserWithEmail(email)) {
			throw new UserDoesNotExistException();
		}
		else if(!d.isValid()) {
			throw new InvalidDateException();
		} 
		else if(!emailUsers.find(email).hasMoveInDate(d)) {
			throw new HasNotMoveInDateException();
		} else {
			return emailUsers.find(email).getMoveWithDate(d);
		}
	}
	
	public User getLoggedUser() {
		return loggedUser;
	}
	
	public void ajuda() throws NoLoggedUserException {
		if(!hasLoggedUser())
			throw new NoLoggedUserException();
	}
	
	public void termina() throws SessionModeOnException {
		if (hasLoggedUser())
			throw new SessionModeOnException();
	}
	
	public String sai() throws NoLoggedUserException {
		if(!hasLoggedUser())
			throw new NoLoggedUserException();
		String loggedAux = loggedUser.getName();
		loggedUser = null;
		return loggedAux;
	}
	
	public Iterator<Entry<Date, Move>> listaMinhas() throws NoLoggedUserException, NoMovesException {
		if(!hasLoggedUser()) {
			throw new NoLoggedUserException();
		}
		else if(!loggedUser.hasMoves()) {
			throw new NoMovesException();
		}
		else {
			Iterator<Entry<Date, Move>> it = loggedUser.iteratorMoves();
			return it;
		}
	}
	
	public Iterator<Entry<Date, Move>> listBoleias() throws NoLoggedUserException, NoLiftsException {
		if(!hasLoggedUser()) {
			throw new NoLoggedUserException();
		}
		else if(!loggedUser.hasLifts()) {
			throw new NoLiftsException();
		}
		else {
			Iterator<Entry<Date, Move>> it = loggedUser.iteratorLifts();
			return it;
		}
	}
	
	public Iterator<Entry<Date, Move>> listaPorEmail(String email) throws NoLoggedUserException, UserDoesNotExistException, NoMovesException {
		if(!hasLoggedUser()) {
			throw new NoLoggedUserException();
		}
		else if (!hasUserWithEmail(email)) {
			throw new UserDoesNotExistException();
		}
		else if(!emailUsers.find(email).hasMoves()) {
			throw new NoMovesException();
		}
		else {
			User u = emailUsers.find(email);
			Iterator<Entry<Date, Move>> it = u.iteratorMoves();
			return it;
		}
	}
	
	public Iterator<Entry<Date, SortedMap<String, Move>>> listaTodas() throws NoLoggedUserException {
		if(!hasLoggedUser()) {
			throw new NoLoggedUserException();
		}
		else {
			Iterator<Entry<Date, SortedMap<String, Move>>> it = boleias.iterator();
			return it;
		}
	}
	
	public Iterator<Entry<String, Move>> listaPorData(String date) throws NoLoggedUserException, InvalidDateException, NoMovesException {
		Date d = setDate(date);
		if(!hasLoggedUser()) {
			throw new NoLoggedUserException();
		}
		else if(!d.isValid()) {
			throw new InvalidDateException();
		} 
		else if(boleias.find(d) == null) {
			throw new NoMovesException();
		}
		else {
			SortedMap<String, Move> s = boleias.find(d);
			return s.iterator();
		}
	}
	
	public int getNumberRegists() {
		return nRegists;
	}
	
	// -------------------------------------------------------------------------------------------------------------------------------------------
	
	public boolean hasUserWithEmail(String email){
		return emailUsers.find(email) != null;
	}
	
	public void setExceptionEmail (String email) throws HasAlreadyUserException {
		if(emailUsers.find(email) != null) {
			throw new HasAlreadyUserException();
		} 
	}
	
	public void setExceptionUserDoesNotExist (String email) throws UserDoesNotExistException {
		if(!hasUserWithEmail(email)) {
			throw new UserDoesNotExistException();
		}
	}
	
	public void setExceptionSessionModeOn() throws SessionModeOnException {
		if(hasLoggedUser()) {
			throw new SessionModeOnException();
		}
	}
	
	public boolean hasLoggedUser() {
		return loggedUser != null;
	}
	
	public String getUserPass(String email) {
		return emailUsers.find(email).getPassword();
	}
	
	/**
	 * Creats a new date
	 * @param date - string with the date (format: day-month-year)
	 * @return the new date created
	 */
	private Date setDate(String date) {
		return new DateClass(date);
	}
	
	/**
	 * Creates a new time with hours and minutes
	 * @param time - string with the hours and minutes (format: hours:minutes) 
	 * @return the new time created
	 */
	private HourMinutes setTime(String time) {
		return new HourMinutesClass(time); 
	}
	
	/**
	 * Checks if the arguments passed to the "nova" command are valid
	 * @param date - the move's date
	 * @param time - the move's time
	 * @param duration - the duration of the move
	 * @param seats - the available seats of the move
	 * @return true, if the arguments are valid; false, otherwise
	 */
	private boolean validArguments(Date date, HourMinutes time, int duration, int seats) {
		return (date.isValid() && time.isValid() && duration > 0 && seats>0 && seats <=10);
	}
	
	public int getWaiting(String email, String date) {
		Date d = setDate(date);
		User u = emailUsers.find(email);
		Move m = u.getMoveWithDate(d);
		return m.getInQueue();
	}
	
	
	
}
