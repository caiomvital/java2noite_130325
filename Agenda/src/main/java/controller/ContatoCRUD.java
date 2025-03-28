package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import conexao.ConexaoJDBC;
import model.Contato;

public class ContatoCRUD {

	public static void adicionarContato(Contato contato) {
		Connection conexao = ConexaoJDBC.iniciarConexao();

		if (conexao != null) {

			try {

				String sql = "INSERT INTO contatos (nome, telefone) VALUES (?, ?)";

				PreparedStatement ps = conexao.prepareStatement(sql);

				ps.setString(1, contato.getNome());
				ps.setString(2, contato.getTelefone());

				ps.executeUpdate();

				System.out.println("Contato adicionado com sucesso.");

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				ConexaoJDBC.fecharConexao(conexao);
			}

		}
	}

	public static List<Contato> listarContatos() {
		Connection conexao = ConexaoJDBC.iniciarConexao();
		if (conexao != null) {
			try {

				List<Contato> contatos = new ArrayList<>();

				String sql = "SELECT * FROM contatos ORDER BY id ASC";
				PreparedStatement ps = conexao.prepareStatement(sql);

				ResultSet conjuntoDeResultados = ps.executeQuery();

				while (conjuntoDeResultados.next()) {
					Contato contato = new Contato();
					contato.setId(conjuntoDeResultados.getInt("id"));
					contato.setNome(conjuntoDeResultados.getString("nome"));
					contato.setTelefone(conjuntoDeResultados.getString("telefone"));
					contatos.add(contato);
				}

				return contatos;

			} catch (SQLException e) {

			} finally {
				ConexaoJDBC.fecharConexao(conexao);
			}
		}
		return null;

	}

	public static Contato localizarContatoPorId(int id) {
		Connection conexao = ConexaoJDBC.iniciarConexao();
		if (conexao != null) {
			try {

				String sql = "SELECT * FROM contatos WHERE id = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);

				ps.setInt(1, id);

				ResultSet conjuntoDeResultados = ps.executeQuery();

				if (conjuntoDeResultados.next()) {
					Contato contato = new Contato();
					contato.setId(conjuntoDeResultados.getInt("id"));
					contato.setNome(conjuntoDeResultados.getString("nome"));
					contato.setTelefone(conjuntoDeResultados.getString("telefone"));
					return contato;
				}

				return null;

			} catch (SQLException e) {

			} finally {
				ConexaoJDBC.fecharConexao(conexao);
			}
		}

		return null;

	}

	public static void alterarContato(Contato contato) {

		if (contato != null) {
			Connection conexao = ConexaoJDBC.iniciarConexao();
			

			String sql = "UPDATE contatos SET nome = ?, telefone = ? where ID = ?";

			try {

				PreparedStatement ps = conexao.prepareStatement(sql);

				ps.setString(1, contato.getNome());
				ps.setString(2, contato.getTelefone());
				ps.setInt(3, contato.getId());

				int linhasAfetadas = ps.executeUpdate();

				if (linhasAfetadas > 0) {

					System.out.println("Contato atualizado com sucesso.");

				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				ConexaoJDBC.fecharConexao(conexao);
			}

		}

	}

	public static void removerContato(int id) {
		Contato contato = localizarContatoPorId(id);

		if (contato != null) {
			Connection conexao = ConexaoJDBC.iniciarConexao();

			String sql = "DELETE FROM contatos WHERE id = ?";

			try {

				PreparedStatement ps = conexao.prepareStatement(sql);

				ps.setInt(1, id);
				

				int linhasAfetadas = ps.executeUpdate();

				if (linhasAfetadas > 0) {

					System.out.println("Contato removido com sucesso.");

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

}
