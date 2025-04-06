package dao;

import java.util.List;

import model.Compromisso;

public interface CompromissoDAO {

	
	void inserir();
	Compromisso localizarPorId(int id);
	List<Compromisso> listarPorContato(int contatoId);
	void atualizar(int id);
	void excluir(int id);
	List<Compromisso> listarTodos();
	
}
