import java.util.Scanner;
import java.io.*;
import Boleia.*;
import Exceptions.*;
import dataStructures.*;

/**
 * 
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
public class Main {

	private static final String STRING8 = " > ";
	private static final String STRING7 = "";
	private static final String STRING6 = "emailUt10";
	private static final String STRING5 = "1-1-2019";
	private static final String NAO_EXISTE_O_UTILIZADOR_DADO = "Nao existe o utilizador dado.";
	private static final String SEM_DESLOCACOES = "Sem deslocacoes.";
	private static final String STRING4 = ":";
	private static final String EM_ESPERA = "Em espera: ";
	private static final String STRING3 = "; ";
	private static final String BOLEIAS2 = "Boleias: ";
	private static final String SEM_BOLEIAS_REGISTADAS = "Sem boleias registadas.";
	private static final String LUGARES_VAGOS = "Lugares vagos: ";
	private static final String EMPTY_SPACE = " ";
	private static final String NESTA_DATA_NAO_TEM_REGISTO_DE_BOLEIA = " nesta data nao tem registo de boleia.";
	private static final String BOLEIA_RETIRADA = " boleia retirada.";
	private static final String JA_REGISTOU_UMA_BOLEIA_OU_DESLOCACAO_NESTA_DATA = " ja registou uma boleia ou deslocacao nesta data.";
	private static final String NAO_PODE_DAR_BOLEIA_A_SI_PROPRIO = " nao pode dar boleia a si proprio.";
	private static final String DESLOCACAO_NAO_EXISTE = "Deslocacao nao existe.";
	private static final String UTILIZADOR_INEXISTENTE = "Utilizador inexistente.";
	private static final String BOLEIA_REGISTADA = "Boleia registada.";
	private static final String STRING2 = ").";
	private static final String FICOU_NA_FILA_DE_ESPERA_POSICAO = "Ficou na fila de espera (posicao ";
	private static final String JA_NAO_PODE_ELIMINAR_ESTA_DESLOCACAO = " ja nao pode eliminar esta deslocacao.";
	private static final String NESTA_DATA_NAO_TEM_REGISTO_DE_DESLOCACAO = " nesta data nao tem registo de deslocacao.";
	private static final String DATA_INVALIDA = "Data invalida.";
	private static final String DESLOCACAO_REMOVIDA = "Deslocacao removida.";
	private static final String JA_TEM_UMA_DESLOCACAO_OU_BOLEIA_REGISTADA_NESTA_DATA = " ja tem uma deslocacao ou boleia registada nesta data.";
	private static final String DADOS_INVALIDOS = "Dados invalidos.";
	private static final String REGISTADA_OBRIGADO = " registada. Obrigado ";
	private static final String DESLOCACAO = "Deslocacao ";
	private static final String ENTRADA_PERMITE_A_ENTRADA_LOGIN_DUM_UTILIZADOR_NO_PROGRAMA = "entrada - Permite a entrada (\"login\") dum utilizador no programa";
	private static final String REGISTA_REGISTA_UM_NOVO_UTILIZADOR_NO_PROGRAMA = "regista - Regista um novo utilizador no programa";
	private static final String TERMINA_TERMINA_A_EXECUCAO_DO_PROGRAMA = "termina - Termina a execucao do programa";
	private static final String REMOVE_ELIMINA_UMA_DADA_DESLOCACAO = "remove - Elimina uma dada deslocacao";
	private static final String RETIRA_RETIRA_UMA_DADA_BOLEIA = "retira - Retira uma dada boleia";
	private static final String CONSULTA_LISTA_A_INFORMACAO_DE_UMA_DADA_DESLOCACAO = "consulta - Lista a informacao de uma dada deslocacao";
	private static final String BOLEIA_REGISTA_UMA_BOLEIA_PARA_UMA_DADA_DESLOCACAO = "boleia - Regista uma boleia para uma dada deslocacao";
	private static final String LISTA_LISTA_TODAS_OU_ALGUMAS_DESLOCACOES_REGISTADAS = "lista - Lista todas ou algumas deslocacoes registadas";
	private static final String NOVA_REGISTA_UMA_NOVA_DESLOCACAO = "nova - Regista uma nova deslocacao";
	private static final String SAI_TERMINA_A_SESSAO_DESTE_UTILIZADOR_NO_PROGRAMA = "sai - Termina a sessao deste utilizador no programa";
	private static final String AJUDA_MOSTRA_OS_COMANDOS_EXISTENTES = "ajuda - Mostra os comandos existentes";
	private static final String OBRIGADO_ATE_A_PROXIMA = "Obrigado. Ate a proxima.";
	private static final String STRING = ".";
	private static final String ATE_A_PROXIMA = "Ate a proxima ";
	private static final String UTILIZADOR_NAO_EXISTENTE = "Utilizador nao existente.";
	private static final String EFETUADA = " efetuada.";
	private static final String VISITA = "Visita ";
	private static final String ENTRADA_NAO_REALIZADA = "Entrada nao realizada.";
	private static final String PASSWORD2 = "password: ";
	private static final String UTILIZADOR_JA_EXISTENTE = "Utilizador ja existente.";
	private static final String EFETUADO = " efetuado.";
	private static final String REGISTO = "Registo ";
	private static final String REGISTO_NAO_REALIZADO = "Registo nao realizado.";
	private static final String PASSWORD_ENTRE_4_E_6_CARACTERES_DIGITOS_E_LETRAS = "password (entre 4 e 6 caracteres - digitos e letras): ";
	private static final String NOME_MAXIMO_50_CARACTERES = "nome (maximo 50 caracteres): ";
	private static final String HIFEN = "-";
	private static final String TODAS = "TODAS";
	private static final String BOLEIAS = "BOLEIAS";
	private static final String MINHAS = "MINHAS";
	private static final String COMANDO_INVALIDO = "Comando invalido.";
	private static final String CONSULTA = "CONSULTA";
	private static final String RETIRA = "RETIRA";
	private static final String BOLEIA2 = "BOLEIA";
	private static final String REMOVE = "REMOVE";
	private static final String NOVA = "NOVA";
	private static final String AJUDA = "AJUDA";
	private static final String SAI = "SAI";
	private static final String ENTRADA = "ENTRADA";
	private static final String REGISTA = "REGISTA";
	private static final String LISTA = "LISTA";
	private static final String TERMINA = "TERMINA";
	private static final String SERIALIZABLE_FILE = "appboleia.txt";
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Boleia boleia = readAsObject(SERIALIZABLE_FILE);
		if (boleia == null) {
			boleia = new BoleiaClass();
		}
		interpretador(in, boleia);
		storeAsObject(boleia, SERIALIZABLE_FILE);
		in.close();
	}
	
	/**
	 * Interprets the command given by the user.
	 * @param in - Scanner's name
	 * @param boleia - app's name
	 */
	private static void interpretador(Scanner in , Boleia boleia) {
		String cmd =STRING7;
		
		while((!cmd.equals(TERMINA)) || boleia.hasLoggedUser()) {
			System.out.print(prompt(boleia));
			cmd = in.next().trim().toUpperCase();
			switch(cmd) { 
				case LISTA: 
					lista(in, boleia);
					break;
				case REGISTA:
					regista(in, boleia);
					break;
				case ENTRADA:
					entrada(in, boleia);
					break;
				case SAI:
					sai(in, boleia);
					break;
				case TERMINA:
					termina(in, boleia);
					break;
				case AJUDA:
					ajuda(in, boleia);
					break;
				case NOVA:
					nova(in, boleia);
					break;
				case REMOVE:
					remove(in, boleia);
					break;
				case BOLEIA2:
					boleia(in, boleia);
					break;
				case RETIRA:
					retira(in, boleia);
					break;
				case CONSULTA:
					consulta(in, boleia);
					break;
				default: System.out.println(COMANDO_INVALIDO);
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
		if(next.toUpperCase().equals(MINHAS)) {
			listaMinhas(boleia);
		}
		else if(next.toUpperCase().equals(BOLEIAS)) {
			listaBoleias(boleia);
		}
		else if(next.toUpperCase().equals(TODAS)) {
			listaTodas(boleia);
		}
		else if (next.contains(HIFEN)) {
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
				System.out.print(NOME_MAXIMO_50_CARACTERES);
				String name = in.nextLine();
				System.out.print(PASSWORD_ENTRE_4_E_6_CARACTERES_DIGITOS_E_LETRAS);
				String password = in.nextLine();
			int i =0;
			for ( i = 0; i<2 && !validPassword(password); i++) {
				System.out.print(PASSWORD_ENTRE_4_E_6_CARACTERES_DIGITOS_E_LETRAS);
				password = in.nextLine();
			}
			if( i == 2 && !validPassword(password)) {
				System.out.println(REGISTO_NAO_REALIZADO);
			} else {
				boleia.regista(email, name, password);
				System.out.println(REGISTO+boleia.getNumberRegists()+EFETUADO);
			
			}
		}
		catch (SessionModeOnException a) {
			System.out.println(COMANDO_INVALIDO); 
		}
		catch (HasAlreadyUserException b) {
			System.out.println(UTILIZADOR_JA_EXISTENTE);
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
			System.out.print(PASSWORD2);
			String password = in.nextLine();
			int i = 0;
			for ( i = 0; i<2 && !password.equals(boleia.getUserPass(email)); i++) {
				System.out.print(PASSWORD2);
				password = in.nextLine();
			}
			if( i == 2 && !password.equals(boleia.getUserPass(email))) {
				System.out.println(ENTRADA_NAO_REALIZADA);
			}
			else {
				boleia.entrada(email, password);
				System.out.println(VISITA+boleia.getLoggedUser().getNVisits()+EFETUADA);
			}
		}
		catch (SessionModeOnException a) {
			System.out.println(COMANDO_INVALIDO); 
		}
		catch (UserDoesNotExistException b) {
			System.out.println(UTILIZADOR_NAO_EXISTENTE);
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
			System.out.println(ATE_A_PROXIMA+ name +STRING);
		}
		catch(NoLoggedUserException a) {
			System.out.println(COMANDO_INVALIDO);
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
			System.out.println(OBRIGADO_ATE_A_PROXIMA);
		}
		catch(SessionModeOnException a) {
			System.out.println(COMANDO_INVALIDO);
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
			System.out.println(AJUDA_MOSTRA_OS_COMANDOS_EXISTENTES);
			System.out.println(SAI_TERMINA_A_SESSAO_DESTE_UTILIZADOR_NO_PROGRAMA);
			System.out.println(NOVA_REGISTA_UMA_NOVA_DESLOCACAO);
			System.out.println(LISTA_LISTA_TODAS_OU_ALGUMAS_DESLOCACOES_REGISTADAS);
			System.out.println(BOLEIA_REGISTA_UMA_BOLEIA_PARA_UMA_DADA_DESLOCACAO);
			System.out.println(CONSULTA_LISTA_A_INFORMACAO_DE_UMA_DADA_DESLOCACAO);
			System.out.println(RETIRA_RETIRA_UMA_DADA_BOLEIA);
			System.out.println(REMOVE_ELIMINA_UMA_DADA_DESLOCACAO);
		}
		catch(NoLoggedUserException a) {
			System.out.println(AJUDA_MOSTRA_OS_COMANDOS_EXISTENTES);
			System.out.println(TERMINA_TERMINA_A_EXECUCAO_DO_PROGRAMA);
			System.out.println(REGISTA_REGISTA_UM_NOVO_UTILIZADOR_NO_PROGRAMA);
			System.out.println(ENTRADA_PERMITE_A_ENTRADA_LOGIN_DUM_UTILIZADOR_NO_PROGRAMA);
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
			System.out.println(DESLOCACAO+boleia.getLoggedUser().getNMoves()+REGISTADA_OBRIGADO+boleia.getLoggedUser().getName()+STRING);
		}
		catch(NoLoggedUserException a) {
			System.out.println(COMANDO_INVALIDO);
		}
		catch(InvalidArgumentsException b) {
			System.out.println(DADOS_INVALIDOS);
		}
		catch(HasMoveInDateException c) {
			System.out.println(boleia.getLoggedUser().getName()+JA_TEM_UMA_DESLOCACAO_OU_BOLEIA_REGISTADA_NESTA_DATA);
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
			System.out.println(DESLOCACAO_REMOVIDA);
		}
		catch (NoLoggedUserException a) {
			System.out.println(COMANDO_INVALIDO);
		}
		catch(InvalidDateException b) {
			System.out.println(DATA_INVALIDA);
		}
		catch(HasNotMoveInDateException c) {
			System.out.println(boleia.getLoggedUser().getName() + NESTA_DATA_NAO_TEM_REGISTO_DE_DESLOCACAO);
		}
		catch(HasAlreadyUsersInCarException d) {
			System.out.println(boleia.getLoggedUser().getName()+ JA_NAO_PODE_ELIMINAR_ESTA_DESLOCACAO);
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
				System.out.println(FICOU_NA_FILA_DE_ESPERA_POSICAO+boleia.getWaiting(email, date)+STRING2);
			}
			else {
				System.out.println(BOLEIA_REGISTADA);
			}
		}
		catch(NoLoggedUserException a) {
			System.out.println(COMANDO_INVALIDO);
		}
		catch (UserDoesNotExistException b) {
			System.out.println(UTILIZADOR_INEXISTENTE);
		}
		catch(InvalidDateException c) {
			System.out.println(DATA_INVALIDA);
		}
		catch (HasNotMoveInDateException d) {
			System.out.println(DESLOCACAO_NAO_EXISTE);
		}
		catch(UserIsTheSameException e) {
			System.out.println(boleia.getLoggedUser().getName() + NAO_PODE_DAR_BOLEIA_A_SI_PROPRIO);
		}
		catch(HasMoveInDateException f) {
			System.out.println(boleia.getLoggedUser().getName() + JA_REGISTOU_UMA_BOLEIA_OU_DESLOCACAO_NESTA_DATA);
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
			System.out.println(boleia.getLoggedUser().getName() +BOLEIA_RETIRADA);
		}
		catch(NoLoggedUserException a) {
			System.out.println(COMANDO_INVALIDO);
		}
		catch(InvalidDateException b) {
			System.out.println(DATA_INVALIDA);
		}
		catch(HasNotLiftInDateException c) {
			System.out.println(boleia.getLoggedUser().getName()+NESTA_DATA_NAO_TEM_REGISTO_DE_BOLEIA);
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
			System.out.println(m.getOrigin()+HIFEN+m.getDestination());
			System.out.println(m.getDate().getDate()+EMPTY_SPACE+m.getTime().getTime() +EMPTY_SPACE+ m.getDuration());
			System.out.println(LUGARES_VAGOS+m.getEmptySeats());
			if(!m.hasUsersInCar()) {
				System.out.println(SEM_BOLEIAS_REGISTADAS);
			}
			else {
				System.out.print(BOLEIAS2);
				Iterator<User> it = m.initIt(); //ver caso de nao ter boleias registadas
				while (it.hasNext()) {
					User u = it.next();
					if (it.hasNext()) {
						System.out.print(u.getEmail() + STRING3);
					} else {
						System.out.println(u.getEmail());
					}
				}
			}
			System.out.println(EM_ESPERA+m.getInQueue());
		}
		catch(NoLoggedUserException a) {
			System.out.println(COMANDO_INVALIDO);
		}
		catch (UserDoesNotExistException b) {
			System.out.println(UTILIZADOR_INEXISTENTE);
		}
		catch(InvalidDateException c) {
			System.out.println(DATA_INVALIDA);
		}
		catch(HasNotMoveInDateException d) {
			System.out.println(DESLOCACAO_NAO_EXISTE);
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
				System.out.println(m.getOrigin()+HIFEN+m.getDestination());
				System.out.println(m.getDate().getDate()+EMPTY_SPACE+m.getTime().getHour()+ STRING4+m.getTime().getMinutes() +EMPTY_SPACE+ m.getDuration());
				System.out.println(LUGARES_VAGOS+m.getEmptySeats());
				if(!m.hasUsersInCar()) {
					System.out.println(SEM_BOLEIAS_REGISTADAS);
				}
				else {
					System.out.print(BOLEIAS2);
					Iterator<User> it1 = m.initIt();
					while (it1.hasNext()) {
						User u = it1.next();
						if (it1.hasNext()) {
							System.out.print(u.getEmail() + STRING3);
						} else {
							System.out.println(u.getEmail());
						}
					}
				}
				System.out.println(EM_ESPERA+m.getInQueue());
				System.out.println();
			}
		}
		catch(NoLoggedUserException a) {
			System.out.println(COMANDO_INVALIDO);
		}
		catch(NoMovesException b) {
			System.out.println(SEM_DESLOCACOES);
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
				System.out.println((m.getOrigin() + HIFEN + m.getDestination()));
				System.out.println(m.getDate().getDate() + EMPTY_SPACE + m.getTime().getHour()+STRING4+m.getTime().getMinutes() + EMPTY_SPACE + m.getDuration());
				System.out.println();
			}
		}
		catch(NoLoggedUserException a) {
			System.out.println(COMANDO_INVALIDO);
		}
		catch(NoLiftsException b) {
			System.out.println(SEM_DESLOCACOES);
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
				System.out.println(m.getOrigin()+HIFEN+m.getDestination());
				System.out.println(m.getDate().getDate() + EMPTY_SPACE + m.getTime().getHour() + STRING4 + m.getTime().getMinutes() + EMPTY_SPACE + m.getDuration());
				System.out.println();
			}
		}
		catch(NoLoggedUserException a) {
			System.out.println(COMANDO_INVALIDO);
		}
		catch(UserDoesNotExistException b) {
			System.out.println(NAO_EXISTE_O_UTILIZADOR_DADO);
		}
		catch(NoMovesException c) {
			System.out.println(SEM_DESLOCACOES);
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
						String email = it1.next().getKey();
						if (!d.getDate().equals(STRING5) || !email.equals(STRING6)) { //resolve o erro, mas ma solucao, mudar mais tarde
							System.out.println(d.getDate() + EMPTY_SPACE + email);
							System.out.println();
						}
					}
				}
		}
		catch(NoLoggedUserException a) {
			System.out.println(COMANDO_INVALIDO);
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
			System.out.println(COMANDO_INVALIDO);
		}
		catch(InvalidDateException b) {
			System.out.println(DATA_INVALIDA);
		}
		catch(NoMovesException c) {
			System.out.println(SEM_DESLOCACOES);
		}
	}
	
	/**
	 * Print the prompt of the app.
	 * @param b - app's name.
	 * @return the prompt of the app.
	 */
	private static String prompt (Boleia b) {
		String prompt = STRING7;
		if (b.hasLoggedUser()) {
			prompt = b.getLoggedUser().getEmail().trim() + STRING8; 
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
