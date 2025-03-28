package dao;

import java.util.List;

import model.Contato;

public interface ContatoDAO {

	void inserir();
	Contato localizarPorId(int id);
	void atualizar(int id);
	void excluir(int id);
	List<Contato> listarTodos();
	
}
