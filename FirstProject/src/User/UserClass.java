package User;
import Boleia.*;
import dataStructures.*;

public class UserClass implements User{ 
	

	private String email;
	private String name;
	private String password;
	private int nVisits;
	private int nMoves;
	private TwoWayList<Move> moves; //DESLOCAÇOES
	private TwoWayList<Move> lifts; //boleias

	
	public UserClass(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.nVisits = 0;
		this.nMoves = 0;
		moves = new DoublyLinkedList<Move>();
		lifts = new DoublyLinkedList<Move>();
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
	
	public void addVisit() {
		nVisits++;
	}
	
	public int getNVisits() {
		return nVisits;
	}

	
}
