package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import dao.CompromissoDAOImpl;
import model.Compromisso;
import model.Contato;

public class CompromissoService {

	private static Scanner scan = new Scanner(System.in);
	private static CompromissoDAOImpl impl = new CompromissoDAOImpl();

	public static void salvarCompromisso() {		
		Compromisso compromisso = new Compromisso();
		System.out.println("Informe o nome do compromisso: ");
		String nome = scan.nextLine();
		if (nome.length() < 2) {
			throw new IllegalArgumentException("Nome muito curto!");
		}
		System.out.println("Informe a descrição do compromisso: ");
		String descricao = scan.nextLine();
		Contato contato = ContatoService.localizarContato();
        LocalDateTime dataHora = obterDataHora();

		if (contato != null) {
			compromisso.setNome(nome);
			compromisso.setDescricao(descricao);
			compromisso.setContato(contato);
			compromisso.setDataHora(dataHora);
			impl.save(compromisso);
		} else {
			System.out.println("Contato inexistente");
		}

	}

	public static Compromisso localizarCompromisso() {
		System.out.println("Informe o ID do compromisso: ");
		int id = scan.nextInt();
		scan.nextLine();
		return impl.findById(id);
	}

	public static List<Compromisso> listarCompromissos() {
		return impl.findAll();
	}

	public static List<Compromisso> listarCompromissosPorContatoId(){
		Contato contato = ContatoService.localizarContato();
		
		if(contato != null) {
			return impl.findByContatoId(contato.getId());
		} else {
			return null;
		}
	}
	
	public static void alterarCompromisso() {
		Compromisso compromisso = localizarCompromisso();
		if (compromisso != null) {
			System.out.println("Informe o nome do compromisso: ");
			String nome = scan.nextLine();
			if (nome.length() < 2) {
				throw new IllegalArgumentException("Nome muito curto!");
			}
			System.out.println("Informe a descricao do compromisso: ");
			String descricao = scan.nextLine();
			Contato contato = ContatoService.localizarContato();
			LocalDateTime dataHora = obterDataHora();
			if (contato != null) {
				compromisso.setContato(contato);
				compromisso.setNome(nome);
				compromisso.setDescricao(descricao);
				compromisso.setDataHora(dataHora);
				impl.update(compromisso);
			} else {
				System.out.println("Contato inexistente");
			}
		}

	}

	public static void removerCompromisso() {
		Compromisso compromisso = localizarCompromisso();

		if (compromisso != null) {
			impl.delete(compromisso.getId());
		}
	}
	
	public static void removerTodosCompromissosDoContato() {
		Contato contato = ContatoService.localizarContato();
		
		if(contato != null) {
			impl.deleteAllByContatoId(contato.getId());
		}
	}
	
	private static LocalDateTime obterDataHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Digite a data e hora no formato dd/MM/yyyy HH:mm:");
        String dataHora = scan.nextLine();
        
        while(!dataHora.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}")) {
            System.out.println("Digite a data e hora no formato dd/MM/yyyy HH:mm:");
            dataHora = scan.nextLine();
        }
        
        LocalDateTime dataHoraFormatada = LocalDateTime.parse(dataHora, formatter);
        return dataHoraFormatada;
	}

}
