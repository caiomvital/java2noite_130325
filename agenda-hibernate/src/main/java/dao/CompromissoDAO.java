package dao;

import java.util.List;

import model.Compromisso;

public interface CompromissoDAO {

	void save(Compromisso compromisso);
	Compromisso findById(int id);
	List<Compromisso> findAll();
	List<Compromisso> findByContatoId(int contatoId);
	void update(Compromisso compromisso);
	void delete(int id);
	void deleteAllByContatoId(int contatoId);
	
}
