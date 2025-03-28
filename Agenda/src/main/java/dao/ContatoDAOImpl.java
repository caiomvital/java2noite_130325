package dao;

import java.util.List;
import java.util.Scanner;

import controller.ContatoCRUD;
import model.Compromisso;
import model.Contato;

public class ContatoDAOImpl implements ContatoDAO {

	private static Scanner scan = new Scanner(System.in);
	
	
	
	@Override
	public void inserir() {
		System.out.println("Digite o nome do contato: ");
		String nome = scan.nextLine();
		
		while(nome.length() <= 2 || nome.isBlank()) {
			System.out.println("Nome inválido");
			System.out.println("Tente novamente: ");
			nome = scan.nextLine();
			
		}
		
		System.out.println("Digite o telefone do contato: ");
		String telefone = scan.nextLine();
		
		Contato c = new Contato();
		c.setNome(nome);
		c.setTelefone(telefone);
		ContatoCRUD.adicionarContato(c);

	}

	@Override
	public Contato localizarPorId(int id) {
		return ContatoCRUD.localizarContatoPorId(id);

	}

	@Override
	public void atualizar(int id) {
		Contato contato = localizarPorId(id);
		
		if(contato != null) {
			System.out.println("Informe o novo nome do contato: ");
			String nome = scan.nextLine();
			System.out.println("Informe o novo telefone do contato: ");
			String telefone = scan.nextLine();
			contato.setNome(nome);
			contato.setTelefone(telefone);
			ContatoCRUD.alterarContato(contato);
		} else {
			System.out.println("Contato inexistente");
		}

	}

	@Override
	public void excluir(int id) {
		Contato contato = localizarPorId(id);
		CompromissoDAOImpl impl = new CompromissoDAOImpl();
		if(contato != null) {
			
			List<Compromisso> compromissos = impl.listarPorContato(id);
			if(compromissos == null) {
				ContatoCRUD.removerContato(id);
			} else {
				System.out.println("Contato tem compromissos associados.");
			}
			
		} else {
			System.out.println("Contato inexistente");
		}

	}

	@Override
	public List<Contato> listarTodos() {
		return ContatoCRUD.listarContatos();
	}

}
