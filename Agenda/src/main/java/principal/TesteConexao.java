package principal;

import java.sql.Connection;
import java.util.Scanner;

import conexao.ConexaoJDBC;
import dao.ContatoDAOImpl;

public class TesteConexao {

	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {

		Connection conexao = ConexaoJDBC.iniciarConexao();

		ContatoDAOImpl impl = new ContatoDAOImpl();
		
		impl.inserir();
		
		impl.listarTodos().forEach(System.out::println);

		
		
		
		

	}

	
}
