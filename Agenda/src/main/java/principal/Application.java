package principal;

import java.util.List;
import java.util.Scanner;

import controller.Menu;
import dao.CompromissoDAOImpl;
import dao.ContatoDAOImpl;
import model.Compromisso;
import model.Contato;

public class Application {

	private static Scanner scan = new Scanner(System.in);
	private static ContatoDAOImpl contatoImpl = new ContatoDAOImpl();
	private static CompromissoDAOImpl compromissoImpl = new CompromissoDAOImpl();

	public static void iniciar() {
		int opcao = 0;

		while (true) {
			Menu.exibirMenuPrincipal();
			opcao = scan.nextInt();

			switch (opcao) {
			case 1 -> exibirMenuContatos();
			case 2 -> exibirMenuCompromissos();
			case 3 -> {
				System.out.println("Saindo do programa...");
				return;
			}
			default -> System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	private static void exibirMenuContatos() {
		int opcao = 0;
		while (true) {
			Menu.exibirMenuContatos();
			opcao = scan.nextInt();
			scan.nextLine();

			switch (opcao) {
			case 1 -> {
				contatoImpl.inserir();
			} // inserir contato
			case 2 -> {
				contatoImpl.listarTodos().forEach(System.out::println);
			} // listar contatos
			case 3 -> {

				int id = coletarId();
				Contato contato = contatoImpl.localizarPorId(id);
				if (contato != null) {
					System.out.println(contato);
				} else {
					System.out.println("Contato não encontrado.");
				}
			} // localizar contato por id
			case 4 -> {
				int id = coletarId();
				contatoImpl.atualizar(id);
			} // atualizar contato
			case 5 -> {
				int id = coletarId();
				contatoImpl.excluir(id);
			} // remover contato
			case 6 -> {
				System.out.println("Voltando ao Menu Principal...");
				return;
			}
			default -> System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	private static void exibirMenuCompromissos() {
		int opcao = 0;
		while (true) {
			Menu.exibirMenuCompromissos();
			opcao = scan.nextInt();

			switch (opcao) {
			case 1 -> {
				compromissoImpl.inserir();
			} // inserir compromisso
			case 2 -> {
				compromissoImpl.listarTodos().forEach(System.out::println);
			} // listar compromissos
			case 3 -> {
				int id = coletarId();
				Compromisso compromisso = compromissoImpl.localizarPorId(id);
				if (compromisso != null) {
					System.out.println(compromisso);
				} else {
					System.out.println("Compromisso não encontrado.");
				}

			} // localizar compromisso por id
			case 4 -> {
				int id = coletarId();
				Contato contato = contatoImpl.localizarPorId(id);
				if (contato != null) {
					System.out.println(contato);
					List<Compromisso> lista = compromissoImpl.listarPorContato(id);
					if (lista != null) {
						lista.forEach(System.out::println);
					} else {
						System.out.println("Este contato não tem compromissos associados.");
					}

				} else {
					System.out.println("Contato não encontrado.");
				}
			} // localizar compromisso por contato
			case 5 -> {
				int id = coletarId();
				compromissoImpl.atualizar(id);
			} // atualizar compromisso
			case 6 -> {
				int id = coletarId();
				compromissoImpl.excluir(id);
			} // remover compromisso

			case 7 -> {
				System.out.println("Voltando ao Menu Principal...");
				return;
			}
			default -> System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	private static int coletarId() {
		System.out.println("Entre com o ID: ");
		int id = scan.nextInt();
		scan.nextLine();
		return id;
	}

}
