package Boleia;
import dataStructures.*;

import java.util.ArrayList;
import java.util.Iterator;
import Exceptions.*;
import User.*;


public class BoleiaClass implements Boleia {
	
	private int nRegists;
	
//	private ArrayList<User> users;
	
	private Map<String, User> emailUsers;
	
	private SortedMap<Date, TwoWayList> moves;
	
	private User loggedUser;
	
	private static final int CAPACITY = 20000;
	
	public BoleiaClass() {
		//users = new ArrayList<User> (); //temos que mudar
		moves = new SortedMapWithJavaClass<Date, TwoWayList>(CAPACITY); 
		emailUsers = new MapWithJavaClass<String, User >(CAPACITY);
		nRegists = 0;
		loggedUser = null;
	}


	@Override
	public void regist(String email, String name, String password) throws InicialModeException, HasEmailException, InvalidPassException {
		if (this.userOn()) {
			throw new InicialModeException();
		} else if(hasEmail(email)) {
			throw new HasEmailException();
		} else if(!validPass(password)) 
			throw new InvalidPassException();
		else {
			User u = new UserClass (email, name, password);
			//users.add(u);
			emailUsers.insert(email, u);
			nRegists++;
		}
	}
	
	public void login(String email, String password) /*throws InicialModeException, HasNotUserException, InvalidPassException*/ {
		
		//falta tentativas password, e mandar exceptions
		User u = getUser(email);
		loggedUser = u;
		u.addVisit();
	}


	@Override
	public boolean userOn() {
		return loggedUser != null;
	}
	
	public int getNRegists() {
		return nRegists;
	}
	
	public User getLoggedUser() {
		return loggedUser;
	}
	
	private User getUser(String email) {
		User user = null;
		Iterator<User> it = users.iterator();
		while (it.hasNext()) {
			User tmp = it.next();
			if (tmp.getEmail().equals(email))
				user = tmp;
		}
		return user;
	}

	private boolean hasEmail(String email) {
		boolean has = false;
		Iterator<User> it = users.iterator();
		while (it.hasNext()) {
			User tmp = it.next();
			if (tmp.getEmail().equals(email)) {
				has = true;
			}
		}
		return has;
	}
	
	private boolean validPass(String password) {
		boolean valid = false;
		char[] pass = password.toCharArray();
		for (int i = 0; i < pass.length; i++) {
			if (!Character.isLetterOrDigit(pass[i]) && password.length() >= 4 && password.length() <= 6 && !validPass(password))
				valid = true;
		}
		return valid;
	}
}
