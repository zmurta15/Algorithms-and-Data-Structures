package Boleia;

import Exceptions.*;
import User.*;
public interface Boleia {
	
	void regist(String email, String name, String password) throws InicialModeException, HasEmailException, InvalidPassException;
	
	boolean userOn ();
	
	int getNRegists();
	
	User getLoggedUser();

}
