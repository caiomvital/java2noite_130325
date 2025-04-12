package controller;

import java.util.List;
import java.util.Scanner;

import model.Compromisso;
import model.Contato;
import service.CompromissoService;
import service.ContatoService;

public class Aplicacao {

	private static final Scanner scan = new Scanner(System.in);

	public static void exibirMenuPrincipal() {
		while(true) {
		Menu.exibirMenuPrincipal();
		int opcao = scan.nextInt();
		scan.nextLine();

		switch (opcao) {
		case 1 -> exibirMenuContatos();
		case 2 -> exibirMenuCompromissos();
		case 3 -> {
			System.out.println("Encerrando o programa...");
			System.exit(0); // método simples pra encerrar o programa
		}
		default -> {
			System.out.println("Opção inválida! Tente novamente.");
			exibirMenuPrincipal();
		}
		}
		}
	}

	public static void exibirMenuContatos() {
		Menu.exibirMenuContatos();
		int opcao = scan.nextInt();
		scan.nextLine();

		switch (opcao) {
		case 1 -> ContatoService.salvarContato();
		case 2 -> {
			List<Contato> contatos = ContatoService.listarContatos();

			if (!contatos.isEmpty()) {
				contatos.forEach(System.out::println);
			} else {
				System.out.println("Não há contatos registrados.");
			}
		}

		case 3 -> {
			Contato contato = ContatoService.localizarContato();
			System.out.println(contato != null ? contato : "contato inexistente");
		}

		case 4 -> ContatoService.alterarContato();
		case 5 -> ContatoService.removerContato();
		case 6 -> exibirMenuPrincipal();
		default -> {
			System.out.println("Opção inválida! Tente novamente.");
			exibirMenuContatos();
		}
		}
	}

	public static void exibirMenuCompromissos() {
        Menu.exibirMenuCompromissos();
        int opcao = scan.nextInt(); scan.nextLine(); 

        switch (opcao) {
            case 1 -> CompromissoService.salvarCompromisso();
            case 2 -> {
            	List<Compromisso> compromissos = CompromissoService.listarCompromissos();

			if (!compromissos.isEmpty()) {
				compromissos.forEach(System.out::println);
			} else {
				System.out.println("Não há compromissos registrados.");
			}
		}
            case 3 -> {
            	Compromisso compromisso = CompromissoService.localizarCompromisso();
    			System.out.println(compromisso != null ? compromisso : "compromisso inexistente");
            	
            }
            case 4 -> {
            	List<Compromisso> compromissos = CompromissoService.listarCompromissosPorContatoId();

    			if (!compromissos.isEmpty()) {
    				compromissos.forEach(System.out::println);
    			} else {
    				System.out.println("Não há compromissos registrados para este contato.");
    			}
            }
            case 5 -> CompromissoService.alterarCompromisso();
            case 6 -> CompromissoService.removerCompromisso();
            case 7 -> exibirMenuPrincipal();
            default -> {
                System.out.println("Opção inválida! Tente novamente.");
                exibirMenuCompromissos();
            }
        }
    }
}
