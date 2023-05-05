package Boleia;
import java.io.Serializable;

import dataStructures.*;
/**
 * 
 * @author zmurt
 *
 */
public class UserClass implements User, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * User's email
	 */
	private String email;
	
	/**
	 * User's name
	 */
	private String name;
	
	/**
	 * User's password
	 */
	private String password;
	
	/**
	 * Number of visits of the user in the app
	 */
	private int nVisits;
	
	/**
	 * Number of moves registed in the app
	 */
	private int nMoves;
	
	/**
	 * Moves of the user registed in the date
	 */
	private SortedMap<Date, Move> moves;
	
	/**
	 * Lifts of the user registed in the date
	 */
	private SortedMap<Date, Move> lifts;
	
	
	/**
	 * Creates a new user.
	 * @param email - email of the new user
	 * @param name - name of the new user
	 * @param password - password of the new user
	 */
	public UserClass(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.nVisits = 0;
		this.nMoves = 0;
		this.moves = new AVL<Date, Move>();
		this.lifts = new AVL<Date, Move>();
	}
	
	public void addMove(Date date, Move move) {
		moves.insert(date, move);
		nMoves++;
	}
	
	public void addLift(Date date, Move lift) {
		lifts.insert(date, lift);
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void incNVisits() {
		nVisits++;
	}
	
	public int getNVisits() {
		return nVisits;
	}
	
	public boolean hasMoveOrLiftInDate(Date date) {
		return ((moves.find(date) != null) || (lifts.find(date) != null));
	}
	
	public int getNMoves() {
		return nMoves;
	}
	
	public boolean hasMoveInDate(Date date) {
		return moves.find(date) != null;
	}
	
	public boolean hasLiftInDate (Date date) {
		return lifts.find(date) != null;
	}
	
	public boolean hasAlreadyUserInCar(Date date) {
		Move move = moves.find(date);
		return move.hasUsersInCar();
	}
	
	public void removeMove(Date date) {
		moves.remove(date);
		nMoves--;
	}
	
	public void removeLift(Date date) {
		Move m = lifts.find(date);
		m.removeUser(this);
		lifts.remove(date);
	}
	
	public Move getMoveWithDate(Date date) {
		return moves.find(date);
	}
	
	public Iterator<Entry<Date, Move>> iteratorMoves() {
		Iterator<Entry<Date, Move>> it = moves.iterator();
		return it;
	}
	
	public Iterator<Entry<Date, Move>> iteratorLifts() {
		Iterator<Entry<Date, Move>> it = lifts.iterator();
		return it;
	}
	
	public boolean hasLifts() {
		return !lifts.isEmpty();
	}
	
	public boolean hasMoves() {
		return !moves.isEmpty();
	}

}
