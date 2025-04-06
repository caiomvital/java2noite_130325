package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import conexao.ConexaoJDBC;
import model.Compromisso;
import model.Contato;

public class CompromissoCRUD {

	private static Scanner scan = new Scanner(System.in);

	public static void adicionarCompromisso(Compromisso compromisso) {
		Connection conexao = ConexaoJDBC.iniciarConexao();

		if (conexao != null) {

			try {

					String sql = "INSERT INTO compromissos (nome, descricao, contato_id, data_hora) VALUES (?, ?, ?, ?)";

					PreparedStatement ps = conexao.prepareStatement(sql);
					
					Timestamp timestamp = Timestamp.valueOf(compromisso.getDataHora());
					
					ps.setString(1, compromisso.getNome());
					ps.setString(2, compromisso.getDescricao());
					ps.setInt(3, compromisso.getContato().getId());
					ps.setTimestamp(4, timestamp);


					ps.executeUpdate();

					System.out.println("Compromisso adicionado com sucesso.");
				
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
					LocalDateTime localDateTime = conjuntoDeResultados.getTimestamp("data_hora").toLocalDateTime();
					compromisso.setDataHora(localDateTime);
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
					LocalDateTime localDateTime = conjuntoDeResultados.getTimestamp("data_hora").toLocalDateTime();
					compromisso.setDataHora(localDateTime);
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
					LocalDateTime localDateTime = conjuntoDeResultados.getTimestamp("data_hora").toLocalDateTime();
					compromisso.setDataHora(localDateTime);
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

	public static void alterarCompromisso(Compromisso compromisso) {

		if (compromisso != null) {
			Connection conexao = ConexaoJDBC.iniciarConexao();

			String sql = "UPDATE compromissos SET nome = ?, descricao = ?, contato_id = ?, data_hora = ? where ID = ?";

			try {

				PreparedStatement ps = conexao.prepareStatement(sql);

				ps.setString(1, compromisso.getNome());
				ps.setString(2, compromisso.getDescricao());
				ps.setInt(3, compromisso.getContato().getId());
				ps.setTimestamp(4, Timestamp.valueOf(compromisso.getDataHora()));
				ps.setInt(5, compromisso.getId());

				int linhasAfetadas = ps.executeUpdate();

				if (linhasAfetadas > 0) {

					System.out.println("Compromisso atualizado com sucesso.");

				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				ConexaoJDBC.fecharConexao(conexao);
			}

		}
	}

	public static void excluirCompromisso(Compromisso compromisso) {
		

		if (compromisso != null) {
			Connection conexao = ConexaoJDBC.iniciarConexao();

			String sql = "DELETE FROM compromissos WHERE id = ?";

			try {

				PreparedStatement ps = conexao.prepareStatement(sql);

				ps.setInt(1, compromisso.getId());

				int linhasAfetadas = ps.executeUpdate();

				if (linhasAfetadas > 0) {

					System.out.println("Compromisso removido com sucesso.");

				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				ConexaoJDBC.fecharConexao(conexao);
			}

		}
	}

}
