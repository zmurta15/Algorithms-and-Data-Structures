import java.util.Scanner;
import java.io.*;
import Boleia.*;
import Exceptions.*;
import Move.*;
import User.*;
import dataStructures.*;
import DateAndTime.*;

/**
 * 
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Boleia boleia = readAsObject("appboleia.txt");
		if (boleia == null) {
			boleia = new BoleiaClass();
		}
		interpretador(in, boleia);
		storeAsObject(boleia, "appboleia.txt");
		in.close();
	}
	
	/**
	 * Interprets the command given by the user.
	 * @param in - Scanner's name
	 * @param boleia - app's name
	 */
	private static void interpretador(Scanner in , Boleia boleia) {
		String cmd ="";
		
		while((!cmd.equals("TERMINA")) || boleia.hasLoggedUser()) {
			System.out.print(prompt(boleia));
			cmd = in.next().trim().toUpperCase();
			switch(cmd) { 
				case "LISTA": 
					lista(in, boleia);
					break;
				case "REGISTA":
					regista(in, boleia);
					break;
				case "ENTRADA":
					entrada(in, boleia);
					break;
				case "SAI":
					sai(in, boleia);
					break;
				case "TERMINA":
					termina(in, boleia);
					break;
				case "AJUDA":
					ajuda(in, boleia);
					break;
				case "NOVA":
					nova(in, boleia);
					break;
				case "REMOVE":
					remove(in, boleia);
					break;
				case "BOLEIA":
					boleia(in, boleia);
					break;
				case "RETIRA":
					retira(in, boleia);
					break;
				case "CONSULTA":
					consulta(in, boleia);
					break;
				default: System.out.println("Comando invalido.");
				}
			}
		}
	
	/**
	 * Command list
	 * @param in - Scanner's name
	 * @param boleia - app's name
	 */
	private static void lista(Scanner in, Boleia boleia) {
		String next = in.nextLine().trim();
		if(next.toUpperCase().equals("MINHAS")) {
			listaMinhas(boleia);
		}
		else if(next.toUpperCase().equals("BOLEIAS")) {
			listaBoleias(boleia);
		}
		else if(next.toUpperCase().equals("TODAS")) {
			listaTodas(boleia);
		}
		else if (next.contains("-")) {
			listaPorData(boleia, next);
		}
		else {
			listaPorEmail(boleia, next);
		}
	}
	
	
	/**
	 * Regist a new user on the app.
	 * @param in - Scanner's name
	 * @param boleia - app's name
	 */
	private static void regista(Scanner in, Boleia boleia) {
		String email = in.nextLine().trim();
		try {
				boleia.setExceptionEmail(email);
				System.out.print("nome (maximo 50 caracteres): ");
				String name = in.nextLine();
				System.out.print("password (entre 4 e 6 caracteres - digitos e letras): ");
				String password = in.nextLine();
			int i =0;
			for ( i = 0; i<2 && !validPassword(password); i++) {
				System.out.print("password (entre 4 e 6 caracteres - digitos e letras): ");
				password = in.nextLine();
			}
			if( i == 2 && !validPassword(password)) {
				System.out.println("Registo nao realizado.");
			} else {
				boleia.regista(email, name, password);
				System.out.println("Registo "+boleia.getNumberRegists()+" efetuado.");
			
			}
		}
		catch (SessionModeOnException a) {
			System.out.println("Comando invalido."); 
		}
		catch (HasAlreadyUserException b) {
			System.out.println("Utilizador ja existente.");
		}
	}
	
	/**
	 * Logins a new user to the app
	 * @param in - Scanner's name
	 * @param boleia - app's name
	 */
	private static void entrada(Scanner in, Boleia boleia) {
		String email = in.nextLine().trim();
		try {
			boleia.setExceptionSessionModeOn();
			boleia.setExceptionUserDoesNotExist(email);
			System.out.print("password: ");
			String password = in.nextLine();
			int i = 0;
			for ( i = 0; i<2 && !password.equals(boleia.getUserPass(email)); i++) {
				System.out.print("password: ");
				password = in.nextLine();
			}
			if( i == 2 && !password.equals(boleia.getUserPass(email))) {
				System.out.println("Entrada nao realizada.");
			}
			else {
				boleia.entrada(email, password);
				System.out.println("Visita "+boleia.getLoggedUser().getNVisits()+" efetuada.");
			}
		}
		catch (SessionModeOnException a) {
			System.out.println("Comando invalido."); 
		}
		catch (UserDoesNotExistException b) {
			System.out.println("Utilizador nao existente.");
		}
	}
	
	/**
	 * End a session of the logged user.
	 * @param in - Scanner's name
	 * @param boleia - App's name
	 */
	private static void sai(Scanner in, Boleia boleia) {
		try {
			String name = boleia.sai();
			System.out.println("Ate a proxima "+ name +".");
		}
		catch(NoLoggedUserException a) {
			System.out.println("Comando invalido.");
		}
	}
	
	
	/**
	 * Ends the execution of the program
	 * @param in - Scanner's name
	 * @param boleia - app's name
	 */
	private static void termina(Scanner in, Boleia boleia) {
		try {
			boleia.termina();
			System.out.println("Obrigado. Ate a proxima.");
		}
		catch(SessionModeOnException a) {
			System.out.println("Comando invalido.");
		}
		
	}
	
	/**
	 * Print all the commands to the user.
	 * @param in - Scanner's name
	 * @param boleia - app's name
	 */
	private static void ajuda(Scanner in, Boleia boleia) {
		try {
			boleia.ajuda();
			System.out.println("ajuda - Mostra os comandos existentes");
			System.out.println("sai - Termina a sessao deste utilizador no programa");
			System.out.println("nova - Regista uma nova deslocacao");
			System.out.println("lista - Lista todas ou algumas deslocacoes registadas");
			System.out.println("boleia - Regista uma boleia para uma dada deslocacao");
			System.out.println("consulta - Lista a informacao de uma dada deslocacao");
			System.out.println("retira - Retira uma dada boleia");
			System.out.println("remove - Elimina uma dada deslocacao");
		}
		catch(NoLoggedUserException a) {
			System.out.println("ajuda - Mostra os comandos existentes");
			System.out.println("termina - Termina a execucao do programa");
			System.out.println("regista - Regista um novo utilizador no programa");
			System.out.println("entrada - Permite a entrada (\"login\") dum utilizador no programa");
		}
	}
	
	/**
	 * Creates a new move 
	 * @param in - Scanner's name
	 * @param boleia - app's name 
	 */
	private static void nova(Scanner in, Boleia boleia) {
		in.nextLine();
		String origin = in.nextLine().trim();
		String destination = in.nextLine().trim();
		String date = in.next().trim(); 
		String time = in.next().trim();
		int duration = in.nextInt();
		int seats = in.nextInt();
		in.nextLine();
		try {
			boleia.nova(origin, destination, date, time, duration, seats);
			System.out.println("Deslocacao "+boleia.getLoggedUser().getNMoves()+" registada. Obrigado "+boleia.getLoggedUser().getName()+".");
		}
		catch(NoLoggedUserException a) {
			System.out.println("Comando invalido.");
		}
		catch(InvalidArgumentsException b) {
			System.out.println("Dados invalidos.");
		}
		catch(HasMoveInDateException c) {
			System.out.println(boleia.getLoggedUser().getName()+" ja tem uma deslocacao ou boleia registada nesta data.");
		}
	}
	
	/**
	 * Removes a move of the user.
	 * @param in - Scanner's name
	 * @param boleia - app's name
	 */
	private static void remove(Scanner in, Boleia boleia) {
		String date = in.nextLine().trim();
		try {
			boleia.remove(date);
			System.out.println("Deslocacao removida.");
		}
		catch (NoLoggedUserException a) {
			System.out.println("Comando invalido.");
		}
		catch(InvalidDateException b) {
			System.out.println("Data invalida.");
		}
		catch(HasNotMoveInDateException c) {
			System.out.println(boleia.getLoggedUser().getName() + " nesta data nao tem registo de deslocacao.");
		}
		catch(HasAlreadyUsersInCarException d) {
			System.out.println(boleia.getLoggedUser().getName()+ " ja nao pode eliminar esta deslocacao.");
		}
	}
	
	/**
	 * Add a lift to the logged user
	 * @param in - Scanner's name
	 * @param boleia - app's name
	 */
	private static void boleia(Scanner in, Boleia boleia) {
		String email = in.next().trim();
		String date = in.nextLine().trim();
		try {
			boleia.boleia(email, date);
			if(boleia.getWaiting(email, date)> 0) {
				System.out.println("Ficou na fila de espera (posicao "+boleia.getWaiting(email, date)+").");
			}
			else {
				System.out.println("Boleia registada.");
			}
		}
		catch(NoLoggedUserException a) {
			System.out.println("Comando invalido.");
		}
		catch (UserDoesNotExistException b) {
			System.out.println("Utilizador inexistente.");
		}
		catch(InvalidDateException c) {
			System.out.println("Data invalida.");
		}
		catch (HasNotMoveInDateException d) {
			System.out.println("Deslocacao nao existe.");
		}
		catch(UserIsTheSameException e) {
			System.out.println(boleia.getLoggedUser().getName() + " nao pode dar boleia a si proprio.");
		}
		catch(HasMoveInDateException f) {
			System.out.println(boleia.getLoggedUser().getName() + " ja registou uma boleia ou deslocacao nesta data.");
		}
	}
	
	/**
	 * Removes a user of the lift with date date
	 * @param in - Scanner's name
	 * @param boleia - app's name
	 */
	private static void retira(Scanner in, Boleia boleia) {
		String date = in.nextLine().trim();
		try {
			boleia.retira(date);
			System.out.println(boleia.getLoggedUser().getName() +" boleia retirada.");
		}
		catch(NoLoggedUserException a) {
			System.out.println("Comando invalido.");
		}
		catch(InvalidDateException b) {
			System.out.println("Data invalida.");
		}
		catch(HasNotLiftInDateException c) {
			System.out.println(boleia.getLoggedUser().getName()+" nesta data nao tem registo de boleia.");
		}
	}
	
	/**
	 * Prints information about the move with owner with email and date
	 * @param in - Scanner's name
	 * @param boleia - app's name
	 */
	private static void consulta(Scanner in, Boleia boleia) {
		String email = in.next().trim();
		String date = in.nextLine().trim();
		try {
			Move m = boleia.consulta(email, date);
			System.out.println(email);
			System.out.println(m.getOrigin()+"-"+m.getDestination());
			System.out.println(m.getDate().getDate()+" "+m.getTime().getTime() +" "+ m.getDuration());
			System.out.println("Lugares vagos: "+m.getEmptySeats());
			if(!m.hasUsersInCar()) {
				System.out.println("Sem boleias registadas.");
			}
			else {
				System.out.print("Boleias: ");
				Iterator<User> it = m.initIt(); //ver caso de nao ter boleias registadas
				while (it.hasNext()) {
					User u = it.next();
					if (it.hasNext()) {
						System.out.print(u.getEmail() + "; ");
					} else {
						System.out.println(u.getEmail());
					}
				}
			}
			System.out.println("Em espera: "+m.getInQueue());
		}
		catch(NoLoggedUserException a) {
			System.out.println("Comando invalido.");
		}
		catch (UserDoesNotExistException b) {
			System.out.println("Utilizador inexistente.");
		}
		catch(InvalidDateException c) {
			System.out.println("Data invalida.");
		}
		catch(HasNotMoveInDateException d) {
			System.out.println("Deslocacao nao existe.");
		}
	}
	
	/**
	 * List all loggedUser's moves
	 * @param boleia - app's name
	 */
	private static void listaMinhas(Boleia boleia) {
		try {
			Iterator<Entry<Date, Move>> it = boleia.listaMinhas();
			while(it.hasNext()) {
				Move m = it.next().getValue();
				System.out.println(boleia.getLoggedUser().getEmail());
				System.out.println(m.getOrigin()+"-"+m.getDestination());
				System.out.println(m.getDate().getDate()+" "+m.getTime().getHour()+ ":"+m.getTime().getMinutes() +" "+ m.getDuration());
				System.out.println("Lugares vagos: "+m.getEmptySeats());
				if(!m.hasUsersInCar()) {
					System.out.println("Sem boleias registadas.");
				}
				else {
					System.out.print("Boleias: ");
					Iterator<User> it1 = m.initIt();
					while (it1.hasNext()) {
						User u = it1.next();
						if (it1.hasNext()) {
							System.out.print(u.getEmail() + "; ");
						} else {
							System.out.println(u.getEmail());
						}
					}
				}
				System.out.println("Em espera: "+m.getInQueue());
				System.out.println();
			}
		}
		catch(NoLoggedUserException a) {
			System.out.println("Comando invalido.");
		}
		catch(NoMovesException b) {
			System.out.println("Sem deslocacoes.");
		}
	}
	
	/**
	 * List all loggeduser's lifts
	 * @param boleia - app's name
	 */
	private static void listaBoleias(Boleia boleia) {
		try {
			Iterator<Entry<Date, Move>> it = boleia.listBoleias();
			while(it.hasNext()) {
				Move m = it.next().getValue();
				System.out.println(m.getOwner().getEmail());
				System.out.println((m.getOrigin() + "-" + m.getDestination()));
				System.out.println(m.getDate().getDate() + " " + m.getTime().getHour()+":"+m.getTime().getMinutes() + " " + m.getDuration());
				System.out.println();
			}
		}
		catch(NoLoggedUserException a) {
			System.out.println("Comando invalido.");
		}
		catch(NoLiftsException b) {
			System.out.println("Sem deslocacoes.");
		}
	}
	
	/**
	 * List all the moves from user with email.
	 * @param boleia - app's name
	 * @param email - user's email
	 */
	private static void listaPorEmail(Boleia boleia, String email) {
		try {
			Iterator<Entry<Date, Move>> it =boleia.listaPorEmail(email);
			while(it.hasNext()) {
				Move m = it.next().getValue();
				System.out.println(m.getOwner().getEmail());
				System.out.println(m.getOrigin()+"-"+m.getDestination());
				System.out.println(m.getDate().getDate() + " " + m.getTime().getHour() + ":" + m.getTime().getMinutes() + " " + m.getDuration());
				System.out.println();
			}
		}
		catch(NoLoggedUserException a) {
			System.out.println("Comando invalido.");
		}
		catch(UserDoesNotExistException b) {
			System.out.println("Nao existe o utilizador dado.");
		}
		catch(NoMovesException c) {
			System.out.println("Sem deslocacoes.");
		}
	}
	
	/**
	 * List all moves from all the users of the app
	 * @param boleia - app's name
	 */
	private static void listaTodas(Boleia boleia) {
		try {
			Iterator<Entry<Date, SortedMap<String, Move>>> it = boleia.listaTodas();
			while(it.hasNext()) {
				Entry<Date, SortedMap<String, Move>> e = it.next();
				Date d = e.getKey();
				SortedMap<String, Move> s = e.getValue();
				Iterator <Entry<String, Move>> it1 = s.iterator();
				while (it1.hasNext()) {
					System.out.println(d.getDate() + " " + it1.next().getKey());
					System.out.println();
				}
			}
		}
		catch(NoLoggedUserException a) {
			System.out.println("Comando invalido.");
		}
	}
	
	/**
	 * List all the move with date date
	 * @param boleia - app's name
	 * @param date - date for the moves
	 */
	private static void listaPorData(Boleia boleia, String date) {
		try {
			Iterator<Entry<String, Move>> it = boleia.listaPorData(date);
			while(it.hasNext()) {
				Entry <String, Move> it1 = it.next();
				if (it1.getValue().getEmptySeats() != 0) {
					String s = it1.getKey();
					System.out.println(s);
					System.out.println();
				}
			}
			
		}
		catch(NoLoggedUserException a) {
			System.out.println("Comando invalido.");
		}
		catch(InvalidDateException b) {
			System.out.println("Data invalida.");
		}
		catch(NoMovesException c) {
			System.out.println("Sem deslocacoes.");
		}
	}
	
	/**
	 * Print the prompt of the app.
	 * @param b - app's name.
	 * @return the prompt of the app.
	 */
	private static String prompt (Boleia b) {
		String prompt = "";
		if (b.hasLoggedUser()) {
			prompt = b.getLoggedUser().getEmail().trim() + " > "; 
		} else {
			prompt = "> ";
		}
		return prompt;
	}
	
	/**
	 * Checks if the password given by the user in th regist is valid.
	 * @param password - password given by the user.
	 * @return true,if the password is valid; false, otherwise
	 */
	private static boolean validPassword(String password) {
		boolean valid = false;
		char[] pass = password.toCharArray();
		for (int i = 0; i < pass.length; i++) {
			if (Character.isLetterOrDigit(pass[i]) && password.length() >= 4 && password.length() <= 6)
				valid = true;
		}
		return valid;
	}
	
	/**
	 * Loads the file that contains the state of the system
	 * @param filename - the name of the file to read
	 * @return object with the system's state
	 */
	private static Boleia readAsObject(String filename) {
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(
					filename));
				Boleia boleia = (Boleia) file.readObject();
			file.close();
			return boleia;
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		return null;
	}
	
	/**
	 * Saves the state of the system in a file with a specific
	 * 
	 * @param boleia - the object that represents the app
	 * @param filename - the name of the file to save in
	 */
	private static void storeAsObject(Boleia boleia, String filename) {

		try {
			ObjectOutputStream file = new ObjectOutputStream(
					new FileOutputStream(filename));

			file.writeObject(boleia);
			file.flush();
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
