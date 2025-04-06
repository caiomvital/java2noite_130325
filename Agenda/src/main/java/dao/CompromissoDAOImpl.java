package dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import controller.CompromissoCRUD;
import model.Compromisso;
import model.Contato;

public class CompromissoDAOImpl implements CompromissoDAO {

	private static Scanner scan = new Scanner(System.in);
	ContatoDAOImpl impl = new ContatoDAOImpl();
	
	@Override
	public void inserir() {
		Compromisso compromisso = new Compromisso();
		System.out.println("Digite o nome do compromisso: ");
		String nome = scan.nextLine();
		System.out.println("Insira a descrição do compromisso: ");
		String descricao = scan.nextLine();
		System.out.println("Insira o ID do contato associado: ");
		int id = scan.nextInt(); scan.nextLine();
		Contato contato = impl.localizarPorId(id);
		
		System.out.println("Informe a data (dd/mm/aaaa): ");
		String data = scan.nextLine();
		String[] dataArray = data.split("/");
		System.out.println("Informe a hora (hh:mm): ");
		String hora = scan.nextLine();
		String[] horaArray = hora.split(":");
				
		LocalDateTime dataHora = LocalDateTime.of(Integer.parseInt(dataArray[2]), Integer.parseInt(dataArray[1]), Integer.parseInt(dataArray[0]), Integer.parseInt(horaArray[0]), Integer.parseInt(horaArray[1]));
		
		
		
		if(contato != null) {
		
		compromisso.setNome(nome);
		compromisso.setDescricao(descricao);
		compromisso.setContato(contato);
		compromisso.setDataHora(dataHora);
		CompromissoCRUD.adicionarCompromisso(compromisso);
		} else {
			System.out.println("Contato não encontrado.");
		}
	}

	@Override
	public Compromisso localizarPorId(int id) {
		return CompromissoCRUD.localizarCompromissoPorId(id);
	}

	@Override
	public List<Compromisso> listarPorContato(int contatoId) {
		Contato contato = impl.localizarPorId(contatoId);
		
		if(contato != null) {
			
			List<Compromisso> compromissos = CompromissoCRUD.listarCompromissosPorContato(contato);
			
			 if(!compromissos.isEmpty()) {
				 return compromissos;
			 } else {
				 System.out.println("Contato não tem compromissos associados");
				 return null;
			 }
					
		} else {
			System.out.println("Contato não encontrado.");
			return null;
		}
		
	}

	@Override
	public void atualizar(int id) {
		Compromisso compromisso = localizarPorId(id);
		if(compromisso != null) {
			System.out.println("Informe o id do contato: ");
			int idContato = scan.nextInt(); scan.nextLine();
			Contato contato = impl.localizarPorId(idContato);
			if(contato != null) {
				System.out.println("Informe o novo nome do compromisso: ");
				String nome = scan.nextLine();
				System.out.println("Informe a nova descrição do compromisso: ");
				String descricao = scan.nextLine();
				compromisso.setNome(nome);
				compromisso.setDescricao(descricao);
				compromisso.setContato(contato);
				CompromissoCRUD.alterarCompromisso(compromisso);
			}
		}

	}

	@Override
	public void excluir(int id) {
		Compromisso compromisso = localizarPorId(id);
		
		if(compromisso != null) {
			CompromissoCRUD.excluirCompromisso(compromisso);
		} else {
			System.out.printf("Compromisso não existe com o ID %d%n", id);
		}

	}

	@Override
	public List<Compromisso> listarTodos() {
		return CompromissoCRUD.listarCompromissos();
	}

}
