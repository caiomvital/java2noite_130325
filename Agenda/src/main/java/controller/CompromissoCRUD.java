package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import conexao.ConexaoJDBC;
import model.Compromisso;
import model.Contato;

public class CompromissoCRUD {

	private static Scanner scan = new Scanner(System.in);

	public static void adicionarCompromisso() {
		Connection conexao = ConexaoJDBC.iniciarConexao();

		if (conexao != null) {

			try {

				System.out.println("Informe o ID do contato: ");
				int id = scan.nextInt(); scan.nextLine();
				
				
				Contato contato = ContatoCRUD.localizarContatoPorId(id);

				if(contato != null) {
					Compromisso compromisso = new Compromisso();
					
					System.out.println("Digite o nome do compromisso: ");
					String nome = scan.nextLine();
					compromisso.setNome(nome);
					System.out.println("Digite a descrição do compromisso: ");
					String descricao = scan.nextLine();
					compromisso.setDescricao(descricao);
					
					compromisso.setContato(contato);

					String sql = "INSERT INTO compromissos (nome, descricao, contato_id) VALUES (?, ?, ?)";

					PreparedStatement ps = conexao.prepareStatement(sql);

					ps.setString(1, compromisso.getNome());
					ps.setString(2, compromisso.getDescricao());
					ps.setInt(3, id);

					ps.executeUpdate();

					System.out.println("Compromisso adicionado com sucesso.");
				} else {
					System.out.println("Contato não existe com o ID " + id);
				}
				
				

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				ConexaoJDBC.fecharConexao(conexao);
			}

		}
	}

	public static List<Compromisso> listarCompromissos() {
		Connection conexao = ConexaoJDBC.iniciarConexao();
		if (conexao != null) {
			try {

				List<Compromisso> compromissos = new ArrayList<>();

				String sql = "SELECT * FROM compromissos ORDER BY id ASC";
				PreparedStatement ps = conexao.prepareStatement(sql);

				ResultSet conjuntoDeResultados = ps.executeQuery();

				while (conjuntoDeResultados.next()) {
					Compromisso compromisso = new Compromisso();
					compromisso.setId(conjuntoDeResultados.getInt("id"));
					compromisso.setNome(conjuntoDeResultados.getString("nome"));
					compromisso.setDescricao(conjuntoDeResultados.getString("descricao"));
					int idContato = conjuntoDeResultados.getInt("contato_id");
					compromisso.setContato(ContatoCRUD.localizarContatoPorId(idContato));
					compromissos.add(compromisso);
				}

				return compromissos;

			} catch (SQLException e) {

			} finally {
				ConexaoJDBC.fecharConexao(conexao);
			}
		}
		return null;

	}

	public static List<Compromisso> listarCompromissosPorContato(Contato contato) {
		Connection conexao = ConexaoJDBC.iniciarConexao();
		if (conexao != null) {
			try {

				List<Compromisso> compromissos = new ArrayList<>();

				String sql = "SELECT * FROM compromissos WHERE contato_id = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);

				ps.setInt(1, contato.getId());
				
				ResultSet conjuntoDeResultados = ps.executeQuery();

				while (conjuntoDeResultados.next()) {
					Compromisso compromisso = new Compromisso();
					compromisso.setId(conjuntoDeResultados.getInt("id"));
					compromisso.setNome(conjuntoDeResultados.getString("nome"));
					compromisso.setDescricao(conjuntoDeResultados.getString("descricao"));
					int idContato = conjuntoDeResultados.getInt("contato_id");
					compromisso.setContato(ContatoCRUD.localizarContatoPorId(idContato));
					compromissos.add(compromisso);
				}

				return compromissos;

			} catch (SQLException e) {

			} finally {
				ConexaoJDBC.fecharConexao(conexao);
			}
		}
		return null;
	}
	
	public static Compromisso localizarCompromissoPorId(int id) {
		Connection conexao = ConexaoJDBC.iniciarConexao();
		if (conexao != null) {
			try {


				String sql = "SELECT * FROM compromissos WHERE id = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);

				ps.setInt(1, id);

				ResultSet conjuntoDeResultados = ps.executeQuery();

				if (conjuntoDeResultados.next()) {
					Compromisso compromisso = new Compromisso();
					compromisso.setId(conjuntoDeResultados.getInt("id"));
					compromisso.setNome(conjuntoDeResultados.getString("nome"));
					compromisso.setDescricao(conjuntoDeResultados.getString("descricao"));
					int idContato = conjuntoDeResultados.getInt("contato_id");
					compromisso.setContato(ContatoCRUD.localizarContatoPorId(idContato));
					
					return compromisso;
				}

				return null;

			} catch (SQLException e) {

			} finally {
				ConexaoJDBC.fecharConexao(conexao);
			}
		}

		return null;

	}

	public static void alterarCompromisso(int id) {
		Compromisso compromisso = localizarCompromissoPorId(id);

		if (compromisso != null) {
			Connection conexao = ConexaoJDBC.iniciarConexao();
			System.out.println("Informe o novo nome do compromisso: ");
			String nome = scan.nextLine();
			System.out.println("Informe a nova descrição do compromisso: ");
			String descricao = scan.nextLine();
				
			System.out.println("Informe o ID do novo contato: ");
			int idContato = scan.nextInt(); scan.nextLine();
			Contato novoContato = ContatoCRUD.localizarContatoPorId(idContato);
			
			if(novoContato != null) {
				compromisso.setNome(nome);
				compromisso.setDescricao(descricao);
				compromisso.setContato(novoContato);
			} else {
				System.out.println("Novo contato não encontrado.");
			}


			String sql = "UPDATE compromissos SET nome = ?, descricao = ?, contato_id = ? where ID = ?";

			try {

				PreparedStatement ps = conexao.prepareStatement(sql);

				ps.setString(1, compromisso.getNome());
				ps.setString(2, compromisso.getDescricao());
				ps.setInt(3, idContato);
				ps.setInt(4, id);

				int linhasAfetadas = ps.executeUpdate();

				if (linhasAfetadas > 0) {

					System.out.println("Compromisso atualizado com sucesso.");

				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				ConexaoJDBC.fecharConexao(conexao);
			}

		} else {
			System.out.printf("Contato com o id %d não existe%n", id);
		}

	}

	public static void removerCompromisso(int id) {
		Compromisso compromisso = localizarCompromissoPorId(id);

		if (compromisso != null) {
			Connection conexao = ConexaoJDBC.iniciarConexao();

			String sql = "DELETE FROM compromissos WHERE id = ?";

			try {

				PreparedStatement ps = conexao.prepareStatement(sql);

				ps.setInt(1, id);
				

				int linhasAfetadas = ps.executeUpdate();

				if (linhasAfetadas > 0) {

					System.out.println("Compromisso removido com sucesso.");

				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				ConexaoJDBC.fecharConexao(conexao);
			}

		} else {
			System.out.printf("Compromisso com o id %d não existe%n", id);
		}
	}

}
