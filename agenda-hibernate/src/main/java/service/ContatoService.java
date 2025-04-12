package service;

import java.util.List;
import java.util.Scanner;

import dao.CompromissoDAOImpl;
import dao.ContatoDAOImpl;
import model.Contato;

public class ContatoService {

	private static Scanner scan = new Scanner(System.in);
	private static ContatoDAOImpl impl = new ContatoDAOImpl();
	private static CompromissoDAOImpl compromissoImpl = new CompromissoDAOImpl();
	
	public static void salvarContato() {
		Contato contato = new Contato();
		System.out.println("Informe o nome do contato: ");
		String nome = scan.nextLine();
		if(nome.length() < 2) {
			throw new IllegalArgumentException("Nome muito curto!");
		}
		System.out.println("Informe o telefone do contato: ");
		String telefone = scan.nextLine();
		contato.setNome(nome);
		contato.setTelefone(telefone);
		impl.save(contato);
		
	}
	
	public static Contato localizarContato() {
		System.out.println("Informe o ID do contato: ");
		int id = scan.nextInt(); scan.nextLine();
		Contato contato = impl.findById(id);
		
		return contato;
	}
	
	public static List<Contato> listarContatos() {
		return impl.findAll();
	}
	
	public static void alterarContato() {
		Contato contato = localizarContato();
		if(contato != null) {
		System.out.println("Informe o nome do contato: ");
		String nome = scan.nextLine();
		contato.setNome(nome);
		if(nome.length() < 2) {
			throw new IllegalArgumentException("Nome muito curto!");
		}
		System.out.println("Informe o telefone do contato: ");
		String telefone = scan.nextLine();
		contato.setTelefone(telefone);
		
			impl.update(contato);
		}
		
	}
	
	public static void removerContato() {
		Contato contato = localizarContato();
		
		if(contato != null) {
			compromissoImpl.deleteAllByContatoId(contato.getId());
			impl.delete(contato.getId());
		} else  {
			System.out.println("Contato inexistente");
		} 
	}
	
	/*
	 public static void removerContato() {
		Contato contato = localizarContato();
		
		if(contato != null && contato.getCompromissos().isEmpty()) {
			impl.delete(contato.getId());
		} else if(contato != null) {
			System.out.println("Contato inexistente");
		} else {
			System.out.println("Contato já tem compromissos");
		}
	}
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
