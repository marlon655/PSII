package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Pessoa> listaPessoas = new ArrayList<>();
		int option = 0;
		
		do {
			clearConsole();
			printMenu();
			option = isNumber();
			menu(option, scanner, listaPessoas);
		}while(option != 9);
		System.out.println("Programa Finalizado!");
		scanner.close();
	}
	
	//Exibicao do menu
	public static void printMenu() {
		System.out.println("Digite uma das opções abaixo para acessar os menus:");
		System.out.println("1 - Cadastrar um novo cliente");
		System.out.println("2 - Listar clientes cadastrados");
		System.out.println("3 - Atualizar um cliente cadastrado");
		System.out.println("4 - Excluir um cliente cadastrado");
		System.out.println("5 - Busca de cliente por ID");
		System.out.println("9 - Sair");

	}
	//Menu
	public static void menu(int option, Scanner scanner, List<Pessoa> listaPessoas) {
		switch(option){
			case 1:
				cadastrar(scanner, listaPessoas);
				break;
			case 2:
				listar(scanner, listaPessoas);
				break;
			case 3:
				atualizarPessoa(scanner, listaPessoas);
				break;
			case 4:
				removerPessoa(scanner, listaPessoas);
				break;
			case 5:
				listarId(scanner, listaPessoas);
				break;
			default:
				break;
		}
	}
	
	//procura na lista a id de uma pessoa.
	public static Pessoa searchId(int id, List<Pessoa> listaPessoas) {
		for(Pessoa pessoa : listaPessoas) {
			if(pessoa.getId() == id) {
				 return pessoa;
			}
		}
		return null;
	}
	
	//pesquisa o ultimo id inserido.
	public static int lastId(List<Pessoa> listaPessoas) {
		int lastId = 0;
		for(Pessoa pessoa : listaPessoas) {
			if(pessoa.getId() > lastId) {
				lastId = pessoa.getId();
			}
		}
		return lastId;
	}
	
	//cria o objeto pessoa e adiciona na lista.
	public static void cadastrar(Scanner scanner,List<Pessoa> listaPessoas) {
		// Solicita os dados
		System.out.println("#---- Cadastro de clientes ----#");
		System.out.println("Insira o nome da Pessoa: ");
		String nome = scanner.nextLine();
		System.out.println("Insira o email da Pessoa: ");
	    String email = scanner.nextLine();
		System.out.println("Insira o telefone da Pessoa: ");
		String telefone = scanner.nextLine();

		int lastId = lastId(listaPessoas);
		// Atribui um novo ID
		int id = lastId + 1;

		// Gera uma nova pessoa e coloca na lista
		Pessoa newPessoa = new Pessoa(id, nome, email, telefone);
		listaPessoas.add(newPessoa);

		System.out.printf("Cliente com o id %d inserido com sucesso!\n", id);
		scanner.nextLine();
	}
	
	//Lista todas as pessoas
	public static void listar(Scanner scanner, List<Pessoa> listaPessoas) {
		if(listaPessoas.isEmpty()) {
			System.out.println("A lista esta vazia!");
		}else {
			for(Pessoa pessoa : listaPessoas) {
				System.out.println("ID:" + pessoa.getId() + 
									", Nome: " + pessoa.getNome() +
									", Email: " + pessoa.getEmail() +
									", Telefone: " + pessoa.getTelefone());
			}
		}
		System.out.println("#---- Pressione ENTER para voltar ao Menu ----#");
		scanner.nextLine();
	}
	
	//Lista a pessoa de acordo com o id.
	public static void listarId(Scanner scanner, List<Pessoa> listaPessoas) {
		System.out.println("Digite o id que procura!");
		int id = isNumber();
		Pessoa pessoa = searchId(id, listaPessoas);
		if(pessoa != null) {
			System.out.println("ID:" + pessoa.getId() + 
					", Nome: " + pessoa.getNome() +
					", Email: " + pessoa.getEmail() +
					", Telefone: " + pessoa.getTelefone());
		}else {
			System.out.println("Pessoa nao encontrada!");
		}
		System.out.println("#---- Pressione ENTER para voltar ao Menu ----#");
		scanner.nextLine();
	}
	
	//Atualizar pessoa com base no Id;
	public static void atualizarPessoa(Scanner scanner,List<Pessoa> listaPessoas) {
		System.out.println("Insira o id que deseja atualizar!");
		int id = isNumber();
		Pessoa pessoa = searchId(id, listaPessoas);
		if(pessoa != null) {
			System.out.println("Digite o novo nome.");
			System.out.println("Deixe em branco para manter o atual.");
			String novoNome = scanner.nextLine();
			if(!novoNome.isEmpty()) {
				pessoa.setNome(novoNome);
			}
			
			System.out.println("Digite o novo Email.");
			System.out.println("Deixe em branco para manter o atual.");
			String novoEmail = scanner.nextLine();
			if(!novoEmail.isEmpty()) {
				pessoa.setEmail(novoEmail);
			}
			
			System.out.println("Digite o novo Telefone.");
			System.out.println("Deixe em branco para manter o atual.");
			String novoTelefone = scanner.nextLine();
			if(!novoTelefone.isEmpty()) {
				pessoa.setTelefone(novoTelefone);
			}
		}else {
			System.out.println("Pessoa nao encontrada!");
		}
		System.out.println("#---- Pressione ENTER para voltar ao Menu ----#");
		scanner.nextLine();
	}
	
	//Remover pessoa da lista com base no Id;
	public static void removerPessoa(Scanner scanner, List<Pessoa> listaPessoas) {
		System.out.println("Digite o id que deseja deletar.");
		int id = isNumber();
		Pessoa pessoa = searchId(id, listaPessoas);
		if(pessoa != null ) {
			listaPessoas.remove(pessoa);
			System.out.println("Id: " + pessoa.getId() + " removido!");
		}else {
			System.out.println("Id nao encontrada!");
		}
		System.out.println("#---- Pressione ENTER para voltar ao Menu ----#");
		scanner.nextLine();
	}
	
	public static void clearConsole() {
		try {
		if (System.getProperty("os.name").contains("Windows")) {
			// Comando para limpar o console no Windows
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} else {
		// Comando para limpar o console no Unix/Linux/MacOS
			new ProcessBuilder("clear").inheritIO().start().waitFor();
			}
		} catch (Exception e) {
			System.out.println("Erro ao limpar o console.");
			e.printStackTrace();
		}
	}
	
	//Validacao de numero.
	public static int isNumber() {
		Scanner scanner = new Scanner(System.in);
		boolean validNumber = false;
		int number = 0;
		
		while(!validNumber) {
			String input = scanner.next();
			try {
				number = Integer.parseInt(input);
				validNumber = true;
				return number;
			}catch(NumberFormatException e) {
				System.out.println("Voce deve inserir apenas numero!");
			}
		}
		scanner.close();
		return isNumber();
	}

	
}
