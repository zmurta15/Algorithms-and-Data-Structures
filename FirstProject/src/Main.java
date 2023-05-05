import java.util.Scanner;
import Exceptions.*;
import Boleia.*;

public class Main {

	public static void main(String[] args) {
		Boleia boleia;
		boleia = new BoleiaClass(); //variavel onde se guarda a referencia para o Obejto Boleia
		System.out.print(prompt(boleia));
		Scanner in = new Scanner(System.in);
		//in.nextLine();
		interpretador(in, boleia);
		System.out.println("Obrigado. Ate a proxima."); //ver em caso de estar em mode sessao
		in.close();
	}
	
	private static void interpretador(Scanner in , Boleia boleia) {
		String cmd ="";
		
		while((!cmd.equals("TERMINA"))) {
			cmd = in.next().trim().toUpperCase();
			System.out.print(prompt(boleia));
			}
			switch(cmd) { // meter em modo sessao e nao
				case "LISTA": break;
				//......
				case "REGISTA":
					regista(in, boleia);
					break;
				default: System.out.println("Comando invalido.");
			}
		}
	
	private static void lista(Scanner in /*, Boleia b*/) {
		
	}
	
	private static void regista(Scanner in , Boleia boleia) { //falta as tentativas da password
		try {
			String email = in.nextLine();
			System.out.print("nome (maximo 50 caracteres):");
			String name = in.nextLine();
			System.out.print("password (entre 4 e 6 caracteres - digitos e letras):");
			String password = in.nextLine();
			boleia.regist(email, name, password);
			System.out.println("Registo "+boleia.getNRegists()+" efetuado.");
		}
		catch (InicialModeException a) {
			System.out.println("Comando invalido.");
		}
		catch (HasEmailException b) {
			System.out.println("Utilizador ja existente.");
		}
		catch (InvalidPassException c) {
			System.out.println("Password invalido.");
		}
	}
	
	private static void entrada (Scanner in /*, Boleia b*/ ) {
		
	}
	
	
	private static String prompt (Boleia b) {
		String prompt = "";
		if (b.userOn()) {
			prompt = b.getLoggedUser().getEmail() + " >"; 
		} else {
			prompt = "> ";
		}
		return prompt;
	}
}

