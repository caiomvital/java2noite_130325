package dao;

import java.util.List;

import model.Contato;

public interface ContatoDAO {

	void save(Contato contato);
	Contato findById(int id);
	List<Contato> findAll();
	void update(Contato contato);
	void delete(int id);
	
}
