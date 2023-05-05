package Boleia;
import java.io.Serializable;

import dataStructures.*;

/**
 * The class that represents the move, which can be and deslocation or a lift
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
public class MoveClass implements Move, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * The origin of the move
	 */
	private String origin;
	
	/**
	 * The destination of the move
	 */
	private String destination;
	
	/**
	 * The date of the move
	 */
	private Date date;
	
	/**
	 * Time of the move
	 */
	private HourMinutes time;
	
	/**
	 * The duration of the move
	 */
	private int duration;
	
	/**
	 * The total number of seats of the move
	 */
	private int seats;
	
	/**
	 * The number of empty seats of the move
	 */
	private int emptySeats;
	
	/**
	 * The user that created the move
	 */
	private User owner;
	
	/*
	 * Queue of the users waiting in the list
	 */
	private Queue<User> usersWaiting;
	
	/**
	 * List of the users in car
	 */
	private TwoWayList<User> usersInCar;
	
	/**
	 * Number of people in the waiting list
	 */
	private int inQueue;
	
	public MoveClass(String origin, String destination, Date date, HourMinutes time, int duration, int seats, User owner) {
		this.origin = origin;
		this.destination = destination;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.seats = seats;
		this.emptySeats = seats;
		this.owner = owner;
		this.usersWaiting= new QueueInList<User>();
		this.usersInCar = new DoublyLinkedList<User>();
		this.inQueue = 0;
		
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public Date getDate() {
		return date;
	}
	
	public HourMinutes getTime() {
		return time;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getTotalSeats() {
		return seats;
	}
	
	public int getEmptySeats() {
		return emptySeats;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public boolean hasUsersInCar() {
		return !usersInCar.isEmpty();
	}
	
	public void addToMove(User u) {
		if(emptySeats == 0) {
			usersWaiting.enqueue(u);
			inQueue++;
		}else {
			usersInCar.addLast(u);
			emptySeats--;
		}
	}
	
	public int getInQueue() {
		return inQueue;
	}
	
	public void removeUser(User u) {
		usersInCar.remove(usersInCar.find(u));
		emptySeats++;
		if(inQueue>0) {
			usersInCar.addLast(usersWaiting.dequeue());
			emptySeats--;
		} 
	}
	
	public Iterator<User> initIt() {
		return usersInCar.iterator();
	}
	
	public void incEmptySeats() {
		emptySeats++;
	}
}
