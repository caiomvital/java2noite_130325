package dao;

import java.util.List;

import model.Compromisso;

public interface CompromissoDAO {

	
	void inserir(Compromisso compromisso);
	Compromisso localizarPorId(int id);
	List<Compromisso> listarPorContato(int contatoId);
	void atualizar(Compromisso compromisso);
	void excluir(Compromisso compromisso);
	List<Compromisso> listarTodos();
	
}
