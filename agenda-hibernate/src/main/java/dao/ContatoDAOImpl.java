package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import model.Contato;
import utils.JPAUtil;

public class ContatoDAOImpl implements ContatoDAO {

	private EntityManager em;
	
	public ContatoDAOImpl() {
		this.em = JPAUtil.getEntityManager();
	}
	
	
	@Override
	public void save(Contato contato) {
		em.getTransaction().begin();
		em.persist(contato);
		em.getTransaction().commit();
		
	}

	@Override
	public Contato findById(int id) {
		return em.find(Contato.class, id);
	}

	@Override
	public List<Contato> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Contato contato) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
