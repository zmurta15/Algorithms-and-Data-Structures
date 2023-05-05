package Boleia;
import User.*;
import dataStructures.*;

public class MoveClass implements Move {
	
	private String origin;
	private String destination;
	private Date date;
	private int hour;
	private int minutes;
	private int duration;
	private int seats;
	private int emptySeats;
	private User owner;
	private Queue <User> usersWaiting;
	private List<User> usersInCar;
	
	public MoveClass(String origin, String destination, Date date, int hour, int minutes, int duration, int seats, User onwer) {
		this.origin = origin;
		this.destination = destination;
		this.date = date;
		this.hour = hour;
		this.duration = duration;
		this.seats = seats;
		this.emptySeats = seats;
		this.owner = owner;
		this.usersWaiting = new QueueInList<User>();
		this.usersInCar = new DoublyLinkedList<User>();
	}

}
