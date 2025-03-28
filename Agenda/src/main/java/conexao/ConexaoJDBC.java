package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJDBC {

	private static final String URL = "jdbc:postgresql://localhost:5432/agenda_noite";
	private static final String USER = "caiomvital";
	private static final String PASS = "1234";
	
	public static Connection iniciarConexao() {
		try {
			Connection conexao = DriverManager.getConnection(URL, USER, PASS);
			
			System.out.println("Conectado com sucesso.");
			return conexao;
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			System.out.println();
			return null;
		}
	}
	
	public static void fecharConexao(Connection conexao) {
		if(conexao != null) {
			try {
				conexao.close();
				System.out.println("Conexão fechada com sucesso.");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	
}
